package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharacterDetailsUseCase
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import com.rubikans.challenge.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


@HiltViewModel
class CharacterDetailViewModel @Inject constructor(
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
) : BaseViewModel() {

    private val _character = MutableLiveData<Resource<Character>>()
    val character: LiveData<Resource<Character>> = _character

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharacterDetailVM", exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        _character.postValue(Resource.error(exception.message ?: "Error"))
    }

    fun getCharacterDetail(characterId: Int) {
        _character.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacter(characterId)
        }
    }

    private suspend fun loadCharacter(characterId: Int) {
        getCharacterDetailsUseCase(characterId).collect {
            Log.d("CharacterDetailVM", it.toString())
            _character.postValue(Resource.success(it))
        }
    }


}