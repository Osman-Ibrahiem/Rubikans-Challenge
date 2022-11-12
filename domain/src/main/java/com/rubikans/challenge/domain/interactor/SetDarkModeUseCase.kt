package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.repository.SettingsRepository
import javax.inject.Inject

class SetDarkModeUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : BaseUseCase<Boolean, Unit> {

    override suspend operator fun invoke(params: Boolean) {
        settingsRepository.isNight = params
    }
}