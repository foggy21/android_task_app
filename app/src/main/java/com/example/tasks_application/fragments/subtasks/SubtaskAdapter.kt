package com.example.tasks_application.fragments.subtasks

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.tasks_application.R
import com.example.tasks_application.data.Subtask
import kotlinx.android.synthetic.main.tasks_row.view.*

class SubtaskAdapter(private var args: SubtaskFragmentArgs) : RecyclerView.Adapter<SubtaskAdapter.MyViewHolder>() {
    private var subtasks = emptyList<Subtask>()
    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.tasks_row, parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        if (args.currentTask.task_id == subtasks[position].task_id){
            val currentSubtask = subtasks[position]
            holder.itemView.id_task.text = currentSubtask.subtask_id.toString()
            holder.itemView.task_title.text = currentSubtask.subtask_title
            holder.itemView.desc.text = currentSubtask.desc
            holder.itemView.date.text = currentSubtask.date

            holder.itemView.rowLayout.setOnClickListener{
                val action = SubtaskFragmentDirections.actionSubtaskFragmentToUpdateSubtaskFragment(currentSubtask, args.currentTask, args.currentList)
                holder.itemView.findNavController().navigate(action)
            }
        }
    }

    override fun getItemCount(): Int {
        return subtasks.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(subtasks: List<Subtask>){
        this.subtasks = subtasks
        notifyDataSetChanged()
    }
}