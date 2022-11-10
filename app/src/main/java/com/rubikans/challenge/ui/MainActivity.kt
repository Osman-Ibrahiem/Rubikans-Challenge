package com.rubikans.challenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.rubikans.challenge.databinding.ActivityMainBinding
import com.rubikans.challenge.presentation.viewmodel.CharacterState
import com.rubikans.challenge.presentation.viewmodel.CharactersListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: CharactersListViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setObservers()
    }

    private fun setObservers() {
        viewModel.stateObservable.observe(this) { characterState ->
            updateView(characterState)
        }
//        viewModel.getCharacters()
    }

    private fun updateView(characterState: CharacterState) {
        when (characterState) {
            is CharacterState.Success -> {
                binding.progressBar.isVisible = false
                binding.textView.isVisible = true
                binding.textView.text =
                    characterState.characters.characters.joinToString { it.fullName }
            }
            is CharacterState.Error -> {
                binding.progressBar.isVisible = false
                binding.textView.isVisible = true
                binding.textView.text = getString(characterState.message)
            }
            CharacterState.Init -> {
                binding.textView.isVisible = false
                binding.progressBar.isVisible = false
            }
            CharacterState.Loading -> {
                binding.progressBar.isVisible = true
                binding.textView.isVisible = false
            }
        }
    }
}