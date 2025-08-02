package com.example.feature.character_list.data

import com.example.core.network.ApiResult
import com.example.feature.character_list.data.network.CharacterApiService
import com.example.feature.character_list.data.network.toCharacters
import com.example.feature.character_list.domain.CharacterRepository
import com.example.feature.character_list.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CharacterRepositoryImpl @Inject constructor(
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val characterApiService: CharacterApiService
) : CharacterRepository {

    override fun getCharacters(): Flow<ApiResult<List<Character>>> = flow {
        emit(ApiResult.Loading)

        val response = characterApiService.getCharacters()
        try {
            val body = response.body()
            if (response.isSuccessful && body != null) {
                emit(ApiResult.Success(body.toCharacters()))
            } else {
                val errorMessage = response.errorBody()?.string() ?: UNKNOWN_ERROR_MESSAGE
                emit(ApiResult.Error(errorMessage))
            }
        } catch (e: Exception) {
            emit(ApiResult.Error(e.message ?: UNKNOWN_ERROR_MESSAGE))
        }

    }.flowOn(dispatcher)

    companion object {
        private const val UNKNOWN_ERROR_MESSAGE = "Unknown error"
    }
}