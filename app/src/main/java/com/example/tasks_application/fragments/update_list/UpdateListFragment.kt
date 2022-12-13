package com.example.tasks_application.fragments.update_list

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
import com.example.tasks_application.data.Lists
import com.example.tasks_application.view_models.ListsViewModel
import kotlinx.android.synthetic.main.fragment_add_list.*
import kotlinx.android.synthetic.main.fragment_add_list.view.*

class UpdateListFragment : Fragment() {
    private val args by navArgs<UpdateListFragmentArgs>()
    private lateinit var mListsViewModel: ListsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_update_list, container, false)

        mListsViewModel = ViewModelProvider(this)[ListsViewModel::class.java]

        view.title_task.setText(args.currentList.title)

        view.button2.setOnClickListener{
            updateListInDatabase()
        }

        return view
    }

    private fun updateListInDatabase() {
        val title = title_task.text.toString()
        if (!TextUtils.isEmpty(title)){
            val list = Lists(args.currentList.id, title)
            mListsViewModel.updateList(list)
            Toast.makeText(requireContext(), "Successfully updated!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_updateListFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out field", Toast.LENGTH_LONG).show()
        }
    }
}