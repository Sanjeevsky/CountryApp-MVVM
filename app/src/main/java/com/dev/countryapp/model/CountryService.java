package com.dev.countryapp.model;

import com.dev.countryapp.di.DaggerAPIComponent;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryService {

    public static CountryService instance;
    @Inject
    public CountriesAPI apiInterface;

    private CountryService(){
        //Dagger Dependency Injection
        DaggerAPIComponent.create().inject(this);
    }

    public static CountryService getInstance(){
        if(instance==null){
            instance=new CountryService();
        }
        return instance;
    }

    public Single<List<CountryModel>> getCountries(){
        return apiInterface.getCountries();
    }

}
