package com.dev.countryapp.di;

import com.dev.countryapp.model.CountriesAPI;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

//Declaration of Code
@Module
public class APIModule {

    public static final String BASE_URL="https://raw.githubusercontent.com/";

    //Creates Bean
    @Provides
    public CountriesAPI provideCountriesAPI(){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(CountriesAPI.class);
    }
}
