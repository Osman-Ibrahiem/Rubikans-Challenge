package com.rubikans.challenge.ui.characterDetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.rubikans.challenge.databinding.FragmentCharacterDetailsBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.extension.showSnackBar
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.CharacterDetailViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class CharacterDetailsFragment : Fragment() {

    private var _binding: FragmentCharacterDetailsBinding? = null
    private val binding get() = _binding!!
    private val args: CharacterDetailsFragmentArgs by navArgs()

    private val viewModel: CharacterDetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentCharacterDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.character, ::onViewStateChange)
        viewModel.getCharacterDetail(args.characterId)
    }

    private fun onViewStateChange(result: Resource<Character>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                result.data?.let {
                    binding.textViewCharacterName.text = it.fullName
                }
            }
            Resource.Status.ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                showSnackBar(binding.rootView, error, true)
            }
            Resource.Status.INIT -> {

            }
            Resource.Status.LOADING -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}