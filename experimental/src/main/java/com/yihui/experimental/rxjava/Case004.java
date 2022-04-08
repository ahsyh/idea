package com.yihui.experimental.rxjava;

import com.yihui.experimental.util.CommonUtil;
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

public class Case004 {
    private Case004() {
    }

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
                logger.warn("observable start, " + this);
                emitter.onNext("Hello1");
                CommonUtil.sleep(600);
                emitter.onNext("Hello2");
                CommonUtil.sleep(600);
                emitter.onNext("Hello3");
                CommonUtil.sleep(600);
                emitter.onNext("Hello4");
                CommonUtil.sleep(600);
                emitter.onNext("Hello5");
                CommonUtil.sleep(600);
                emitter.onComplete();
                logger.warn("observable end" + this);
            }
        });

        // 创建Observer(观察者), 可以接受所有通知
        Observer<String> obs1 = new Case004.A("obs1");
        Observer<String> obs2 = new Case004.A("obs2");

        // 1. 进行订阅，subscribe(Observer)
        observable
                .map(i -> "test1 " + i)
                .subscribeOn(Schedulers.computation())
                .observeOn(Schedulers.io())
                .subscribe(obs1);

        CommonUtil.sleep(1500);

        observable
                .map(i -> "test2 " + i)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation())
                .subscribe(obs2);

        CommonUtil.sleep(3500);

//        // 创建只接受 onNext(item) 通知的Consumer(观察者)
//        Consumer<String> nextConsumer = new Consumer<String>() {
//
//            @Override
//            public void accept(String t) throws Exception {
//                logger.warn("--> accept nextConsumer: " + t);
//            }
//        };
//
//        // 创建只接受 onError(Throwable) 通知的Consumer(观察者)
//        Consumer<Throwable> errorConsumer = new Consumer<Throwable>() {
//
//            @Override
//            public void accept(Throwable t) throws Exception {
//                logger.warn("-- accept errorConsumer: " + t);
//            }
//        };
//
//        // 创建只接受 onComplete() 通知的Action(观察者)
//        Action completedAction = new Action() {
//
//            @Override
//            public void run() throws Exception {
//                logger.warn("--> run completedAction");
//            }
//        };
//
//        // 创建只接受 onSubscribe 通知的Consumer(观察者)
//        Consumer<Disposable> onSubscribeComsumer = new Consumer<Disposable>() {
//
//            @Override
//            public void accept(Disposable t) throws Exception {
//                logger.warn("--> accept onSubscribeComsumer ");
//            }
//        };
//        logger.warn("---------------------------------------------");

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
