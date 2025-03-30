package com.example.logogenia.presentation.navigation

import android.os.Bundle
import androidx.annotation.VisibleForTesting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


const val KEY_CONTENT_PAGE_INDEX = "CONTENT_PAGE_INDEX"

/**
 * Every screen has a route, so that we don't have to add the route setup of all screens to the [NavigationComponent].
 *
 * Inherits NavRoute, to be able to navigate away from this screen. All navigation logic is in there.
 */



interface RouteNavigator {
    fun onNavigated(state: NavigationState)
    fun navigateUp()
    fun popToRoute(route: String)
    fun navigateToRoute(route: String)
    fun clearBackStack(route: String)

    val navigationState: StateFlow<NavigationState>
    val bundleState: StateFlow<Bundle>
}

class LogogeniaRouteNavigator : RouteNavigator {

    /**
     * Note that I'm using a single state here, not a list of states. As a result, if you quickly
     * update the state multiple times, the view will only receive and handle the latest state,
     * which is fine for my use case.
     */
    override val navigationState: MutableStateFlow<NavigationState> =
        MutableStateFlow(NavigationState.Idle)

    override val bundleState: MutableStateFlow<Bundle> =
        MutableStateFlow(Bundle())

    override fun onNavigated(state: NavigationState) {
        // clear navigation state, if state is the current state:
        navigationState.compareAndSet(state, NavigationState.Idle)
    }

    override fun popToRoute(route: String) = navigate(NavigationState.PopToRoute(route))

    override fun navigateUp() = navigate(NavigationState.NavigateUp())
    override fun navigateToRoute(route: String) = navigate(NavigationState.NavigateToRoute(route))
    override fun clearBackStack(route: String) = navigate(NavigationState.ClearBackStack(route))

    @VisibleForTesting
    fun navigate(state: NavigationState) {
        navigationState.value = state
    }

}

