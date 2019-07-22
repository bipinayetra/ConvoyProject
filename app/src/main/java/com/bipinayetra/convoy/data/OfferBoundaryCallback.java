package com.bipinayetra.convoy.data;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.arch.core.util.Function;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.paging.PagedList;

import com.bipinayetra.convoy.api.ConvoyAPIs;
import com.bipinayetra.convoy.api.ConvoyService;
import com.bipinayetra.convoy.api.Sort;
import com.bipinayetra.convoy.db.OfferLocalCache;
import com.bipinayetra.convoy.model.Offer;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OfferBoundaryCallback extends PagedList.BoundaryCallback<Offer> {


    public MutableLiveData _networkErrors;

    ConvoyService convoyService;
    OfferLocalCache offerLocalCache;

    boolean isRequestInProgress = false;


    public OfferBoundaryCallback( ConvoyService convoyService, OfferLocalCache offerLocalCache) {
        this.convoyService = convoyService;
        this.offerLocalCache = offerLocalCache;

        _networkErrors = new MutableLiveData();
    }

    @Override
    public void onZeroItemsLoaded() {
        Log.d("OfferBoundaryCallback","onZeroItemsLoaded ");
        requestAndSaveData();
    }

    @Override
    public void onItemAtEndLoaded(@NonNull Offer itemAtEnd) {
        Log.d("OfferBoundaryCallback","onItemAtEndLoaded "+itemAtEnd);
        Sort.setOffset(Sort.getOffset()+Sort.getLimit());
        requestAndSaveData();
    }

    private void requestAndSaveData(){

        if(isRequestInProgress){
            Log.d("BoundaryCallback", "Request is In Progress ... current request ignored");
            return;
        }
        isRequestInProgress = true;


        convoyService.serverCall(new Observer<List<Offer>>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d("BoundaryCallback", "onSubscribe -"+d);
            }

            @Override
            public void onNext(List<Offer> offers) {
                Log.d("BoundaryCallback", "OnNext -"+offers);
                Log.d("BoundaryCallback", "OnNext is on THread"+Thread.currentThread().getName());
                offerLocalCache.insert(offers);

            }

            @Override
            public void onError(Throwable e) {
                Log.d("BoundaryCallback", "onError -"+e);
                _networkErrors.postValue(e.getMessage());
                isRequestInProgress = false;
            }

            @Override
            public void onComplete() {
                Log.d("BoundaryCallback", "onComplete");
                isRequestInProgress = false;

            }
        });
    }
}
