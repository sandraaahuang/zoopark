package com.sample.zoopark.areadetails.views

import android.graphics.Color
import android.graphics.PorterDuff
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.zoopark.CommonConfig
import com.sample.zoopark.R
import com.sample.zoopark.area.models.ZooAreaData
import com.sample.zoopark.areadetails.viewmodels.ZooAreaDetailsViewModel
import com.sample.zoopark.plant.models.PlantData
import kotlinx.android.synthetic.main.fragment_zoo_area.*
import kotlinx.android.synthetic.main.item_zoo_area_details_header.view.*
import kotlinx.android.synthetic.main.item_zoo_area_details_plants.view.*


class ZooAreaDetailsAdapter(private val zooData: ZooAreaData,
                            private val viewModel: ZooAreaDetailsViewModel,
                            private val fragment: ZooAreaDetailsFragment,
                            private val link: LinkOnClickListener,
                            private val onClickListener: OnClickListener):
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var plantList: ArrayList<PlantData> = arrayListOf()
    private val TAG = "ZooAreaDetailsAdapter"

    fun isHeader(position: Int): Boolean {
        return position == 0
    }

    class HeaderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_VIEW_TYPE_HEADER -> HeaderViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_zoo_area_details_header, parent, false))
            ITEM_VIEW_TYPE_ITEM -> ItemViewHolder(LayoutInflater.from(parent.context)
                .inflate(R.layout.item_zoo_area_details_plants, parent, false))
            else -> throw ClassCastException("Unknown viewType $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (isHeader(position)) {
            true -> {
                Glide.with(holder.itemView.imageview_zoo_details_picture)
                    .load(zooData.picture)
                    .into(holder.itemView.imageview_zoo_details_picture)

                when (zooData.information.isEmpty()) {
                    true -> {
                        holder.itemView.textview_zoo_details_inforamtion.visibility = View.GONE
                    }
                    false -> {
                        holder.itemView.textview_zoo_details_inforamtion.visibility = View.VISIBLE
                        holder.itemView.textview_zoo_details_inforamtion.text = zooData.information
                    }
                }

                when (zooData.memo.isEmpty()) {
                    true -> {
                        holder.itemView.textview_zoo_details_memo.visibility = View.GONE
                    }
                    false -> {
                        holder.itemView.textview_zoo_details_memo.visibility = View.VISIBLE
                        holder.itemView.textview_zoo_details_memo.text = zooData.memo
                    }
                }

                when (zooData.category.isEmpty()) {
                    true -> {
                        holder.itemView.textview_zoo_details_category.visibility = View.GONE
                    }
                    false -> {
                        holder.itemView.textview_zoo_details_category.visibility = View.VISIBLE
                        holder.itemView.textview_zoo_details_category.text = zooData.category
                    }
                }

                holder.itemView.textview_zoo_details_link.setOnClickListener {
                    link.onClick()
                }
            }
            false -> {
                holder.itemView.progressbar_plant_data_loading.indeterminateDrawable.setColorFilter(Color.parseColor("#000000"), PorterDuff.Mode.SRC_IN)

                viewModel.isLoading.observe(fragment, Observer {
                    holder.itemView.progressbar_plant_data_loading.visibility = View.GONE
                    holder.itemView.imageview_plant_right_arrow.visibility = View.GONE
                    holder.itemView.textview_plant_no_data.visibility = View.GONE
                    holder.itemView.textview_plants_header.visibility = View.GONE

                    when (it) {

                        CommonConfig.LOADING_STATUS.LOADING -> {
                            holder.itemView.progressbar_plant_data_loading.visibility = View.VISIBLE
                        }
                        CommonConfig.LOADING_STATUS.LOADING_SUCCESS -> {
                            when (plantList.isEmpty()) {
                                true -> {
                                    holder.itemView.textview_plant_no_data.visibility = View.VISIBLE
                                }
                                false -> {
                                    holder.itemView.imageview_plant_right_arrow.visibility = View.VISIBLE
                                    if (position - 1 == 0) {
                                        holder.itemView.textview_plants_header.visibility = View.VISIBLE
                                    }
                                    Glide.with(holder.itemView.imageview_plant_picture)
                                        .load(plantList[position - 1].pic01URL)
                                        .into(holder.itemView.imageview_plant_picture)

                                    holder.itemView.textview_plant_name_ch.text = plantList[position - 1].nameCh
                                    holder.itemView.textview_plant_also_known.text = plantList[position - 1].alsoKnown


                                    holder.itemView.setOnClickListener {
                                        onClickListener.onClick(plantList[position - 1])
                                    }
                                }
                            }

                        }
                        CommonConfig.LOADING_STATUS.LOADING_ERROR -> {
                        }
                    }
                })

            }
        }



    }

    override fun getItemViewType(position: Int): Int {
        return if (isHeader(position)) ITEM_VIEW_TYPE_HEADER else ITEM_VIEW_TYPE_ITEM
    }

    override fun getItemCount(): Int {
        return when (plantList.isEmpty()) {
            true -> plantList.size + 2
            false -> plantList.size + 1
        }
    }

    fun updateList(newList: ArrayList<PlantData>) {
        Log.d(TAG, "updateList: $newList")
        plantList.clear()
        plantList = newList
        notifyDataSetChanged()
    }

    companion object {
        const val ITEM_VIEW_TYPE_HEADER = 0
        const val ITEM_VIEW_TYPE_ITEM = 1
    }

    class OnClickListener(val clickListener: (plantData: PlantData) -> Unit) {
        fun onClick(plantData: PlantData) = clickListener(plantData)
    }

    class LinkOnClickListener(val clickListener: () -> Unit) {
        fun onClick() = clickListener()
    }
}