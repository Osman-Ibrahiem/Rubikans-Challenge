package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.model.Settings
import com.rubikans.challenge.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSettingsUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : BaseUseCase<Unit, Flow<List<Settings>>> {

    override suspend operator fun invoke(params: Unit) = settingsRepository.getSettings()
}