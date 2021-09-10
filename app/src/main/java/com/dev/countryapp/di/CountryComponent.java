package com.dev.countryapp.di;

import com.dev.countryapp.model.CountryService;
import com.dev.countryapp.viewmodel.ListViewModel;

import dagger.Component;

@Component(modules = {CountryModule.class})
public interface CountryComponent {
     void inject(ListViewModel model);
}
