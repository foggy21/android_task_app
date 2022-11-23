package com.example.tasks_application.fragments.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks_application.R
import com.example.tasks_application.data.Lists
import kotlinx.android.synthetic.main.lists_row.view.*

class ListAdapter : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {
    private var lists = emptyList<Lists>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.lists_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = lists[position]
        holder.itemView.id_text.text = currentItem.id.toString()
        holder.itemView.name.text = currentItem.title

        holder.itemView.rowLayout.setOnClickListener{
            val action = ListFragmentDirections.actionListFragmentToTasksFragment(currentItem)
            holder.itemView.findNavController().navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return lists.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Lists>){
        this.lists = list
        notifyDataSetChanged()
    }
}