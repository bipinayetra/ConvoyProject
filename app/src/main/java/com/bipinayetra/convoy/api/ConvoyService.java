package com.bipinayetra.convoy.api;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.bipinayetra.convoy.db.OfferLocalCache;
import com.bipinayetra.convoy.db.OffersDatabase;
import com.bipinayetra.convoy.model.Offer;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ConvoyService {

    static final String BASE_URL = "https://convoy-frontend-homework-api.herokuapp.com/";
    ConvoyAPIs convoyAPIs;
    private static ConvoyService INSTANCE;

    private ConvoyService() {
        convoyAPIs = createConvoyAPI();
    }

    public static synchronized ConvoyService getInstance(Context context) {

        if (INSTANCE == null) {
            INSTANCE = new ConvoyService();
        }
        return INSTANCE;
    }

    public void serverCall(Observer<List<Offer>> observer) {
        convoyAPIs.loadOffers(Sort.getType(), Sort.getOffset(), Sort.getLimit()).subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(observer);
    }

    private ConvoyAPIs createConvoyAPI() {

        HttpLoggingInterceptor logger = new HttpLoggingInterceptor();
        logger.setLevel(HttpLoggingInterceptor.Level.BASIC);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(logger)
                .build();

        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ConvoyAPIs.class);

    }

}
