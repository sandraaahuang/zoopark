package com.sample.zoopark.areadetails.views

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.zoopark.R
import com.sample.zoopark.areadetails.viewmodels.ZooAreaDetailsViewModel
import com.sample.zoopark.getVmFactory
import com.sample.zoopark.plant.models.PlantData
import kotlinx.android.synthetic.main.fragment_zoo_area_details.*

class ZooAreaDetailsFragment: Fragment() {

    private val viewModel by viewModels<ZooAreaDetailsViewModel> { getVmFactory() }
    private var adapter: ZooAreaDetailsAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_zoo_area_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val zooData = ZooAreaDetailsFragmentArgs.fromBundle(arguments!!).ZooAreaData
        adapter = ZooAreaDetailsAdapter(
            zooData,
            viewModel,
            this,
            ZooAreaDetailsAdapter.LinkOnClickListener {
            val uri = Uri.parse(zooData.url)
            val intent = Intent(Intent.ACTION_VIEW, uri)
            startActivity(intent) },
            ZooAreaDetailsAdapter.OnClickListener {
            findNavController().navigate(ZooAreaDetailsFragmentDirections.actionZooAreaDetailsFragmentToPlantFragment(it, it.nameCh))
            })

        val dividerItemDecoration = DividerItemDecoration(
            recyclerview_zoo_area_details.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerview_zoo_area_details.addItemDecoration(dividerItemDecoration)
        recyclerview_zoo_area_details.adapter = adapter

        viewModel.plantList.observe(viewLifecycleOwner, Observer {
            var updateList = listOf<PlantData>()
            updateList = it.filter { _plant ->
                val locationList = arrayListOf<String>()
                _plant.location.split("；").forEach {
                    locationList.add(it)
                }
                locationList.contains(zooData.name)
            }
            adapter?.updateList(ArrayList(updateList))
        })
    }
}