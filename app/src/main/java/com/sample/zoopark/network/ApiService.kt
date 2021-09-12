package com.sample.zoopark.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.sample.zoopark.area.models.ZooAreaDataResult
import com.sample.zoopark.plant.models.PlantDataResult
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val client = OkHttpClient.Builder()
    .addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    })
        .addInterceptor(HostSelectionInterceptor())
        .cache(null)
        .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .baseUrl(NetWorkConfig.BASE_URL)
    .client(client)
    .build()


interface ApiService {
    @GET("5a0e5fbb-72f8-41c6-908e-2fb25eff9b8a?scope=resourceAquire")
    fun getZooAreaList():
            Deferred<ZooAreaDataResult>

    @GET("f18de02f-b6c9-47c0-8cda-50efad621c14?scope=resourceAquire")
    fun getPlantList():
            Deferred<PlantDataResult>

}


object ZooApi {
    val retrofitService: ApiService by lazy {
        retrofit.create(ApiService::class.java)
    }
}

class HostSelectionInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().apply {
            this.newBuilder()
                    .url(NetWorkConfig.BASE_URL)
                    .build()
        }
        return chain.proceed(request)
    }

}