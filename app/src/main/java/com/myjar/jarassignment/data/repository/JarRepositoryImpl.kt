package com.myjar.jarassignment.data.repository

import com.myjar.jarassignment.data.api.ApiService
import com.myjar.jarassignment.data.model.ComputerItem
import com.myjar.jarassignment.domain.repository.JarRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class JarRepositoryImpl(
    private val apiService: ApiService
) : JarRepository {
    override suspend fun fetchResults(): Flow<List<ComputerItem>> = flow {
        emit(apiService.fetchResults())
    }
}