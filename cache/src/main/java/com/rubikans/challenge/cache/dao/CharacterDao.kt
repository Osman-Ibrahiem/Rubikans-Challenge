package com.rubikans.challenge.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rubikans.challenge.cache.models.CharacterCacheEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): Flow<List<CharacterCacheEntity>>

    @Query("DELETE FROM characters")
    fun clearCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(character: CharacterCacheEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacters(characters: List<CharacterCacheEntity>): Long
}