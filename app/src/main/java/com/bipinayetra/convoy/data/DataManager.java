package com.bipinayetra.convoy.data;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.DataSource;
import androidx.paging.PagedList;
import androidx.paging.RxPagedListBuilder;

import com.bipinayetra.convoy.api.ConvoyService;
import com.bipinayetra.convoy.api.Sort;
import com.bipinayetra.convoy.db.OfferLocalCache;
import com.bipinayetra.convoy.db.OffersDatabase;
import com.bipinayetra.convoy.model.Offer;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DataManager {

    ConvoyService convoyService;
    OfferLocalCache offerLocalCache;
    DataSource.Factory<Integer, Offer> DSfactory;


//    public LiveData<PagedList<Offer>> data;
    public LiveData<String> networkErrors;
    public Observable<PagedList<Offer>> data;


    public DataManager(Application application) {
        OffersDatabase db = OffersDatabase.getInstance(application);
        offerLocalCache = new OfferLocalCache(db.offerDao());
        convoyService = ConvoyService.getInstance(application);
        networkErrors = new MutableLiveData<>();
    }

    public void loadOffers(){


        offerLocalCache.deleteAll();
        OfferBoundaryCallback boundaryCallback = null;
        data = null;

        if(Sort.getType().equals(Sort.pickupDate))
        {
            DSfactory = offerLocalCache.offersBypickupDate();
        }else if(Sort.getType().equals(Sort.dropoffDate))
        {
            DSfactory = offerLocalCache.offersByDropoffDate();
        }else if(Sort.getType().equals(Sort.price))
        {
            DSfactory = offerLocalCache.offersByPrice();
        }else if(Sort.getType().equals(Sort.origin))
        {
            DSfactory = offerLocalCache.offersByOrigin();
        }else if(Sort.getType().equals(Sort.destination))
        {
            DSfactory = offerLocalCache.offersByDestination();
        }else if(Sort.getType().equals(Sort.miles))
        {
            DSfactory = offerLocalCache.offersByMiles();
        }

//        DSfactory = offerLocalCache.offersBypickupDate();

        boundaryCallback = new OfferBoundaryCallback(convoyService,offerLocalCache);



        boundaryCallback._networkErrors.observeForever(new Observer<String>() {
            @Override
            public void onChanged(String t) {
                ((MutableLiveData)networkErrors).postValue(t);
            }
        });

        // Get the paged list
//        data = new LivePagedListBuilder(DSfactory, 10)
//                .setBoundaryCallback(boundaryCallback)
//                .build();


        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(true)
                        .setInitialLoadSizeHint(10)
                        .setPageSize(20).build();

        data = new RxPagedListBuilder(DSfactory, pagedListConfig)
                .setBoundaryCallback(boundaryCallback)
                .setFetchScheduler(Schedulers.io())
                .buildObservable();

    }
}
