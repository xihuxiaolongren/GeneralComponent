package me.xihuxiaolong.generalcomponent.common.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import me.xihuxiaolong.generalcomponent.common.MyApplication;
import me.xihuxiaolong.generalcomponent.common.model.DoubanHttpResult;
import me.xihuxiaolong.generalcomponent.common.model.Subject;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by IntelliJ IDEA.
 * User: xiaolong
 * Date: 16/8/3.
 */
public class DoubanApiServiceImpl implements DoubanApiService{

    public static final String BASE_URL = "https://api.douban.com/";

    IDoubanApiService iDoubanApiService;

    public DoubanApiServiceImpl(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .build();
        Retrofit retrofit= new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        iDoubanApiService = retrofit.create(IDoubanApiService.class);
    }

    @Override
    public void getTopMovie(Subscriber<List<Subject>> subscriber, final int start, final int count){
        Observable<List<Subject>> observable = observeNetStatus(
                iDoubanApiService.getTopMovie(start, count)
                        .map(new HttpResultFunc<List<Subject>>()));
        toSubscribe(observable, subscriber);
    }


    private <T> Observable<T> observeNetStatus(final Observable<T> observable){
        return Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                ConnectivityManager cm =
                        (ConnectivityManager) MyApplication.getInstance().getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
                boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
                if (!isConnected) {
                    throw new ApiException("Wi-Fi和移动数据已断开");
                }
                subscriber.onNext("ok");
                subscriber.onCompleted();
            }
        }).flatMap(new Func1<String, Observable<T>>() {
            @Override
            public Observable<T> call(String s) {
                return observable;
            }
        });
    }


    public interface IDoubanApiService {

        @GET("v2/movie/top250")
        Observable<DoubanHttpResult<List<Subject>>> getTopMovie(@Query("start") int start, @Query("count") int count);

    }

    //添加线程管理并订阅
    private <T> void toSubscribe(Observable<T> o, Subscriber<T> s){
        o.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(s);
    }

    /**
     * 用来统一处理Http的resultCode,并将HttpResult的Data部分剥离出来返回给subscriber
     *
     * @param <T>   Subscriber真正需要的数据类型，也就是Data部分的数据类型
     */
    private class HttpResultFunc<T> implements Func1<DoubanHttpResult<T>, T> {

        @Override
        public T call(DoubanHttpResult<T> httpResult) {
            if (httpResult.getCount() == 0) {
                throw new ApiException(99);
            }
            return httpResult.getSubjects();
        }
    }

}
