package com.dev.countryapp.di;
//Connection between Module and Service using (@Provides)

import com.dev.countryapp.model.CountryService;

import dagger.Component;
import dagger.Module;

@Component(modules={APIModule.class})
public interface APIComponent {
    //Where to inject
    void inject(CountryService service);
}
