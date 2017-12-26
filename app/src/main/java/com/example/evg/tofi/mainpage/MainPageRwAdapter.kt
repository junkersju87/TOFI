package com.example.evg.tofi.mainpage

import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.evg.tofi.R
import com.example.evg.tofi.models.EventModel

class MainPageRwAdapter(val onClickAction: (element: EventModel) -> Unit, val list: List<EventModel>) : RecyclerView.Adapter<MainPageRwAdapter.ViewHolder>() {


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val img = view.findViewById<ImageView>(R.id.main_page_item_img)
        val title = view.findViewById<TextView>(R.id.main_page_item_title)
        val descr = view.findViewById<TextView>(R.id.main_page_item_descr)
        val craete_date = view.findViewById<TextView>(R.id.main_page_item_create_date)
        val change_date = view.findViewById<TextView>(R.id.main_page_item_change_date)
        val container = view.findViewById<ConstraintLayout>(R.id.main_page_item_container)
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent?.context).inflate(R.layout.main_page_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.title.text = list[position].title
        holder.descr.text = list[position].description
        holder.change_date.text = list[position].change_date
        holder.craete_date.text = list[position].create_date
        holder.container.setOnClickListener({
            onClickAction.invoke(list[position])
        })
    }
}