package com.example.smart_lock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.smart_lock.ui.theme.Screen.EnterPinCodeScreen
import com.example.smart_lock.ui.theme.Screen.HomeSmartLock
import com.example.smart_lock.ui.theme.Screen.LoginWelcome
import com.example.smart_lock.ui.theme.Screen.PinScreen
import com.example.smart_lock.ui.theme.Screen.Profile
import com.example.smart_lock.ui.theme.Screen.SetPinCodeScreen
import com.example.smart_lock.ui.theme.Screen.SignUp
import com.example.smart_lock.ui.theme.Smart_lockTheme
import com.example.smart_lock.ui.theme.viewModel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



        setContent {
            Smart_lockTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    MyApp()
                }
            }
        }
    }
}


@Composable
private fun providerAuthViewModel(): AuthViewModel {
    return viewModel()
}

@Composable
private fun provinderNavController(): NavController {
    return rememberNavController()
}

@Composable
fun MyApp(authViewModel: AuthViewModel = providerAuthViewModel()) {
    val navController: NavController = provinderNavController()
    val auth1ViewModel = AuthViewModel()
    NavHost(
        navController = navController as NavHostController,
        startDestination = "Login_welcome"
    ) {

        composable("Login_welcome") {
            LoginWelcome(authViewModel = authViewModel,
                navController = navController,
                onSignUpClick = { navController.navigate("Sign_up") }
            )
        }
        composable("Home_screen") {
            HomeSmartLock(navController = navController,
                onProfileClick = { navController.navigate("profileScreen") })
        }
        composable("Sign_up") {
            SignUp(authViewModel = authViewModel, navController = navController)
        }
        composable("pinScreen") {
            PinScreen(navController = navController)
        }

        composable("setPinScreen") {
            SetPinCodeScreen(authViewModel = authViewModel, navController = navController)
        }

        composable("enterPinScreen") {
            EnterPinCodeScreen(authViewModel = authViewModel, navController = navController)
        }

        composable("profileScreen") {
            Profile()
        }
    }
}


