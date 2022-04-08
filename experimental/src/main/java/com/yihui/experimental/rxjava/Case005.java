package com.yihui.experimental.rxjava;

import com.yihui.experimental.util.CommonUtil;
import com.yihui.experimental.util.Log;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import lombok.Data;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.yihui.experimental.AppMain.logger;

public class Case005 {
    private static final String TAG = "case005";

    private Case005() {
    }

    @Data
    private static class A {
        private Integer id;
    }

    private int getOneId(List<A> listA) {
        return listA.stream()
                .map(a -> a.id)
                .findAny()
                .orElse(0);
    }

    public static void test() {
//        Observable.timer(2, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation()) // timer 默认在新线程，所以需要切换回主线程
//                .subscribe(aLong -> {
//                    logger.warn("timer :" + aLong);
//                });
//        Observable.just(1, 2, 3, 4)
//                .doOnNext(integer -> logger.warn("doOnNext 保存 " + integer + "成功"))
//                .subscribe(integer -> logger.warn("doOnNext :" + integer));

        Log.e(TAG, "interval start : ");
        Observable.interval(1,1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.computation()) // 由于interval默认在新线程，所以我们应该切回主线程
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        Log.e(TAG, "interval :" + aLong );
                    }
                });

//        logger.warn( "window\n");
//        Observable.interval(1, TimeUnit.SECONDS) // 间隔一秒发一次
//                .take(15) // 最多接收15个
//                .window(3, TimeUnit.SECONDS)
//                .subscribeOn(Schedulers.io())
//                .observeOn(Schedulers.computation())
//                .subscribe(new Consumer<Observable<Long>>() {
//                    @Override
//                    public void accept(@NonNull Observable<Long> longObservable) throws Exception {
//                        //mRxOperatorsText.append("Sub Divide begin...\n");
//                        logger.warn( "Sub Divide begin...");
//                        longObservable.subscribeOn(Schedulers.io())
//                                .observeOn(Schedulers.computation())
//                                .subscribe(new Consumer<Long>() {
//                                    @Override
//                                    public void accept(@NonNull Long aLong) throws Exception {
//                                        //mRxOperatorsText.append("Next:" + aLong + "\n");
//                                        logger.warn( "Next:" + aLong );
//                                    }
//                                });
//                    }
//                });

        CommonUtil.sleep(16000);
    }
}
