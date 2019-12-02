package com.example.medicanet.ui.main;

import com.example.medicanet.ui.detail.DetailsFragment;
import com.example.medicanet.ui.list.ListFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
