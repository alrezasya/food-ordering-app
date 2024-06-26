package com.rezaalamsyah.littlelemon.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.rezaalamsyah.littlelemon.data.model.MenuItemDb
import com.rezaalamsyah.littlelemon.data.network.AppRepository
import com.rezaalamsyah.littlelemon.utils.ApiResult
import com.rezaalamsyah.littlelemon.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class HomeViewModel(application: Application) : AndroidViewModel(application = application) {
    private val appRepository = AppRepository.getInstance(
        application.applicationContext
    )

    private val _menuData = MutableStateFlow<ApiResult<List<MenuItemDb>>>(ApiResult.Loading)
    val menuData: StateFlow<ApiResult<List<MenuItemDb>>> = _menuData

    init {
        getData()
    }

    fun getData() {
        appRepository.getMenuData()
            .asResult()
            .onEach { _menuData.value = it }
            .launchIn(viewModelScope)
    }

}