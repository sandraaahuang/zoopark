package com.sample.zoopark.data


import com.sample.zoopark.area.models.ZooAreaData
import com.sample.zoopark.data.remote.RemoteDataSource
import com.sample.zoopark.plant.models.PlantData

class DefaultRepository(private val remoteDataSource: RemoteDataSource): Repository {

    override suspend fun getZooAreaList(): Result<List<ZooAreaData>> {
        return remoteDataSource.getZooAreaList()
    }

    override suspend fun getPlantList(): Result<List<PlantData>> {
        return remoteDataSource.getPlantList()
    }
}