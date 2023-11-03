package com.example.logogenia

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.StatsLog.logEvent
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.example.logogenia.presentation.ui.home.HomeRoute
import com.example.logogenia.presentation.ui.navigation.NavigationMainComponent
import com.example.logogenia.presentation.ui.theme.LogogeniaTheme
import dagger.hilt.android.AndroidEntryPoint
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.analytics.ktx.logEvent
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        //val userStatus = bundle!!.getString("UserStatus")


        var startDestination = HomeRoute.route
        setContent {
            val navController = rememberNavController()
            LogogeniaTheme() {
                Scaffold {
                    NavigationMainComponent().navigationComponent(
                        navController, it, startDestination
                    )
                }
            }
        }
    }

}
