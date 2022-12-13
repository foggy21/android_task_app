package com.example.tasks_application.fragments.update_task

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.tasks_application.R
import com.example.tasks_application.fragments.update_task.UpdateTaskFragment
import com.example.tasks_application.fragments.update_task.UpdateTaskFragmentDirections
import com.example.tasks_application.data.Task
import com.example.tasks_application.fragments.update_list.UpdateListFragmentArgs
import com.example.tasks_application.view_models.TaskViewModel
import kotlinx.android.synthetic.main.fragment_update_task.*
import kotlinx.android.synthetic.main.fragment_update_task.view.*

class UpdateTaskFragment : Fragment() {
    private val args by navArgs<UpdateTaskFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_task, container, false)

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.title_task.setText(args.currentTask.task_title)
        view.desc_task.setText(args.currentTask.desc)
        view.date_task.setText(args.currentTask.date)
        view.favorite_task.isChecked = args.currentTask.favorite

        view.button2.setOnClickListener {
            updateTaskInDatabase()
        }

        return view
    }

    private fun updateTaskInDatabase() {
        val title = title_task.text.toString()
        val desc = desc_task.text.toString()
        val date = date_task.text.toString()
        val favorite = favorite_task.isChecked

        if (inputChecked(title, desc, date)){
            val task = Task(args.currentTask.task_id, args.currentList.id, title, desc, favorite, date)
            mTaskViewModel.updateTask(task)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            val action = UpdateTaskFragmentDirections.actionUpdateTaskFragmentToTasksFragment(args.currentList)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out field", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecked(title : String, desc : String, date : String) : Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(date))
    }

}