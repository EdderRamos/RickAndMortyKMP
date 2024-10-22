package com.edlabcode.rickmortyapp.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.edlabcode.rickmortyapp.data.database.entity.CharacterOfTheDayEntity

@Dao
interface UserPreferencesDAO {
    @Query("SELECT * FROM character_of_the_day")
    suspend fun getCharacterOfTheDayDB(): CharacterOfTheDayEntity?

    @Insert(
        entity = CharacterOfTheDayEntity::class,
        onConflict = OnConflictStrategy.REPLACE
    )
    suspend fun saveCharacter(characterOfTheDayEntity: CharacterOfTheDayEntity)
}