package com.example.tasks_application.fragments.update_subtask

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
import kotlinx.android.synthetic.main.fragment_update_subtask.*
import kotlinx.android.synthetic.main.fragment_update_subtask.view.*

class UpdateSubtaskFragment : Fragment() {
    private val args by navArgs<UpdateSubtaskFragmentArgs>()
    private lateinit var mSubtaskViewModel: SubtaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update_subtask, container, false)

        mSubtaskViewModel = ViewModelProvider(this)[SubtaskViewModel::class.java]

        view.title_task4.setText(args.currentSubtask.subtask_title)
        view.desc_task4.setText(args.currentSubtask.desc)
        view.date_task4.setText(args.currentSubtask.date)

        view.button4.setOnClickListener {
            updateSubtaskInDatabase()
        }

        return view
    }

    private fun updateSubtaskInDatabase() {
        val title = title_task4.text.toString()
        val desc = desc_task4.text.toString()
        val date = date_task4.text.toString()

        if (inputChecked(title, desc, date)) {
            val subtask = Subtask(args.currentSubtask.subtask_id, args.currentTask.task_id, title, desc, date)
            mSubtaskViewModel.updateSubtask(subtask)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            val action = UpdateSubtaskFragmentDirections.actionUpdateSubtaskFragmentToSubtaskFragment(args.currentTask, args.currentList)
            findNavController().navigate(action)
        } else {
            Toast.makeText(requireContext(), "Please fill out field", Toast.LENGTH_LONG).show()
        }
    }

    private fun inputChecked(title : String, desc : String, date : String) : Boolean {
        return !(TextUtils.isEmpty(title) && TextUtils.isEmpty(desc) && TextUtils.isEmpty(date))
    }
}