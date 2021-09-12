package com.sample.zoopark

import androidx.fragment.app.Fragment

fun Fragment.getVmFactory(): ViewModelFactory {

    val repository =  ZooParkApplication.instance.repository
    return ViewModelFactory(repository)
}