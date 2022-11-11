package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharactersListUseCase
import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.presentation.utils.ExceptionHandler
import com.rubikans.challenge.presentation.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


@HiltViewModel
class CharactersListViewModel @Inject internal constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : BaseViewModel() {

    private val _characterList = MutableLiveData<Resource<CharactersList>>()
    val characterList: LiveData<Resource<CharactersList>> = _characterList

    var pageNum: Int = 1

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharactersListVM", exception.message ?: "Error ")
        val message = ExceptionHandler.parse(exception)
        _characterList.postValue(Resource.error(exception.message ?: "Error"))
    }

    init {
        pageNum = 1
        getCharacters(pageNum)
    }

    fun getCharacters(page: Int) {
        _characterList.postValue(Resource.loading(null))
        launchCoroutineIO {
            loadCharacters(page)
        }
    }

    private suspend fun loadCharacters(page: Int) {
        getCharactersListUseCase(page).collect {
            Log.d("CharactersListVM", it.toString())
            _characterList.postValue(Resource.success(it))
        }
    }
}