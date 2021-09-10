package com.dev.countryapp.model;

import java.util.List;

import io.reactivex.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CountryService {

    public static final String BASE_URL="https://raw.githubusercontent.com/";
    public static CountryService instance;
    private APIInterface apiInterface= new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(APIInterface.class);

    private CountryService(){};

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
