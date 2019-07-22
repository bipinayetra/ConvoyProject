package com.bipinayetra.convoy.model;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

public class ConvoyAPIResult {
    public ConvoyAPIResult(LiveData<PagedList<Offer>> data, LiveData<String> networkErrors) {
        this.data = data;
        this.networkErrors = networkErrors;
    }

    LiveData<PagedList<Offer>> data;
    LiveData<String> networkErrors;

}
