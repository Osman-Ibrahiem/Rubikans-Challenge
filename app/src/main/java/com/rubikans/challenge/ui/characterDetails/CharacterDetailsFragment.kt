package com.rubikans.challenge.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterDetailsBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.presentation.viewmodel.CharacterDetailViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_details
    override val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()

    @Inject
    lateinit var glide: RequestManager

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getCharacterDetail(args.characterId)

        observeResult()
    }

    private fun observeResult() {
        viewModel.loading.observe(viewLifecycleOwner) { loading: Boolean? ->
            handleLoading(loading == true)
        }

        viewModel.error.observe(viewLifecycleOwner) { throwable: Throwable? ->

        }

        viewModel.character.observe(viewLifecycleOwner) { character: Character? ->
            character ?: return@observe
            viewBinding.apply {
                textViewCharacterName.text = character.fullName
                textViewCharacterEmail.text = character.email
                glide.load(character.avatar).into(imageViewCharacter)
            }
        }
    }

}