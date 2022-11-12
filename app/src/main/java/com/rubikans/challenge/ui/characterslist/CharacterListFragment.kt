package com.rubikans.challenge.ui.characterslist

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterListBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.presentation.viewmodel.CharactersListViewModel
import com.rubikans.challenge.ui.characterslist.adapters.CharacterAdapter
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

        initRecyclerView()
        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionToCharacterDetails(
                characterId = character.id
            ))
        }

        observeResults()
    }

    private fun initRecyclerView() = viewBinding.recyclerViewCharacters.apply {
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun observeResults() {
        viewModel.loading.observe(viewLifecycleOwner) { loading: Boolean? ->
            handleLoading(loading == true)
        }

        viewModel.error.observe(viewLifecycleOwner) { throwable: Throwable? ->
            throwable ?: return@observe

            val error = throwable.message ?: "Error"
            Timber.e(error)
            handleErrorMessage(error)
            viewBinding.recyclerViewCharacters.isVisible = false
        }

        viewModel.characterList.observe(viewLifecycleOwner) { characters: List<Character>? ->
            viewBinding.recyclerViewCharacters.isVisible = true
            characterAdapter.list = characters ?: ArrayList()
        }
    }

}