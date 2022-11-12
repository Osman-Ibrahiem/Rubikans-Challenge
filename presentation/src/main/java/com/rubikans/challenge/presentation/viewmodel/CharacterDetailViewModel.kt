package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharacterDetailsUseCase
import com.rubikans.challenge.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _character = MutableLiveData<Character>()
    val character: LiveData<Character> = _character

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharacterDetailVM", exception.message ?: "Error ")
        _error.postValue(exception)
    }

    fun getCharacterDetail(characterId: Int) {
        _loading.postValue(true)
        launchCoroutineIO {
            loadCharacter(characterId)
        }
    }

    private suspend fun loadCharacter(characterId: Int) {
        getCharacterDetailsUseCase(characterId).collect {
            Log.d("CharacterDetailVM", it.toString())
            _loading.postValue(false)
            _character.postValue(it)
        }
    }


}