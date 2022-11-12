package com.rubikans.challenge.ui.settings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.databinding.FragmentSettingsBinding
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.extension.showSnackBar
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    private val viewModel: SettingsViewModel by viewModels()

    @Inject
    lateinit var settingsAdapter: SettingsAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.settings, ::onViewStateChange)
        setupRecyclerView()
        viewModel.getSettings()
    }

    private fun setupRecyclerView() {
        binding.recyclerViewSettings.apply {
            adapter = settingsAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

        settingsAdapter.setItemClickListener { selectedSetting ->
            viewModel.setSettings(selectedSetting)
        }
    }

    private fun onViewStateChange(result: Resource<List<Settings>>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                result.data?.let {
                    settingsAdapter.list = it
                }
            }
            Resource.Status.ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                showSnackBar(binding.rootView, error)
            }
            Resource.Status.LOADING -> {

            }
            Resource.Status.INIT -> {

            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}