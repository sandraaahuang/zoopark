package com.sample.zoopark.plant.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PlantResultData (

    var limit : Int,
    var offset : Int,
    var count : Int,
    var sort : String,
    var results : List<PlantData>
) : Parcelable