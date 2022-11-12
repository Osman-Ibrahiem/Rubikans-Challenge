package com.rubikans.challenge.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.RequestManager
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterDetailsBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.CharacterDetailViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
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
        observe(viewModel.character, ::onViewStateChange)
        viewModel.getCharacterDetail(args.characterId)
    }

    private fun onViewStateChange(result: Resource<Character>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                handleLoading(false)
                result.data?.let {
                    viewBinding.apply {
                        textViewCharacterName.text = it.fullName
                        textViewCharacterEmail.text = it.email
                        glide.load(it.avatar).into(imageViewCharacter)
                    }
                }
            }
            Resource.Status.ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                handleErrorMessage(error)
            }
            Resource.Status.INIT -> {

            }
            Resource.Status.LOADING -> {
                handleLoading(true)
            }
        }
    }

}