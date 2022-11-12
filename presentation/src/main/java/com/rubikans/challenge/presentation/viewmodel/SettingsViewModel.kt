package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetSettingsUseCase
import com.rubikans.challenge.domain.interactor.SetDarkModeUseCase
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import com.rubikans.challenge.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
) : BaseViewModel() {

    private val _settings = MutableLiveData<Resource<List<Settings>>>()
    val settings: LiveData<Resource<List<Settings>>> = _settings

    private val _nightMode = MutableLiveData<Resource<Boolean>>()
    val nightMode: LiveData<Resource<Boolean>> = _nightMode

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _settings.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getSettings() {
        _settings.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadSettings()
        }
    }

    private suspend fun loadSettings() {
        getSettingsUseCase(Unit).collect {
            Log.d("Updated list", it.toString())
            _settings.postValue(Resource.success(it))
        }
    }

    fun setSettings(selectedSetting: Settings) {
        selectedSetting.run {
            launchCoroutineIO {
                setDarkMode(selectedValue)
                loadSettings()
            }
            _nightMode.postValue(Resource.success(selectedValue))
        }
        Log.d("SettingsViewModel", selectedSetting.toString())
    }

    private suspend fun setDarkMode(isDark: Boolean) {
        setDarkModeUseCase(isDark)
    }
}