package com.rubikans.challenge.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.rubikans.challenge.domain.interactor.GetCharactersListUseCase
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.domain.model.CharactersList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import javax.inject.Inject


@HiltViewModel
class CharactersListViewModel @Inject internal constructor(
    private val getCharactersListUseCase: GetCharactersListUseCase,
) : BaseViewModel() {

    private val _loading = MutableLiveData<Boolean>(false)
    val loading: LiveData<Boolean> = _loading
    val isLoading: Boolean get() = loading.value == true

    private val _error = MutableLiveData<Throwable>()
    val error: LiveData<Throwable> = _error

    private val _result = MutableLiveData<CharactersList>()
    val result: LiveData<CharactersList> = _result

    private val _characterList = MutableLiveData<List<Character>>(ArrayList())
    val characterList: LiveData<List<Character>> = _characterList

    var pageNum: Int = 1
    var hasMore: Boolean = true

    override val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        Log.d("CharactersListVM", exception.message ?: "Error ")
        _error.postValue(exception)
    }

    init {
        pageNum = 1
        getCharacters(pageNum)
    }

    fun nextPage() {
        pageNum += 1
        if (hasMore) {
            getCharacters(pageNum)
        }
    }

    fun getCharacters(page: Int) {
        _loading.postValue(true)
        launchCoroutineIO {
            loadCharacters(page)
        }
    }

    private suspend fun loadCharacters(page: Int) {
        getCharactersListUseCase(page).collect {
            Log.d("CharactersListVM", it.toString())
            _loading.postValue(false)
            _result.postValue(it)
            _characterList.postValue((characterList.value ?: ArrayList()) + it.characters)
            hasMore = it.page < it.totalPages
        }
    }
}