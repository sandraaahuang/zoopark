package com.sample.zoopark.data

import com.sample.zoopark.data.remote.RemoteDataSource

object ServiceLocator {

    @Volatile
    var repository: Repository? = null


    fun initializeRepository(): Repository {
        synchronized(this) {
            repository = repository ?: DefaultRepository(createRemoteDataSource())
            return repository ?: DefaultRepository(createRemoteDataSource())
        }
    }

    private fun createRemoteDataSource(): RemoteDataSource {
        return RemoteDataSource
    }
}