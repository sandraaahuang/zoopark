package com.sample.zoopark.area.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ZooResultData (

    var limit : Int,
    var offset : Int,
    var count : Int,
    var sort : String,
    var results : List<ZooAreaData>
) : Parcelable