package com.example.dictionary.feature_dictionary.data.repository

import com.example.dictionary.core.util.Resource
import com.example.dictionary.feature_dictionary.data.local.WordInfoDao
import com.example.dictionary.feature_dictionary.data.remote.DictionaryApi
import com.example.dictionary.feature_dictionary.domain.model.WordInfo
import com.example.dictionary.feature_dictionary.domain.repository.WordInfoRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class WordInfoRepositoryImpl(private val api: DictionaryApi, private val dao: WordInfoDao) :
    WordInfoRepository {
    override fun getWordInfo(word: String): Flow<Resource<List<WordInfo>>> = flow {
        emit(Resource.Loading())

        val wordsInfo = dao.getWordsInfo(word).map { it.toWordInfo() }
        emit(Resource.Loading(data = wordsInfo))

        try {
            val remoteWordsInfo = api.getWordInfo(word)
            dao.deleteWordsInfo(remoteWordsInfo.map { it.word })
            dao.insertWordsInfo(remoteWordsInfo.map { it.toWordInfoEntity() })
        } catch (e: HttpException) {
            emit(Resource.Error(data = wordsInfo, message = "Oops, something went wrong"))
        } catch (e: IOException) {
            emit(
                Resource.Error(
                    data = wordsInfo,
                    message = "Couldn't reach server, check your internet connection."
                )
            )
        }

        val newWordsInfo=dao.getWordsInfo(word).map{it.toWordInfo()}
        emit(Resource.Success(newWordsInfo))

    }
}