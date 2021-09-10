package com.dev.countryapp.di;

import com.dev.countryapp.model.CountryService;

import dagger.Module;
import dagger.Provides;

@Module
public class CountryModule {

    @Provides
    public CountryService provideCountryService(){
        return CountryService.getInstance();
    }
}
