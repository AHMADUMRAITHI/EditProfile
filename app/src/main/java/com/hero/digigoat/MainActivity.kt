package com.hero.digigoat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hero.digigoat.ui.screens.DataRecoveryScreen
import com.hero.digigoat.ui.screens.EditProfileScreen
import com.hero.digigoat.ui.screens.Profile
import com.hero.digigoat.ui.screens.ProfileScreen
import com.hero.digigoat.ui.screens.ProfileViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainApp()
        }
    }
}

@Composable
fun MainApp() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "profile_screen"
    ) {
        composable("profile_screen") { ProfileScreen(navController) }
        composable("data_recovery") { DataRecoveryScreen(navController) }
        composable("edit_profile") { EditProfileScreen(navController)}
        composable("profile") {
            Profile(
                navController = navController,
                paddingValues = PaddingValues(0.dp),
                profileImageUri = null,
                name = "Rafish Madeta",
                email = "rafish@gmail.com",
                password = "**********************",
                birthDate = "01/01/2000"
            ) }
    }
}

