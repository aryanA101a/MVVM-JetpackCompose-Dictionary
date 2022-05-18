package com.example.dictionary.feature_dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dictionary.feature_dictionary.data.local.entity.WordInfoEntity

@Dao
interface WordInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordsInfo(wordsInfo: List<WordInfoEntity>)

    @Query("DELETE FROM wordinfoentity WHERE word IN(:words)")
    suspend fun deleteWordsInfo(words: List<String>)

    @Query("SELECT * FROM wordinfoentity WHERE word LIKE '%'||:word||'%'")
    suspend fun getWordsInfo(word: String): List<WordInfoEntity>
}