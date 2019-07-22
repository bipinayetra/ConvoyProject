package com.bipinayetra.convoy.ui;

import android.app.Application;

import androidx.arch.core.util.Function;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.bipinayetra.convoy.data.DataManager;
import com.bipinayetra.convoy.db.OfferLocalCache;
import com.bipinayetra.convoy.db.OffersDatabase;
import com.bipinayetra.convoy.model.Offer;

public class OffersViewModel extends AndroidViewModel {


    DataManager dataManager;

    public OffersViewModel(Application application) {
        super(application);
        dataManager = new DataManager(application);

    }

    public void searchOffers(){
        dataManager.loadOffers();
    }
}
