package com.yihui.experimental.rxjava;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

import java.util.Objects;

public class Case001<T> {

    private static class MObserver<T> implements Observer<T> {
        @Override
        public void onSubscribe(Disposable d) {
            System.out.println("--------------------------------");
            System.out.println("--> onSubscribe");
        }

        @Override
        public void onNext(T t) {
            System.out.println("--> onNext: " + t);
        }

        @Override
        public void onError(Throwable e) {
            System.out.println("--> onError: " + e);
        }

        @Override
        public void onComplete() {
            System.out.println("--> onComplete");
        }
    }

    final PublishSubject<T> subject = PublishSubject.create();

    public void test(T value1, T value2, T value3) {
        // 释放订阅后接收到正常发射的数据，有error将不会发射任何数据

        // 观察者对象
        MObserver<T> observer = new MObserver<>();
        subject.filter(Objects::nonNull)
                .subscribe(observer);

        // 1. 此时订阅将释放后续正常发射的数据： 1，2, 3, 4, error
        // subject.subscribe(observer);
        subject.onNext(value1);

        // 2. 此时订阅，发射后续正常发射的数据：3, 4, error
        //subject.subscribe(observer);
        subject.onNext(value2);

        // 此时将不会发送任何数据，直接发送error
        subject.onNext(value3);
        subject.onError(new NullPointerException());
        subject.onComplete();

        // 3. 此时订阅如果有error，仅发送error，否则无数据发射
        //subject.subscribe(observer);
    }
}
