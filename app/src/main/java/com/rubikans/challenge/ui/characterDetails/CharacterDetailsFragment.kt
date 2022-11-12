package com.rubikans.challenge.ui.characterDetails

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.rubikans.challenge.R
import com.rubikans.challenge.databinding.FragmentCharacterDetailsBinding
import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.presentation.viewmodel.CharacterDetailViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CharacterDetailsFragment :
    BaseFragment<FragmentCharacterDetailsBinding, CharacterDetailViewModel>() {

    override val layoutId: Int = R.layout.fragment_character_details
    override val viewModel: CharacterDetailViewModel by viewModels()
    private val args: CharacterDetailsFragmentArgs by navArgs()

    private var isEditMode: Boolean = false
        set(value) {
            field = value
            viewBinding.rootView.post {
                viewBinding.expandedDetails.isExpanded = !value
                viewBinding.expandedEdit.isExpanded = value
                viewBinding.btnEdit.setImageResource(if (value) R.drawable.ic_done else R.drawable.ic_edit)
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isEditMode = false
        viewModel.getCharacterDetail(args.characterId)

        observeResult()
        viewBinding.btnEdit.setOnClickListener {
            if (isEditMode) {
                val newCharacter = viewBinding.item ?: Character(
                    id = args.characterId,
                    firstName = viewBinding.textInputEditTextFirstName.text?.toString() ?: "",
                    lastName = viewBinding.textInputEditTextLastName.text?.toString() ?: "",
                    email = viewBinding.textInputEditTextEmail.text?.toString() ?: "",
                )
                viewModel.updateCharacter(newCharacter)
            }
            isEditMode = !isEditMode
        }
    }

    private fun observeResult() {
        viewModel.loading.observe(viewLifecycleOwner) { loading: Boolean? ->
            handleLoading(loading == true)
        }

        viewModel.error.observe(viewLifecycleOwner) { throwable: Throwable? ->

        }

        viewModel.character.observe(viewLifecycleOwner) { character: Character? ->
            character ?: return@observe
            viewBinding.item = character
        }
    }

}