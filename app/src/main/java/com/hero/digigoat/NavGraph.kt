package com.hero.digigoat

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hero.digigoat.ui.screens.DataRecoveryScreen
import com.hero.digigoat.ui.screens.EditProfileScreen
import com.hero.digigoat.ui.screens.Profile
import com.hero.digigoat.ui.screens.ProfileScreen
import com.hero.digigoat.ui.screens.ProfileViewModel

@Composable
fun NavGraph(navController: NavHostController, profileViewModel: ProfileViewModel) {
    NavHost(
        navController = navController,
        startDestination = "profile"
    ) {
        composable("profile") { ProfileScreen(navController) }
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