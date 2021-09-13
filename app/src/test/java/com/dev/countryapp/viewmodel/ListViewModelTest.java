package com.dev.countryapp.viewmodel;

import androidx.annotation.NonNull;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import com.dev.countryapp.model.CountriesAPI;
import com.dev.countryapp.model.CountryModel;
import com.dev.countryapp.model.CountryService;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;

public class ListViewModelTest {

    @Rule
    public InstantTaskExecutorRule executorRule=new InstantTaskExecutorRule();

    @Mock
    CountryService countryService;

    @InjectMocks
    ListViewModel listViewModel=new ListViewModel();

    private Single<List<CountryModel>> testSingle;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }

    public void tearDown() {
    }

    @Test
    public void getCountriesSuccess(){
        CountryModel countryModel=new CountryModel("dsaijpda","fdsjhifsa","");
        ArrayList<CountryModel> list=new ArrayList<>();
        list.add(countryModel);
        testSingle=Single.just(list);

        Mockito.when(countryService.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();

        Assert.assertEquals(1,listViewModel.countries.getValue().size());
        Assert.assertEquals(false,listViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,listViewModel.loading.getValue());
    }

    @Test
    public void getCountriesError(){
        CountryModel countryModel=new CountryModel("dsaijpda","fdsjhifsa","");
        ArrayList<CountryModel> list=new ArrayList<>();
        list.add(countryModel);
        testSingle=Single.error(new Throwable());

        Mockito.when(countryService.getCountries()).thenReturn(testSingle);
        listViewModel.refresh();

        Assert.assertEquals(true,listViewModel.countryLoadError.getValue());
        Assert.assertEquals(false,listViewModel.loading.getValue());
    }

    @Before
    public void setUpRxSchedulers(){
        Scheduler immediate =new Scheduler() {
            @NonNull
            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run,true);
            }
        };
        RxJavaPlugins.setInitNewThreadSchedulerHandler(schedulerCallable -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(schedulerCallable -> immediate);
    }
}