package com.myjar.jarassignment.domain.repository

import com.myjar.jarassignment.data.model.ComputerItem
import kotlinx.coroutines.flow.Flow

interface JarRepository {
    suspend fun fetchResults(): Flow<List<ComputerItem>>
}