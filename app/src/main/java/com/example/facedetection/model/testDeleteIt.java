package com.example.facedetection.model;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;

public class testDeleteIt {
    private void test() {
        Observable.just("")
                .flatMap(new Function<String, ObservableSource<Boolean>>() {
                             @Override
                             public ObservableSource<Boolean> apply(String s) throws Exception {
                                 return Observable.just(true);
                             }
                         },
                        new BiFunction<String, Boolean, String>() {
                            @Override
                            public String apply(String s, Boolean aBoolean) throws Exception {
                                return s + "" + aBoolean;
                            }
                        }
                );
    }
}
