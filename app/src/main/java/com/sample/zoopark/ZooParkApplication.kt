package com.sample.zoopark

import android.app.Application
import android.util.Log
import com.sample.zoopark.data.Repository
import com.sample.zoopark.data.ServiceLocator
import kotlin.properties.Delegates

class ZooParkApplication : Application() {
    private val TAG = "ZooParkApplication"

    val repository: Repository
        get() = ServiceLocator.initializeRepository()

    companion object {
        var instance: ZooParkApplication by Delegates.notNull()
    }

    override fun onCreate() {
        Log.d(TAG, "onCreate()")
        super.onCreate()
        instance = this
    }

}