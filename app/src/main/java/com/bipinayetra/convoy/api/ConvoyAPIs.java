package com.bipinayetra.convoy.api;

import com.bipinayetra.convoy.model.Offer;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ConvoyAPIs {

    @GET("/offers")
    Observable<List<Offer>> loadOffers(@Query("sort") String sort, @Query("offset") int offset, @Query("limit") int limit);
}
