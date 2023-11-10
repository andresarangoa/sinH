package com.arangoa.logogenia.presentation.navigation.di

import com.arangoa.logogenia.presentation.navigation.RouteNavigator
import com.arangoa.logogenia.presentation.navigation.LogogeniaRouteNavigator
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
class ViewModelModule {

    @Provides
    @ViewModelScoped
    fun bindRouteNavigator(): RouteNavigator = LogogeniaRouteNavigator()
}