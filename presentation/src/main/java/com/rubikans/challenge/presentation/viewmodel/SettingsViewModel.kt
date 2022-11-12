package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetSettingsUseCase
import com.rubikans.challenge.domain.interactor.SetDarkModeUseCase
import com.rubikans.challenge.domain.model.Settings
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
    private val setDarkModeUseCase: SetDarkModeUseCase,
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _settings = MutableLiveData<List<Settings>>()
    val settings: LiveData<List<Settings>> = _settings

    private val _nightMode = MutableLiveData<Boolean>()
    val nightMode: LiveData<Boolean> = _nightMode

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        _error.postValue(exception)
        _loading.postValue(false)
    }

    fun getSettings() {
        _loading.postValue(true)
        launchCoroutineIO {
            loadSettings()
        }
    }

    private suspend fun loadSettings() {
        getSettingsUseCase(Unit).collect {
            Log.d("Updated list", it.toString())
            _loading.postValue(false)
            _settings.postValue(it)
        }
    }

    fun setSettings(selectedSetting: Settings) {
        selectedSetting.run {
            launchCoroutineIO {
                setDarkMode(selectedValue)
                loadSettings()
            }
            _nightMode.postValue(selectedValue)
        }
        Log.d("SettingsViewModel", selectedSetting.toString())
    }

    private suspend fun setDarkMode(isDark: Boolean) {
        setDarkModeUseCase(isDark)
    }
}