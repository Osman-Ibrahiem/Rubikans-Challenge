package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharactersListUseCase
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
class CharactersListViewModel @Inject internal constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : BaseViewModel() {


    private var getCharactersJob: Job? = null
    private val _charactersList = MutableLiveData<List<Character>>()
    val charactersList: LiveData<List<Character>> get() = _charactersList

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            val message = ExceptionHandler.parse(throwable)
        }

    init {
        getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }

    private fun getCharacters() {
        getCharactersJob = launchCoroutine {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getCharactersListUseCase(Unit).collect {
            Log.d("", it.toString())
            _charactersList.value = it.characters
        }
    }
}