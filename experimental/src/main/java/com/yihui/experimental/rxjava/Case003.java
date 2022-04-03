package com.yihui.experimental.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;

import static com.yihui.experimental.AppMain.logger;

public class Case003 {
    @RequiredArgsConstructor
    private static class A implements Observer<String> {
        private final String name;

        @Override
        public void onSubscribe(Disposable d) {
            logger.warn(name + "--> onSubscribe");
        }

        @Override
        public void onNext(String t) {
            logger.warn(name + "--> onNext = " + t);
        }

        @Override
        public void onError(Throwable e) {
            logger.warn(name + "--> onError");
        }

        @Override
        public void onComplete() {
            logger.warn(name + "--> onComplete");
        }
    }

    public static void test() {
        Observable<String> sender = Observable.just("long", "longer", "longest");



        A a = new A("a");

        // 1. 进行订阅，subscribe(Observer)
        sender
                .map(i -> i + " er")
                .filter(i -> i.length() > 7)
                .observeOn(Schedulers.io())
                //.subscribe(s -> logger.warn("accept: " + s));
                .subscribe(a);

    }
}
