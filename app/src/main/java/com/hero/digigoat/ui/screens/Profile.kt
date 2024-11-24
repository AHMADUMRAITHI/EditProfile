package com.hero.digigoat.ui.screens

import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.hero.digigoat.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Profile(
    navController: NavHostController,
    paddingValues: PaddingValues,
    profileImageUri: Uri?,
    name: String,
    email: String,
    password: String,
    birthDate: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = "PROFIL",
                            color = Color.White,
                            fontSize = 16.sp,
                            fontWeight = FontWeight.SemiBold,
                            textAlign = TextAlign.Center
                        )
                        Spacer(modifier = Modifier.weight(1.5f))
                    }
                },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.back),
                            contentDescription = "Back",
                            tint = Color.White
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color(0xFF4B3300)
                )
            )
        },
        content = { innerPadding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    ProfilePicture(profileImageUri)
                }
                Spacer(modifier = Modifier.height(16.dp))

                BoxReadOnly(label = "Nama", value = name)
                BoxReadOnly(label = "Email", value = email)

                // Password field now becomes read-only
                PasswordField(label = "Password", initialValue = password, isEditable = false)

                BoxReadOnly(label = "Tanggal Lahir", value = birthDate)

                Spacer(modifier = Modifier.height(40.dp))

                Box(
                    modifier = Modifier.fillMaxWidth(),
                    contentAlignment = Alignment.Center
                ) {
                    Button(
                        onClick = { navController.navigate("edit_profile") },
                        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF7B5E00)),
                        modifier = Modifier.width(180.dp)
                    ) {
                        Text(text = "Edit", color = Color.White)
                    }
                }
            }
        }
    )
}

@Composable
fun ProfilePicture(profileImageUri: Uri?) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .size(80.dp) // Ukuran lebih kecil
            .clip(CircleShape)
            .background(Color.LightGray)
    ) {
        if (profileImageUri != null) {
            Image(
                painter = rememberAsyncImagePainter(model = profileImageUri),
                contentDescription = "Selected Profile Picture",
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                contentScale = ContentScale.Crop
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.icon_profile),
                contentDescription = "Profile Picture",
                modifier = Modifier.size(40.dp), // Ikon lebih kecil
                tint = Color.Gray
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PasswordField(label: String, initialValue: String, isEditable: Boolean = true) {
    var password by remember { mutableStateOf(initialValue) }
    var isPasswordVisible by remember { mutableStateOf(false) }

    Text(
        text = label,
        fontSize = 14.sp, // Ukuran font lebih kecil
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(5.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp)
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        if (isEditable) {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                trailingIcon = {
                    IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {
                        Icon(
                            imageVector = if (isPasswordVisible) Icons.Filled.VisibilityOff else Icons.Filled.Visibility,
                            contentDescription = null,
                            modifier = Modifier.size(20.dp),
                            tint = Color.Gray
                        )
                    }
                },
                singleLine = true,
                textStyle = LocalTextStyle.current.copy(fontSize = 14.sp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    containerColor = Color.Transparent,
                    focusedBorderColor = Color.Transparent,
                    unfocusedBorderColor = Color.Transparent
                )
            )
        } else {
            Text(
                text = password,
                modifier = Modifier.padding(start = 10.dp),
                fontSize = 14.sp,
                color = Color.Black
            )
        }
    }
    Spacer(modifier = Modifier.height(15.dp))
}

@Composable
fun BoxReadOnly(label: String, value: String) {
    Text(
        text = label,
        fontSize = 14.sp, // Ukuran font lebih kecil
        fontWeight = FontWeight.Bold,
        color = Color.Black
    )
    Spacer(modifier = Modifier.height(5.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(45.dp) // Tinggi sedikit lebih kecil
            .background(Color.LightGray, RoundedCornerShape(8.dp)),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = value,
            modifier = Modifier.padding(start = 10.dp),
            fontSize = 14.sp,
            color = Color.Black
        )
    }
    Spacer(modifier = Modifier.height(15.dp))
}

