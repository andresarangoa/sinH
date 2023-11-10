package com.arangoa.logogenia


import androidx.compose.runtime.Composable
import com.arangoa.logogenia.presentation.ui.EmptyScreen

enum class LogogeniaScreen(
    val body: @Composable ((String)-> Unit) -> Unit
) {
    LogogeniaHome(
        body = { EmptyScreen()}
    );

    @Composable
    fun content(onScreenChange: (String) -> Unit) {
        body(onScreenChange)
    }

    companion object {
        fun fromRoute(route: String?): LogogeniaScreen =
            when (route?.substringBefore("/")) {
                LogogeniaHome.name-> LogogeniaHome
                null -> LogogeniaHome
                else -> throw IllegalArgumentException("Route $route is not recognized.")
            }
    }

}