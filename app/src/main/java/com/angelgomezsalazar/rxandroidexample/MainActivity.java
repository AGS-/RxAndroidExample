package com.angelgomezsalazar.rxandroidexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import rx.Observable;
import rx.Observer;
import rx.Subscription;
import rx.functions.Action1;
import rx.functions.Func1;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // region Basics of Observers and Observables
        // Emits "Hello"
        Observable<String> myObservable = Observable.just("Hello");

        Observer<String> myObserver = new Observer<String>() {

            @Override
            public void onCompleted() {
                // Called when the observable has no more data to emit
            }

            @Override
            public void onError(Throwable e) {
                // Called when the observable encounters an error
            }

            @Override
            public void onNext(String s) {
                // Called each time the observable emits data
                Log.d("MyObserver", s);
            }
        };

        // Used because and onCompleted and onError are often left unused in Observer
        Action1<String> myAction = new Action1<String>() {
            @Override
            public void call(String s) {
                Log.d("MyAction", s);
            }
        };

        Subscription mySubscription = myObservable.subscribe(myAction);
        mySubscription.unsubscribe();
        // endregion

        // region Using Operators
        // Iterates over the Integer array
        Observable<Integer> myArrayObservable = Observable.from(new Integer[] {1, 2, 3, 4, 5, 6});

        myArrayObservable = myArrayObservable.map(new Func1<Integer, Integer>() {
            @Override
            public Integer call(Integer integer) {
                // Square the number
                return integer * integer;
            }
        });

        myArrayObservable.subscribe(new Action1<Integer>() {
            @Override
            public void call(Integer integer) {
                Log.d("MyArrayAction", String.valueOf(integer));
            }
        });
        // endregion

    }
}
