package com.rubikans.challenge.domain.interactor

import com.rubikans.challenge.domain.model.CharactersList
import com.rubikans.challenge.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetCharactersListUseCase @Inject constructor(
    private val characterRepository: CharacterRepository,
) : BaseUseCase<Unit, Flow<CharactersList>> {

    override suspend operator fun invoke(params: Unit) = characterRepository.getCharacters()

}