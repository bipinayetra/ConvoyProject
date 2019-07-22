package com.bipinayetra.convoy.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.paging.PagedList;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bipinayetra.convoy.R;
import com.bipinayetra.convoy.api.Sort;
import com.bipinayetra.convoy.db.OffersDatabase;
import com.bipinayetra.convoy.model.Offer;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    OffersViewModel viewModel;
    OffersAdapter offersAdapter;
    Spinner dropdown;
    RecyclerView recyclerView;
    Button alertBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupSinner();
        setUpRecycleViewToAdapter();

        setupViewModel();


        viewModelObservesDataManager();

        setButtonForDialog();
    }

    private void setButtonForDialog() {
        alertBtn = findViewById(R.id.alert_btn);
        alertBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setMessage(R.string.alert_confirmation)
                        .setTitle(R.string.alert_title);

                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void setupViewModel() {
        viewModel = ViewModelProviders.of(this).get(OffersViewModel.class);
        //default setting to load on app start
        Sort.setLimit(6);
        viewModel.searchOffers();
    }

    private void setUpRecycleViewToAdapter() {
        recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        offersAdapter = new OffersAdapter();
        recyclerView.setAdapter(offersAdapter);
    }

    private void viewModelObservesDataManager() {
        viewModel.dataManager.data
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.Observer<PagedList<Offer>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(PagedList<Offer> offers) {
                        Log.e("MainActivity","offers ->"+offers);
                        offersAdapter.submitList(offers);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

        viewModel.dataManager.networkErrors.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String t) {
//                Toast.makeText(MainActivity.this, t.getMessage(),Toast.LENGTH_LONG).show();
                Log.e("MainActivity","Throwable message ->"+t);
            }
        });
    }

    private void setupSinner() {
        dropdown = findViewById(R.id.spinner);
        String[] items = new String[]{Sort.pickupDate,Sort.destination,Sort.dropoffDate,Sort.miles,Sort.origin,Sort.price};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();

                if(selectedItem.equals(Sort.pickupDate))
                {
                    Sort.setType(Sort.pickupDate);
                }else if(selectedItem.equals(Sort.destination))
                {
                    Sort.setType(Sort.destination);
                }else if(selectedItem.equals(Sort.dropoffDate))
                {
                    Sort.setType(Sort.dropoffDate);
                }else if(selectedItem.equals(Sort.miles))
                {
                    Sort.setType(Sort.miles);
                }else if(selectedItem.equals(Sort.origin))
                {
                    Sort.setType(Sort.origin);
                }else if(selectedItem.equals(Sort.price))
                {
                    Sort.setType(Sort.price);
                }
                Sort.setOffset(0);
                viewModel.searchOffers();
            }
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

    }
}
