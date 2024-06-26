package com.rezaalamsyah.littlelemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.rezaalamsyah.littlelemon.data.local.PrefsRepository
import com.rezaalamsyah.littlelemon.data.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class OnBoardingViewModel(application: Application) : AndroidViewModel(application = application) {
    private val preferenceRepository =
        PrefsRepository.getPrefsRepository(application.applicationContext)

    fun saveUser(user: User) {
        CoroutineScope(context = Dispatchers.IO).launch {
            preferenceRepository.saveUser(user)
        }
    }
}