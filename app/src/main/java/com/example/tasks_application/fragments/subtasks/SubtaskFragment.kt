package com.example.tasks_application.fragments.subtasks

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasks_application.R
import com.example.tasks_application.fragments.tasks.TasksAdapter
import com.example.tasks_application.fragments.tasks.TasksFragmentDirections
import com.example.tasks_application.view_models.SubtaskViewModel
import com.example.tasks_application.view_models.TaskViewModel
import kotlinx.android.synthetic.main.fragment_subtask.view.*

class SubtaskFragment : Fragment() {
    private val args by navArgs<SubtaskFragmentArgs>()
    private lateinit var mTaskViewModel: TaskViewModel
    private lateinit var mSubtaskViewModel: SubtaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_subtask, container, false)

        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]
        mSubtaskViewModel = ViewModelProvider(this)[SubtaskViewModel::class.java]
        mSubtaskViewModel.accessArgs(args)

        setHasOptionsMenu(true)

        val adapter = SubtaskAdapter(args)
        val recyclerView = view.recyclerView3
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())


        mSubtaskViewModel.getSubtasks.observe(viewLifecycleOwner, Observer{ subtask ->
            adapter.setData(subtask)
        })

        view.floatingActionButton3.setOnClickListener{
            val action = SubtaskFragmentDirections.actionSubtaskFragmentToAddSubtaskFragment(args.currentTask, args.currentList)
            findNavController().navigate(action)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
        inflater.inflate(R.menu.rename_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteTask()
        }
        if (item.itemId == R.id.menu_rename){
            val action = SubtaskFragmentDirections.actionSubtaskFragmentToUpdateTaskFragment(args.currentTask, args.currentList)
            findNavController().navigate(action)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteTask() {
        mTaskViewModel.deleteTask(args.currentTask)
        Toast.makeText(requireContext(), "Successfully deleted ${args.currentTask.task_title}", Toast.LENGTH_LONG).show()
        val action = SubtaskFragmentDirections.actionSubtaskFragmentToTasksFragment(args.currentList)
        findNavController().navigate(action)
    }
}