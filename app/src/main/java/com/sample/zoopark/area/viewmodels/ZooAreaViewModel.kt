package com.sample.zoopark.area.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.zoopark.CommonConfig
import com.sample.zoopark.area.models.ZooAreaData
import com.sample.zoopark.data.Repository
import com.sample.zoopark.data.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class ZooAreaViewModel(private val repository: Repository): ViewModel() {

    private val TAG = "ZooAreaViewModel"

    private val job = Job()
    private val coroutineScope = CoroutineScope(job + Dispatchers.IO)

    private var _zooAreaList = MutableLiveData<ArrayList<ZooAreaData>>()
    val zooAreaList: LiveData<ArrayList<ZooAreaData>> = _zooAreaList
    private var _isLoading = MutableLiveData<CommonConfig.LOADING_STATUS>()
    val isLoading: LiveData<CommonConfig.LOADING_STATUS> = _isLoading


    init {

        _isLoading.postValue(CommonConfig.LOADING_STATUS.LOADING)

        coroutineScope.launch {
            val result = repository.getZooAreaList()
            Log.d(TAG, "getZooAreaList, result: $result")
            when (result) {
                is Result.Success -> {
                    val tempZooAreaList = arrayListOf<ZooAreaData>()
                    result.data.forEach {
                        tempZooAreaList.add(it)
                    }
                    _zooAreaList.postValue(ArrayList(result.data))
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