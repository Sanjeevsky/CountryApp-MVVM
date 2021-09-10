package com.dev.countryapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dev.countryapp.model.CountryService;
import com.dev.countryapp.model.CountryModel;

import java.util.List;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class ListViewModel extends ViewModel {
    //Observable livedata that can be mutated
    public MutableLiveData<List<CountryModel>> countries =new MutableLiveData<List<CountryModel>>();
    public MutableLiveData<Boolean> countryLoadError = new MutableLiveData<Boolean>();
    public MutableLiveData<Boolean> loading = new MutableLiveData<Boolean>();
    private CountryService countryService= CountryService.getInstance();

    private CompositeDisposable disposable=new CompositeDisposable();


    public void refresh(){
        fetchCountries();
    }

    private void fetchCountries(){
        loading.setValue(true);

        disposable.add(
                countryService.getCountries()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<CountryModel>>() {
                    @Override
                    public void onSuccess(@NonNull List<CountryModel> countryModels) {
                        countries.setValue(countryModels);
                        loading.setValue(false);
                        countryLoadError.setValue(false);
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        loading.setValue(false);
                        countryLoadError.setValue(true);
                        e.printStackTrace();
                    }
                })
        );


    }

    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
