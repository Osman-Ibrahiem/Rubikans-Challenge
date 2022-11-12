package com.rubikans.challenge.ui.characterslist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterListBinding
import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.CharactersListViewModel
import com.rubikans.challenge.ui.adapters.CharacterAdapter
import com.rubikans.challenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment :
    BaseFragment<FragmentCharacterListBinding, CharactersListViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_list
    override val viewModel: CharactersListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.characterList, ::onViewStateChange)
        initRecyclerView()
        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionToCharacterDetails(
                characterId = character.id
            ))
        }
    }

    private fun initRecyclerView() = viewBinding.recyclerViewCharacters.apply {
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onViewStateChange(result: Resource<CharactersList>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                handleLoading(false)
                viewBinding.recyclerViewCharacters.isVisible = true
                characterAdapter.list = result.data?.characters ?: ArrayList()
            }
            Resource.Status.ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                handleErrorMessage(error)
                viewBinding.recyclerViewCharacters.isVisible = false
            }
            Resource.Status.INIT -> {
                viewBinding.recyclerViewCharacters.isVisible = false
                handleLoading(false)
            }
            Resource.Status.LOADING -> {
                viewBinding.recyclerViewCharacters.isVisible = false
                handleLoading(true)
            }
        }
    }

}