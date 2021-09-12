package com.sample.zoopark.area.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZooAreaData(
    @Json(name = "E_Pic_URL") val picture: String,
    @Json(name = "E_Geo") val geo: String,
    @Json(name = "E_Info") val information: String,
    @Json(name = "E_no") val no: Int,
    @Json(name = "E_Category") val category: String,
    @Json(name = "E_Name") val name: String,
    @Json(name = "E_Memo") val memo: String,
    @Json(name = "_id") val id: Int,
    @Json(name = "E_URL") val url: String
    ) : Parcelable