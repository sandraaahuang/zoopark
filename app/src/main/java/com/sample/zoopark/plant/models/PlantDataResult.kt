package com.sample.zoopark.plant.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantDataResult(
    @Json(name = "result") val plantResultData: PlantResultData
) : Parcelable