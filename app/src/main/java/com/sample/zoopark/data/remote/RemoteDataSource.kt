package com.sample.zoopark.data.remote

import com.sample.zoopark.R
import com.sample.zoopark.area.models.ZooAreaData
import com.sample.zoopark.data.DataSource
import com.sample.zoopark.data.Result
import com.sample.zoopark.network.NetworkUtil
import com.sample.zoopark.network.NetworkUtil.getString
import com.sample.zoopark.network.ZooApi
import com.sample.zoopark.plant.models.PlantData


object RemoteDataSource: DataSource {
    override suspend fun getZooAreaList(): Result<List<ZooAreaData>> {
        if (!NetworkUtil.isInternetConnected())
            return Result.Fail(getString(R.string.intenet_not_connected))

        val getPropertiesDeferred = ZooApi.retrofitService.getZooAreaList()
        return try {
            val result = getPropertiesDeferred.await()
            Result.Success(result.zooResultData.results)
        } catch (e: Exception) {
            Result.Error(e)
        }

    }

    override suspend fun getPlantList(): Result<List<PlantData>> {
        if (!NetworkUtil.isInternetConnected())
            return Result.Fail(getString(R.string.intenet_not_connected))

        val getPropertiesDeferred = ZooApi.retrofitService.getPlantList()
        return try {
            val result = getPropertiesDeferred.await()
            Result.Success(result.plantResultData.results)
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}