package com.rubikans.challenge.ui.characterslist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.databinding.FragmentCharacterListBinding
import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.extension.showSnackBar
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.CharactersListViewModel
import com.rubikans.challenge.ui.adapters.CharacterAdapter
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterListFragment : Fragment() {

    private var _binding: FragmentCharacterListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CharactersListViewModel by viewModels()

    @Inject
    lateinit var characterAdapter: CharacterAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.characterList, ::onViewStateChange)
        setupRecyclerView()
        characterAdapter.setItemClickListener { character ->
            findNavController().navigate(CharacterListFragmentDirections.actionToCharacterDetails(
                characterId = character.id
            ))
        }
    }

    private fun setupRecyclerView() = binding.recyclerViewCharacters.apply {
        adapter = characterAdapter
        layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onViewStateChange(result: Resource<CharactersList>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                binding.recyclerViewCharacters.isVisible = true
                binding.progressBarCharacters.isVisible = false
                characterAdapter.list = result.data?.characters ?: ArrayList()
            }
            Resource.Status.ERROR -> {
                showSnackBar(binding.rootView, result.message ?: "", true)
                binding.recyclerViewCharacters.isVisible = false
                binding.progressBarCharacters.isVisible = false
            }
            Resource.Status.INIT -> {
                binding.recyclerViewCharacters.isVisible = false
                binding.progressBarCharacters.isVisible = false
            }
            Resource.Status.LOADING -> {
                binding.recyclerViewCharacters.isVisible = false
                binding.progressBarCharacters.isVisible = true
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}