package com.bipinayetra.convoy.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.bipinayetra.convoy.model.Offer;

@Database(entities = {Offer.class}, version = 1, exportSchema = false)
public abstract class OffersDatabase extends RoomDatabase {
    private static OffersDatabase INSTANCE = null;

    public abstract OfferDao offerDao();

    public static synchronized OffersDatabase getInstance(Context context) {

        if(INSTANCE == null){
            OffersDatabase db = Room.databaseBuilder(context.getApplicationContext(),
                    OffersDatabase.class, "Convoy.db").build();
            return db;
        }


        return INSTANCE;
    }

}
