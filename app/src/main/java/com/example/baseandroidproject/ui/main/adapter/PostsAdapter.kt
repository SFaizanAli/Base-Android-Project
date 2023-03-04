package com.example.baseandroidproject.ui.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.baseandroidproject.R
import com.example.baseandroidproject.data.model.PostResponseModelItem

class RecyclerViewUserHistoryAdapter(
    var userHistoryModelList: ArrayList<PostResponseModelItem>,
) : RecyclerView.Adapter<RecyclerViewUserHistoryAdapter.ViewHolder>() {

    var selectedPosition = -1

    internal fun setDataList(imageList: ArrayList<PostResponseModelItem>) {
        this.userHistoryModelList = imageList
    }


    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(
        holder: RecyclerViewUserHistoryAdapter.ViewHolder, position: Int
    ) {
        holder.bindItems(userHistoryModelList[position])
    }

    override fun getItemCount(): Int {
        return userHistoryModelList.size
    }

    fun clearList() {
        this.userHistoryModelList = ArrayList()
        notifyDataSetChanged()
    }


    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        fun bindItems(mUserHistoryModelList: PostResponseModelItem) {


            val tvTitle = itemView.findViewById(R.id.tvSongName) as TextView
            val tvSubTitle = itemView.findViewById(R.id.tvArtist) as TextView


            itemView.findViewById(R.id.ivArtist) as ImageView


            tvTitle.text = mUserHistoryModelList.title
            tvSubTitle.text = mUserHistoryModelList.body


        }

        init {
            itemView.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position: Int = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                notifyItemChanged(selectedPosition)
                selectedPosition = adapterPosition
                notifyItemChanged(selectedPosition)

            }

        }

    }


}