package com.example.tasks_application.fragments.add_task

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
import com.example.tasks_application.data.Task
import com.example.tasks_application.fragments.tasks.TasksFragmentArgs
import com.example.tasks_application.view_models.ListsViewModel
import com.example.tasks_application.view_models.TaskViewModel
import kotlinx.android.synthetic.main.fragment_add_task.*
import kotlinx.android.synthetic.main.fragment_add_task.view.*

class AddTaskFragment : Fragment() {
    private val args by navArgs<AddTaskFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_task, container, false)

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        view.button2.setOnClickListener{
            insertTaskToDatabase()
        }

        return view
    }

    private fun insertTaskToDatabase() {
        val title = title_task.text.toString()
        val desc = desc_task.text.toString()
        val date = date_task.text.toString()
        val favorite = favorite_task.isChecked
        if (inputChecked(title, desc, date)){
            val task = Task(0, args.currentList.id, title, desc, favorite, date)
            mTaskViewModel.addTask(task)
            Toast.makeText(requireContext(), "Successfully added task!", Toast.LENGTH_LONG).show()
            val action = AddTaskFragmentDirections.actionAddTaskFragmentToTasksFragment(args.currentList)
            findNavController().navigate(action)
        }else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecked(title : String, desc : String, date : String) : Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(date))
    }
}