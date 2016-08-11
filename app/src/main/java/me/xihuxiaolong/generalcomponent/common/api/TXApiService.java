package me.xihuxiaolong.generalcomponent.common.api;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.xihuxiaolong.generalcomponent.common.model.City;
import me.xihuxiaolong.generalcomponent.common.model.TXHttpResult;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
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
public class TXApiService implements ITXApiService {

    public static final String BASE_URL = "http://apis.map.qq.com/";

    ITxApiService iTxApiService;

    public TXApiService(){
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .readTimeout(60 * 1000, TimeUnit.MILLISECONDS)
                .addInterceptor(new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Request request = chain.request();
                        HttpUrl url = request.url().newBuilder().addQueryParameter("key","GEWBZ-PRPWU-ERHV3-4PMRB-ZL6FH-LOBGB").build();
                        request = request.newBuilder().url(url).build();
                        return chain.proceed(request);
                    }
                })
                .build();
        Retrofit retrofit= new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        iTxApiService = retrofit.create(ITxApiService.class);
    }

    @Override
    public void getCityList(Subscriber<List<List<City>>> subscriber){
        Observable<List<List<City>>> observable = iTxApiService.getCityList()
                .map(new HttpResultFunc<List<List<City>> >());
        toSubscribe(observable, subscriber);
    }


    public interface ITxApiService {

        @GET("ws/district/v1/list")
        Observable<TXHttpResult<List<List<City>>> > getCityList();

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
    private class HttpResultFunc<T> implements Func1<TXHttpResult<T>, T> {

        @Override
        public T call(TXHttpResult<T> httpResult) {
            if (httpResult.getStatus() != 0) {
                throw new TXApiException(httpResult.getMessage());
            }
            return httpResult.getResult();
        }
    }

}
