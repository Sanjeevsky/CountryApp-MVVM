package com.dev.countryapp.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev.countryapp.R;
import com.dev.countryapp.model.CountryModel;
import com.dev.countryapp.view.adapter.CountryListAdapter;
import com.dev.countryapp.viewmodel.ListViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.swipeRefreshLayout)
    SwipeRefreshLayout refreshLayout;
    @BindView(R.id.countriesList)
    RecyclerView countriesList;
    @BindView(R.id.list_error)
    TextView listError;
    @BindView(R.id.loading_view)
    ProgressBar loadingView;

    private ListViewModel viewModel;
    private CountryListAdapter countryListAdapter=new CountryListAdapter(new ArrayList<>());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        viewModel= ViewModelProviders.of(this).get(ListViewModel.class);
        viewModel.refresh();
        countriesList.setLayoutManager(new LinearLayoutManager(this));
        countriesList.setAdapter(countryListAdapter);
        observeViewModel();
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.refresh();
                refreshLayout.setRefreshing(false);
            }
        });

    }

    private void observeViewModel() {
        viewModel.countries.observe(this, new Observer<List<CountryModel>>() {
            @Override
            public void onChanged(List<CountryModel> countryModels) {
                if(countryModels!=null){
                    countriesList.setVisibility(View.VISIBLE);
                    countryListAdapter.updateCountries(countryModels);
                }
            }
        });

        viewModel.countryLoadError.observe(this, isError -> {
            if(isError!=null){
                listError.setVisibility(isError? View.VISIBLE: View.GONE);
            }
        });

        viewModel.loading.observe(this, loading -> {
            if(loading!=null){
                loadingView.setVisibility(loading ? View.VISIBLE : View.GONE);

                if(loading){
                    countriesList.setVisibility(View.GONE);
                    listError.setVisibility(View.GONE);
                }
            }
        });
    }
}