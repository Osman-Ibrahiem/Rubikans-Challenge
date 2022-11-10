package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import com.rubikans.challenge.domain.interactor.GetCharactersUseCase
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
internal class CharactersViewModel @Inject internal constructor(
    private val getCharactersUseCase: GetCharactersUseCase,
) : BaseViewModel() {


    private var getCharactersJob: Job? = null

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
        getCharactersUseCase(Unit).collect {
            Log.d("", it.toString())
        }
    }
}