package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.model.Character
import com.rubikans.challenge.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharacterDetailsUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<Int, Flow<Character>> {

    override suspend operator fun invoke(params: Int): Flow<Character> =
        characterRepository.getCharacter(params)

}