package com.sbaygildin.rickandmortyexplorer.feature.auth

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class AuthViewModel @Inject constructor(

) : ViewModel() {

    fun login(email: String, password: String, onError: (String) -> Unit) {

    }
}