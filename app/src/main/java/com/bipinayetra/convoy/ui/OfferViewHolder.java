package com.bipinayetra.convoy.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bipinayetra.convoy.R;
import com.bipinayetra.convoy.model.Offer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class OfferViewHolder extends RecyclerView.ViewHolder {

    public TextView milesView;
    public TextView offerView;
    public TextView requested;
    public TextView origin_location_View;
    public TextView origin_time_View;
    public TextView destination_location_View;
    public TextView destination_time_View;

    public OfferViewHolder(@NonNull View itemView) {
        super(itemView);
        milesView = itemView.findViewById(R.id.miles);
        offerView = itemView.findViewById(R.id.offer);
        requested = itemView.findViewById(R.id.requested);
        origin_location_View = itemView.findViewById(R.id.origin_location);
        origin_time_View = itemView.findViewById(R.id.origin_pickup_time);
        destination_location_View = itemView.findViewById(R.id.destination_location);
        destination_time_View = itemView.findViewById(R.id.destination_time);

    }

    public void bindTo(Offer offer) {

        milesView.setText(String.format("%s miles", String.valueOf(offer.miles)));
        offerView.setText(String.format("$%s", String.valueOf(offer.offer)));

        origin_location_View.setText(String.format("%s,%s", offer.origin.city, offer.origin.state));
        origin_time_View.setText(String.format("%s - %s", ConvertStartDateFormat(offer.origin.pickup.start), ConvertEndDateFormat(offer.origin.pickup.end)));

        destination_location_View.setText(String.format("%s,%s", offer.destination.city, offer.destination.state));
        if(offer.destination.pickup!=null){
            destination_time_View.setText(String.format("%s - %s", ConvertStartDateFormat(offer.destination.pickup.start), ConvertEndDateFormat(offer.destination.pickup.end)));
        }else {
            destination_time_View.setVisibility(View.GONE);
        }

    }

    public String ConvertStartDateFormat(String datetime) {
        String given = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        SimpleDateFormat givenFormat = new SimpleDateFormat(given);
        String start = "EEE MM/dd hh:mm a";
        SimpleDateFormat outputFormat = new SimpleDateFormat(start);

        String send = null;
        try {
            Date date = givenFormat.parse(datetime);
            send = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return send;
    }
    public String ConvertEndDateFormat(String datetime) {
        String given = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
        SimpleDateFormat givenFormat = new SimpleDateFormat(given);
        String start = "hh:mm a";
        SimpleDateFormat outputFormat = new SimpleDateFormat(start);

        String send = null;
        try {
            Date date = givenFormat.parse(datetime);
            send = outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return send;
    }
}
