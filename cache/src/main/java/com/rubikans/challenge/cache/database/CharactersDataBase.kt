package com.rubikans.challenge.cache.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.rubikans.challenge.cache.dao.CharacterDao
import com.rubikans.challenge.cache.models.CharacterCache
import com.rubikans.challenge.cache.utils.CacheConstants
import com.rubikans.challenge.cache.utils.Migrations
import javax.inject.Inject

@Database(
    entities = [CharacterCache::class],
    version = Migrations.DB_VERSION
)
abstract class CharactersDataBase @Inject constructor() : RoomDatabase() {

    abstract fun cachedCharacterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharactersDataBase? = null

        fun getInstance(context: Context): CharactersDataBase = INSTANCE ?: synchronized(this) {
            INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            CharactersDataBase::class.java,
            CacheConstants.DB_NAME
        ).build()
    }
}