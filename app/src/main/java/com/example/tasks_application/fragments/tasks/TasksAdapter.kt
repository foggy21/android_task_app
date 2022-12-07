package com.example.tasks_application.fragments.tasks

import android.annotation.SuppressLint
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks_application.R
import com.example.tasks_application.data.Task
import kotlinx.android.synthetic.main.fragment_add_task.view.*
import kotlinx.android.synthetic.main.lists_row.view.*
import kotlinx.android.synthetic.main.tasks_row.view.*
import kotlinx.android.synthetic.main.tasks_row.view.rowLayout

class TasksAdapter(arguments: TasksFragmentArgs) : RecyclerView.Adapter<TasksAdapter.MyViewHolder>() {
    private var tasks = emptyList<Task>()
    private var args = arguments
    class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tasks_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (args.currentList.id == tasks[position].list_id){
            val currentTask = tasks[position]
            holder.itemView.id_task.text = currentTask.task_id.toString()
            holder.itemView.task_title.text = currentTask.task_title
            holder.itemView.desc.text = currentTask.desc
            holder.itemView.date.text = currentTask.date
            if (currentTask.favorite){
                holder.itemView.setBackgroundColor(Color.YELLOW)
            }
        }

        holder.itemView.rowLayout.setOnClickListener {

        }
    }

    override fun getItemCount(): Int {
        return tasks.size
    }


    @SuppressLint("NotifyDataSetChanged")
    fun setData(task: MutableList<Task>){
        var i = 0
        while (i < task.size){
            if (task[i].list_id != args.currentList.id){
                task.remove(task[i])
            } else {
                i += 1
            }
        }
        this.tasks = task
        notifyDataSetChanged()
    }


}