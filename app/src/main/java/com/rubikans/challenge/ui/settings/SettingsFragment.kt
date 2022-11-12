package com.rubikans.challenge.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.R
import com.rubikans.challenge.core.theme.ThemeUtils
import com.rubikans.challenge.databinding.FragmentSettingsBinding
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.extension.observe
import com.rubikans.challenge.presentation.utils.Resource
import com.rubikans.challenge.presentation.viewmodel.SettingsViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class SettingsFragment : BaseFragment<FragmentSettingsBinding, SettingsViewModel>() {

    override val layoutId: Int = R.layout.fragment_settings
    override val viewModel: SettingsViewModel by viewModels()

    @Inject
    lateinit var settingsAdapter: SettingsAdapter

    @Inject
    lateinit var themeUtils: ThemeUtils

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.settings, ::onViewStateChange)
        observe(viewModel.nightMode, ::onViewStateChangeNightMode)
        setupRecyclerView()
        viewModel.getSettings()
    }

    private fun setupRecyclerView() {
        viewBinding.recyclerViewSettings.apply {
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
                handleLoading(false)
                result.data?.let {
                    settingsAdapter.list = it
                }
            }
            Resource.Status.ERROR -> {
                val error = result.message ?: "Error"
                Timber.e(error)
                handleErrorMessage(error)
            }
            Resource.Status.LOADING -> {
                handleLoading(true)
            }
            Resource.Status.INIT -> {
                handleLoading(false)
            }
        }
    }

    private fun onViewStateChangeNightMode(result: Resource<Boolean>) {
        when (result.status) {
            Resource.Status.SUCCESS -> {
                result.data?.let {
                    themeUtils.setNightMode(it)
                }
            }
            else -> {
            }
        }
    }

}