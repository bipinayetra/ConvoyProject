package com.bipinayetra.convoy.db;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.RawQuery;
import androidx.sqlite.db.SupportSQLiteQuery;


import com.bipinayetra.convoy.model.Offer;

import java.util.List;

@Dao
public interface OfferDao {

    @Insert
    void insert(List<Offer> offers);

    @Query("SELECT * FROM offers")
    DataSource.Factory<Integer, Offer> getAllOffers();

    @Query("SELECT * FROM offers ORDER BY origin_start ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByPickupdate();

    @Query("SELECT * FROM offers ORDER BY destination_start ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByDropoffDate();

    @Query("SELECT * FROM offers ORDER BY offer ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByPrice();

    @Query("SELECT * FROM offers ORDER BY origin_city ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByOrigin();

    @Query("SELECT * FROM offers ORDER BY destination_city ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByDestination();

    @Query("SELECT * FROM offers ORDER BY miles ASC")
    DataSource.Factory<Integer, Offer> getOffersOrderByMiles();

    @Query("DELETE FROM offers")
    public void nukeTable();

}
