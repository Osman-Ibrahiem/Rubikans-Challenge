package com.rubikans.challenge.ui.settings

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.rubikans.challenge.R
import com.rubikans.challenge.core.theme.ThemeUtils
import com.rubikans.challenge.databinding.FragmentSettingsBinding
import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.presentation.viewmodel.SettingsViewModel
import com.rubikans.challenge.ui.base.BaseFragment
import com.rubikans.challenge.ui.settings.adapters.SettingsAdapter
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
        setupRecyclerView()
        viewModel.getSettings()
        observeResult()
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

    private fun observeResult() {
        viewModel.loading.observe(viewLifecycleOwner) { loading: Boolean? ->
            handleLoading(loading == true)
        }

        viewModel.error.observe(viewLifecycleOwner) { throwable: Throwable? ->
            throwable ?: return@observe

            val error = throwable.message ?: "Error"
            Timber.e(error)
            handleErrorMessage(error)
        }

        viewModel.settings.observe(viewLifecycleOwner) { settings: List<Settings>? ->
            settingsAdapter.list = settings ?: ArrayList()
        }

        viewModel.nightMode.observe(viewLifecycleOwner) { isNightMode: Boolean? ->
            isNightMode ?: return@observe

            themeUtils.setNightMode(requireActivity(), isNightMode)
        }
    }

}