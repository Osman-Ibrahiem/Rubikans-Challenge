package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.repository.SettingsRepository
import javax.inject.Inject

class ClearCacheUseCase @Inject constructor(
    private val settingsRepository: SettingsRepository,
) : BaseUseCase<Unit, Unit> {

    override suspend operator fun invoke(params: Unit) = settingsRepository.clearCache()

}