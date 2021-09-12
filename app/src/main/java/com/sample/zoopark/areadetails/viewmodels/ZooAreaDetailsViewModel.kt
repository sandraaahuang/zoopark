package com.sample.zoopark.areadetails.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.zoopark.CommonConfig
import com.sample.zoopark.data.Repository
import com.sample.zoopark.data.Result
import com.sample.zoopark.plant.models.PlantData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

private val job = Job()
private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

class ZooAreaDetailsViewModel(private val repository: Repository): ViewModel() {

    private val TAG = "ZooAreaViewModel"
    private var _plantList = MutableLiveData<ArrayList<PlantData>>()
    val plantList: LiveData<ArrayList<PlantData>> = _plantList

    private var _isLoading = MutableLiveData<CommonConfig.LOADING_STATUS>()
    val isLoading: LiveData<CommonConfig.LOADING_STATUS> = _isLoading

    init {

        _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING)

        coroutineScope.launch {
            val result = repository.getPlantList()
            Log.d(TAG, "getPlantList, result: $result")
            when (result) {
                is Result.Success -> {
                    val tempPlantList = arrayListOf<PlantData>()
                    result.data.forEach {
                        tempPlantList.add(it)
                    }
                    _plantList.postValue(ArrayList(result.data))
                    _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING_SUCCESS)
                }
                is Result.Fail<*> -> {
                    _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING_ERROR)
                }
                is Result.Error -> {
                    _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING_ERROR)
                }
                else -> {
                    _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING_ERROR)
                }
            }
        }
    }
}