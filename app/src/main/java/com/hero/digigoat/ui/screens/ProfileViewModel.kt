package com.hero.digigoat.ui.screens

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import android.net.Uri


class UserProfileViewModel : ViewModel() {
    // Using mutableStateOf to hold the profile image URI
    private val _profileImageUri = mutableStateOf<Uri?>(null)
    val profileImageUri: State<Uri?> = _profileImageUri

    // Function to set the profile image URI
    fun setProfileImage(uri: Uri?) {
        _profileImageUri.value = uri
    }
}
