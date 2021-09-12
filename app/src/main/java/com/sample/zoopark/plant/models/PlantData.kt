package com.sample.zoopark.plant.models

import android.os.Parcelable
import com.squareup.moshi.Json
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantData(
    @Json(name = "F_Name_Latin") val nameLatin: String,
    @Json(name = "F_pdf02_ALT") val pdf02ALT: String,
    @Json(name = "F_Location") val location: String,
    @Json(name = "F_pdf01_ALT") val pdf01ALT: String,
    @Json(name = "F_Summary") val summary: String,
    @Json(name = "F_Pic01_URL") val pic01URL: String,
    @Json(name = "F_pdf02_URL") val pdf02URL: String,
    @Json(name = "F_Pic02_URL") val pic02URL: String,
    @Json(name = "\uFEFFF_Name_Ch") val nameCh: String,
    @Json(name = "F_Keywords") val keywords: String,
    @Json(name = "F_Code") val code: String,
    @Json(name = "F_Geo") val geo: String,
    @Json(name = "F_Pic03_URL") val pic03URL: String,
    @Json(name = "F_Voice01_ALT") val voice01ALT: String,
    @Json(name = "F_AlsoKnown") val alsoKnown: String,
    @Json(name = "F_Voice02_ALT") val voice02ALT: String,
    @Json(name = "F_Pic04_ALT") val pic04ALT: String,
    @Json(name = "F_Name_En") val nameEn: String,
    @Json(name = "F_Brief") val brief: String,
    @Json(name = "F_Pic04_URL") val pic04URL: String,
    @Json(name = "F_Voice01_URL") val voice01URL: String,
    @Json(name = "F_Feature") val feature: String,
    @Json(name = "F_Pic02_ALT") val pic02ALT: String,
    @Json(name = "F_Family") val family: String,
    @Json(name = "F_Voice03_ALT") val voice03ALT: String,
    @Json(name = "F_Voice02_URL") val voice02URL: String,
    @Json(name = "F_Pic03_ALT") val pic03ALT: String,
    @Json(name = "F_Pic01_ALT") val pic01ALT: String,
    @Json(name = "F_CID") val cid: String,
    @Json(name = "F_pdf01_URL") val pdf01URL: String,
    @Json(name = "F_Vedio_URL") val vedioURL: String,
    @Json(name = "F_Genus") val genus: String,
    @Json(name = "F_Function＆Application") val function: String,
    @Json(name = "F_Voice03_URL") val voice03URL: String,
    @Json(name = "F_Update") val update: String,
    @Json(name = "_id") val id: Int
    ) : Parcelable