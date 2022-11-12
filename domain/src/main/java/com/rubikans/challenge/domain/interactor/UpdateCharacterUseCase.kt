package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class UpdateCharacterUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<Character, Flow<Character>> {

    override suspend operator fun invoke(params: Character): Flow<Character> =
        characterRepository.updateCharacter(params)

}