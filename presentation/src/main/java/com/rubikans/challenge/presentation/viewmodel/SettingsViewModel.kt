package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetSettingsUseCase
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import com.rubikans.challenge.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getSettingsUseCase: GetSettingsUseCase,
) : BaseViewModel() {

    private val _settings = MutableLiveData<Resource<List<Settings>>>()
    val settings: LiveData<Resource<List<Settings>>> = _settings

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        val message = ExceptionHandler.parse(exception)
        _settings.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getSettings() {
        _settings.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getSettingsUseCase(Unit).collect {
            _settings.postValue(Resource.success(it))
        }
    }

    fun setSettings(selectedSetting: Settings) {
        Log.d("SettingsViewModel", selectedSetting.toString())
    }
}