package com.yihui.experimental.rxjava;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.RequiredArgsConstructor;

import static com.yihui.experimental.AppMain.logger;

public class Case002 {
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
        // 创建Observable(被观察者)
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {

            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("Hello");
                emitter.onNext("World");
                emitter.onComplete();
            }
        });
        Observable<String> sender = Observable.fromArray("A", "B", "C");

        // 创建Observer(观察者), 可以接受所有通知
        Observer<String> obs1 = new A("obs1");
        Observer<String> obs2 = new A("obs2");

        // 创建只接受 onNext(item) 通知的Consumer(观察者)
        Consumer<String> nextConsumer = new Consumer<String>() {

            @Override
            public void accept(String t) throws Exception {
                logger.warn("--> accept nextConsumer: " + t);
            }
        };

        // 创建只接受 onError(Throwable) 通知的Consumer(观察者)
        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {

            @Override
            public void accept(Throwable t) throws Exception {
                logger.warn("-- accept errorConsumer: " + t);
            }
        };

        // 创建只接受 onComplete() 通知的Action(观察者)
        Action completedAction = new Action() {

            @Override
            public void run() throws Exception {
                logger.warn("--> run completedAction");
            }
        };

        // 创建只接受 onSubscribe 通知的Consumer(观察者)
        Consumer<Disposable> onSubscribeComsumer = new Consumer<Disposable>() {

            @Override
            public void accept(Disposable t) throws Exception {
                logger.warn("--> accept onSubscribeComsumer ");
            }
        };

        // 1. 进行订阅，subscribe(Observer)
        sender
                .map(i -> "test1 " + i)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(obs1);
        sender
                .map(i -> "test2 " + i)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(obs2);

        try{
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            logger.warn("get interruption " + e);
        }

        logger.warn("---------------------------------------------");
//        // 2. 进行订阅，subscribe(Consumer onNext)
//        observable.subscribe(nextConsumer);
//
//        logger.warn("---------------------------------------------");
//        // 3. 进行订阅，subscribe(Consumer onNext, Consumer onError)
//        observable.subscribe(nextConsumer, errorConsumer);
//
//        logger.warn("---------------------------------------------");
//        // 4. 进行订阅，subscribe(Consumer onNext, Consumer onError, Action onCompleted)
//        observable.subscribe(nextConsumer, errorConsumer, completedAction);
//
//        logger.warn("---------------------------------------------");
//        // 5. 进行订阅，subscribe(Consumer onNext, Consumer onError, Action onCompleted, Consumer onSubscribe)
//        observable.subscribe(nextConsumer, errorConsumer, completedAction, onSubscribeComsumer);
    }
}
