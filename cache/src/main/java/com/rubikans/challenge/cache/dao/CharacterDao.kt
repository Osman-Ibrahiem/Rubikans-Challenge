package com.rubikans.challenge.cache.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rubikans.challenge.cache.models.CharacterCache

@Dao
interface CharacterDao {

    @Query("SELECT * FROM characters")
    fun getCharacters(): List<CharacterCache>

    @Query("DELETE FROM characters")
    fun clearCharacters()

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCharacter(character: CharacterCache)
}