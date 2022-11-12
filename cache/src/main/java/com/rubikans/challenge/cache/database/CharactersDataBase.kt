package com.rubikans.challenge.cache.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rubikans.challenge.cache.dao.CharacterDao
import com.rubikans.challenge.cache.models.CharacterCacheEntity
import com.rubikans.challenge.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [CharacterCacheEntity::class],
    version = Migrations.DB_VERSION,
    exportSchema = false,
)
abstract class CharactersDataBase @Inject constructor() : RoomDatabase() {

    abstract fun cachedCharacterDao(): CharacterDao
}