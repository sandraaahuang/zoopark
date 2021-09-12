package com.sample.zoopark.area.views

import android.graphics.Color
import android.graphics.PorterDuff
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
import com.sample.zoopark.CommonConfig
import com.sample.zoopark.R
import com.sample.zoopark.area.viewmodels.ZooAreaViewModel
import com.sample.zoopark.getVmFactory
import kotlinx.android.synthetic.main.fragment_zoo_area.*


class ZooAreaFragment: Fragment() {

    private val viewModel by viewModels<ZooAreaViewModel> { getVmFactory() }
    private var adapter: ZooAreaAdapter? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_zoo_area, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ZooAreaAdapter(ZooAreaAdapter.OnClickListener {
            findNavController().navigate(ZooAreaFragmentDirections.actionZooAreaFragmentToZooAreaDetailsFragment(it, it.name))
        })

        val dividerItemDecoration = DividerItemDecoration(
            recyclerview_zoo_area_list.context,
            LinearLayoutManager.VERTICAL
        )
        recyclerview_zoo_area_list.addItemDecoration(dividerItemDecoration)
        recyclerview_zoo_area_list.adapter = adapter

        progressbar_area_data_loading.indeterminateDrawable.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN)

        viewModel.isLoading.observe(viewLifecycleOwner, Observer {
            when (it) {
                CommonConfig.LOADING_STATUS.LOADING -> {
                    progressbar_area_data_loading.visibility = View.VISIBLE
                }
                else -> {
                    progressbar_area_data_loading.visibility = View.GONE
                }
            }
        })

        viewModel.zooAreaList.observe(viewLifecycleOwner, Observer {
            adapter?.submitList(it.toMutableList())
        })
    }
}


