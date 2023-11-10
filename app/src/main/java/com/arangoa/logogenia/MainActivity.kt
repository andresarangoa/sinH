package com.arangoa.logogenia

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.arangoa.logogenia.presentation.ui.home.HomeRoute
import com.arangoa.logogenia.presentation.ui.navigation.NavigationMainComponent
import com.arangoa.logogenia.presentation.ui.theme.LogogeniaTheme
import dagger.hilt.android.AndroidEntryPoint

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
