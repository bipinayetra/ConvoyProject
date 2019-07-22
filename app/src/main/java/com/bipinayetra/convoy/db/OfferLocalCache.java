package com.bipinayetra.convoy.db;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.sqlite.db.SimpleSQLiteQuery;

import com.bipinayetra.convoy.api.Sort;
import com.bipinayetra.convoy.model.Offer;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


public class OfferLocalCache {

    OfferDao offerDao;
    ExecutorService executors;
    public OfferLocalCache(OfferDao offerDao) {
        this.offerDao = offerDao;
        executors = Executors.newSingleThreadExecutor();
    }

    public DataSource.Factory<Integer, Offer> offersBypickupDate(){
        return offerDao.getOffersOrderByPickupdate();
    }

    public DataSource.Factory<Integer, Offer> offersByDropoffDate(){
        return offerDao.getOffersOrderByDropoffDate();
    }

    public DataSource.Factory<Integer, Offer> offersByPrice(){
        return offerDao.getOffersOrderByPrice();
    }

    public DataSource.Factory<Integer, Offer> offersByOrigin(){
        return offerDao.getOffersOrderByOrigin();
    }

    public DataSource.Factory<Integer, Offer> offersByDestination(){
        return offerDao.getOffersOrderByDestination();
    }

    public DataSource.Factory<Integer, Offer> offersByMiles(){
        return offerDao.getOffersOrderByMiles();
    }


    public void deleteAll(){

        executors.execute(new Runnable() {
            @Override
            public void run() {
                offerDao.nukeTable();
            }
        });

    }


    public void insert(final List<Offer> response) {
        executors.execute(new Runnable() {
            @Override
            public void run() {
                offerDao.insert(response);
            }
        });

    }
}
