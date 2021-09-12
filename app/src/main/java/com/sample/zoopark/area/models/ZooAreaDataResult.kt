package com.sample.zoopark.area.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZooAreaDataResult(
    @Json(name = "result") val zooResultData: ZooResultData
) : Parcelable