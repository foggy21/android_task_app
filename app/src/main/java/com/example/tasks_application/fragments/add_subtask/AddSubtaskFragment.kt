package com.example.tasks_application.fragments.add_subtask

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
import com.example.tasks_application.data.Subtask
import com.example.tasks_application.view_models.SubtaskViewModel
import kotlinx.android.synthetic.main.fragment_add_subtask.*
import kotlinx.android.synthetic.main.fragment_add_subtask.view.*

class AddSubtaskFragment : Fragment() {
    private val args by navArgs<AddSubtaskFragmentArgs>()
    private lateinit var mSubtaskViewModel: SubtaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_subtask, container, false)

        mSubtaskViewModel = ViewModelProvider(this)[SubtaskViewModel::class.java]

        view.button3.setOnClickListener {
            insertSubtaskToDatabase()
        }

        return view
    }

    private fun insertSubtaskToDatabase() {
        val title = title_task3.text.toString()
        val desc = desc_task3.text.toString()
        val date = date_task3.text.toString()
        if (inputChecked(title, desc, date)){
            val subtask = Subtask(0, args.currentTask.task_id, title, desc, date)
            mSubtaskViewModel.addSubtask(subtask)
            Toast.makeText(requireContext(), "Successfully added subtask!", Toast.LENGTH_LONG).show()
            val action = AddSubtaskFragmentDirections.actionAddSubtaskFragmentToSubtaskFragment(args.currentTask, args.currentList)
            findNavController().navigate(action)
        } else{
            Toast.makeText(requireContext(), "Please fill out all fields.", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecked(title : String, desc : String, date : String) : Boolean {
        return !(TextUtils.isEmpty(title) || TextUtils.isEmpty(desc) || TextUtils.isEmpty(date))
    }
}