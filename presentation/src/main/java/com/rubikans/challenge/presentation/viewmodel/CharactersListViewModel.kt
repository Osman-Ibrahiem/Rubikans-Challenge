package com.rubikans.challenge.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharactersListUseCase
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Job
import javax.inject.Inject


@HiltViewModel
class CharactersListViewModel @Inject internal constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : BaseViewModel<CharacterState>() {

    private var state: CharacterState = CharacterState.Init
        private set(value) {
            field = value
            publishState(value)
        }

    override val stateObservable: MutableLiveData<CharacterState> by lazy {
        MutableLiveData<CharacterState>()
    }

    private var getCharactersJob: Job? = null

    override val coroutineExceptionHandler: CoroutineExceptionHandler
        get() = CoroutineExceptionHandler { _, throwable ->
            val message = ExceptionHandler.parse(throwable)
            state = CharacterState.Error(message)
        }

    init {
        getCharacters()
    }

    override fun onCleared() {
        super.onCleared()
        getCharactersJob?.cancel()
    }

    private fun getCharacters() {
        state = CharacterState.Loading
        getCharactersJob = launchCoroutine {
            loadCharacters()
        }
    }

    private suspend fun loadCharacters() {
        getCharactersListUseCase(Unit).collect { charactersList ->
            state = CharacterState.Success(charactersList)
        }
    }
}