package com.example.logogenia.presentation.navigation.di


import com.example.logogenia.presentation.navigation.RouteNavigator
import com.example.logogenia.presentation.navigation.LogogeniaRouteNavigator
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