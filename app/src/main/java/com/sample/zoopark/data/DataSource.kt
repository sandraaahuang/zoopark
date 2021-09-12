package com.sample.zoopark.data

import com.sample.zoopark.area.models.ZooAreaData
import com.sample.zoopark.plant.models.PlantData
import com.sample.zoopark.plant.models.PlantResultData

interface DataSource {

    suspend fun getZooAreaList(): Result<List<ZooAreaData>>

    suspend fun getPlantList(): Result<List<PlantData>>
}