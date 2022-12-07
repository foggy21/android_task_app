package com.example.tasks_application.fragments.tasks

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
import com.example.tasks_application.data.Task
import com.example.tasks_application.view_models.ListsViewModel
import com.example.tasks_application.view_models.TaskViewModel
import kotlinx.android.synthetic.main.fragment_tasks.view.*

class TasksFragment : Fragment() {
    private val args by navArgs<TasksFragmentArgs>()
    private lateinit var mListsViewModel: ListsViewModel
    private lateinit var mTaskViewModel: TaskViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_tasks, container, false)

        mListsViewModel = ViewModelProvider(this)[ListsViewModel::class.java]
        mTaskViewModel = ViewModelProvider(this)[TaskViewModel::class.java]

        setHasOptionsMenu(true)

        val adapter = TasksAdapter(args)
        val recyclerView = view.recyclerView2
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        mTaskViewModel.getTasks.observe(viewLifecycleOwner, Observer{ task ->
            adapter.setData(task as MutableList<Task>)
        })

        view.floatingActionButton2.setOnClickListener{
            val action = TasksFragmentDirections.actionTasksFragmentToAddTaskFragment(args.currentList)
            findNavController().navigate(action)
        }

        return view
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.delete_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.menu_delete){
            deleteList()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun deleteList() {
        mListsViewModel.deleteList(args.currentList)
        Toast.makeText(requireContext(), "Successfully deleted ${args.currentList.title}", Toast.LENGTH_LONG).show()
        findNavController().navigate(R.id.action_tasksFragment_to_listFragment)
    }
}