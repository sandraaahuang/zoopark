package com.sample.zoopark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.zoopark.area.viewmodels.ZooAreaViewModel
import com.sample.zoopark.areadetails.viewmodels.ZooAreaDetailsViewModel
import com.sample.zoopark.data.Repository


class ViewModelFactory constructor(
    private val repository: Repository
) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel> create(modelClass: Class<T>) =
        with(modelClass) {
            when {
                isAssignableFrom(ZooAreaViewModel::class.java) ->
                    ZooAreaViewModel(repository)
                isAssignableFrom(ZooAreaDetailsViewModel::class.java) ->
                    ZooAreaDetailsViewModel(repository)
                else ->
                    throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
            }
        } as T
}
