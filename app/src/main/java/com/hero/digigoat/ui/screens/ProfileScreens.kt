package com.hero.digigoat.ui.screens

import android.app.Activity
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.compose.ui.platform.LocalContext
import com.hero.digigoat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen(navController: NavHostController) {
    val context = LocalContext.current // Fetch context once

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        // Header
        TopAppBar(
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color(0xFF4B3300),
                titleContentColor = Color.White
            ),
            modifier = Modifier.height(80.dp),
            title = {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.logo),
                        contentDescription = "Logo",
                        modifier = Modifier.size(100.dp)
                    )
                    Spacer(modifier = Modifier.width(18.dp))
                }
            },
            actions = {
                IconButton(
                    onClick = { /* TODO: Handle Notification Click */ },
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .offset(y = 15.dp)
                        .padding(end = 16.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.notifikasi),
                        contentDescription = "Notification",
                        tint = Color.White,
                        modifier = Modifier.size(22.dp)
                    )
                }
            }
        )

        // Profile Options
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            ProfileOptionItem(
                title = "Edit Profile",
                iconId = R.drawable.user,
                onClick = { navController.navigate("profile") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileOptionItem(
                title = "Pemulihan Data",
                iconId = R.drawable.backup,
                onClick = { navController.navigate("data_recovery") }
            )
            Spacer(modifier = Modifier.height(16.dp))

            ProfileOptionItem(
                title = "Keluar",
                iconId = R.drawable.logout,
                onClick = {
                    // Handle Logout by exiting the app
                    val activity = context as? Activity
                    activity?.finish() // This will close the app
                }
            )
        }

        Spacer(modifier = Modifier.weight(1f)) // Spacer to push Footer to the bottom
        FooterNavigationBar(navController = navController) // Passing navController to FooterNavigationBar
    }
}

@Composable
fun ProfileOptionItem(title: String, iconId: Int, onClick: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
            .clickable { onClick() }
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.width(20.dp))
        Text(
            text = title,
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = Color.Black
        )
    }
}

@Composable
fun FooterNavigationBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color(0xFF4B3300))
            .padding(vertical = 12.dp),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FooterItem(iconId = R.drawable.home, title = "Beranda", onClick = { navController.navigate("home") })
        FooterItem(iconId = R.drawable.manajemen, title = "Manajemen", onClick = { navController.navigate("management") })
        FooterItem(iconId = R.drawable.berita, title = "Berita", onClick = { navController.navigate("news") })
        FooterItem(iconId = R.drawable.profil, title = "Profile", selected = true, onClick = { navController.navigate("profile") })
    }
}

@Composable
fun FooterItem(iconId: Int, title: String, selected: Boolean = false, onClick: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .clickable { onClick() } // Handle Footer Navigation Click
            .padding(4.dp)
    ) {
        Icon(
            painter = painterResource(id = iconId),
            contentDescription = title,
            tint = if (selected) Color(0xFFFBC02D) else Color.White,
            modifier = Modifier.size(30.dp)
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = title,
            fontSize = 14.sp,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal,
            color = if (selected) Color(0xFFFBC02D) else Color.White
        )
    }
}
