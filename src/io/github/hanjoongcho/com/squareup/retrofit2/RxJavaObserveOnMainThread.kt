package io.github.hanjoongcho.com.squareup.retrofit2

import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.Call
import rx.schedulers.Schedulers
import java.lang.reflect.Type
import rx.schedulers.Schedulers.io
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import rx.Scheduler
import rx.Observable;


fun main(args: Array<String>) {
    val observeOn = Schedulers.computation() // Or use mainThread() for Android.
    val retrofit = Retrofit.Builder()
            .baseUrl("http://example.com")
            .addCallAdapterFactory(ObserveOnMainCallAdapterFactory(observeOn))
            .addCallAdapterFactory(RxJavaCallAdapterFactory.createWithScheduler(io()))
            .build()
}

internal class ObserveOnMainCallAdapterFactory(val scheduler: Scheduler) : CallAdapter.Factory() {

    override fun get(returnType: Type, annotations: Array<Annotation>, retrofit: Retrofit): CallAdapter<*, *>? {

        if (CallAdapter.Factory.getRawType(returnType) != Observable::class.java) {
            return null // Ignore non-Observable types.
        }

        // Look up the next call adapter which would otherwise be used if this one was not present.
        // noinspection unchecked returnType checked above to be Observable.
        val delegate = retrofit.nextCallAdapter(this, returnType,
                annotations) as CallAdapter<Any, Observable<*>>

        return object : CallAdapter<Any, Any> {
            override fun adapt(call: Call<Any>): Any {
                // Delegate to get the normal Observable...
                val o = delegate.adapt(call)
                // ...and change it to send notifications to the observer on the specified scheduler.
                return o.observeOn(scheduler)
            }

            override fun responseType(): Type {
                return delegate.responseType()
            }
        }
    }

}