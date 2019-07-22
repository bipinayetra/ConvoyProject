package com.bipinayetra.convoy.ui;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.paging.PagedList;
import androidx.paging.PagedListAdapter;
import androidx.recyclerview.widget.DiffUtil;

import com.bipinayetra.convoy.R;
import com.bipinayetra.convoy.model.Offer;

public class OffersAdapter extends PagedListAdapter<Offer, OfferViewHolder>{


    private PagedList<Offer> offers;

    public OffersAdapter() {
        super(DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public OfferViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.offer_item, parent, false);
        return new OfferViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull OfferViewHolder holder, int position) {
        Offer offer = getItem(position);
        if (offer != null) {
            holder.bindTo(offer);
        }
    }


    public static final DiffUtil.ItemCallback<Offer> DIFF_CALLBACK =
            new DiffUtil.ItemCallback<Offer>() {
                @Override
                public boolean areItemsTheSame(@NonNull Offer oldItem, @NonNull Offer newItem) {
                    return oldItem.equals(newItem);
                }

                @Override
                public boolean areContentsTheSame(@NonNull Offer oldItem, @NonNull Offer newItem) {
                    return oldItem.equals(newItem);
                }
            };


//    public void setdata(PagedList<Offer> offers) {
//        this.offers = offers;
//        this.notifyDataSetChanged();
//    }
}
