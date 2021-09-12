package com.sample.zoopark.area.views

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sample.zoopark.R
import com.sample.zoopark.area.models.ZooAreaData
import kotlinx.android.synthetic.main.item_zoo_area.view.*

class ZooAreaAdapter(private val onClickListener: OnClickListener) :
    ListAdapter<ZooAreaData, ZooAreaAdapter.ItemViewHolder>(DiffCallback) {

    private val TAG = "TodoListAdapter"
    private var zooList: ArrayList<ZooAreaData> = arrayListOf()

    companion object DiffCallback : DiffUtil.ItemCallback<ZooAreaData>() {
        override fun areItemsTheSame(
            oldItem: ZooAreaData,
            newItem: ZooAreaData
        ): Boolean {
            return oldItem.id.toLong() == newItem.id.toLong()
        }

        override fun areContentsTheSame(
            oldItem: ZooAreaData,
            newItem: ZooAreaData
        ): Boolean {
            return oldItem == newItem
        }
    }

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {

        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_zoo_area, parent, false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        Glide.with(holder.itemView.imageview_zoo_picture)
            .load(zooList[position].picture)
            .into(holder.itemView.imageview_zoo_picture)

        holder.itemView.textview_zoo_title.text = zooList[position].name
        holder.itemView.textview_zoo_information.text = zooList[position].information
        holder.itemView.textview_zoo_futher_info.text = zooList[position].memo

        holder.itemView.setOnClickListener {
            onClickListener.onClick(zooList[position])
        }
    }

    override fun getItemCount(): Int {
        return zooList.size
    }

    override fun submitList(list: MutableList<ZooAreaData>?) {
        Log.d(TAG, "submitList, list: $list")
        zooList = arrayListOf()
        list?.forEach {
            zooList.add(it.copy())
        }
        super.submitList(list)
    }

    class OnClickListener(val clickListener: (zooAreaData: ZooAreaData) -> Unit) {
        fun onClick(zooAreaData: ZooAreaData) = clickListener(zooAreaData)
    }
}