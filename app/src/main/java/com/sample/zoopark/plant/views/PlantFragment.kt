package com.sample.zoopark.plant.views

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.sample.zoopark.R
import kotlinx.android.synthetic.main.fragment_plant.*

class PlantFragment: Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_plant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val plantData = PlantFragmentArgs.fromBundle(arguments!!).PlantData

        Glide.with(this)
            .load(plantData.pic01URL)
            .into(imageview_plant_picture)
        textview_plant_name_ch.text = plantData.nameCh
        textview_plant_name_en.text = plantData.nameEn
        textview_plant_also_known.text = plantData.alsoKnown
        textview_plant_brief.text = plantData.brief
        textview_plant_feature.text = plantData.feature
        textview_plant_function.text = plantData.function
        textview_last_update.text = plantData.update


    }
}