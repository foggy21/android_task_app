package com.example.tasks_application.fragments.add_list

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tasks_application.R
import com.example.tasks_application.data.Lists
import com.example.tasks_application.view_models.ListsViewModel
import kotlinx.android.synthetic.main.fragment_add_list.*
import kotlinx.android.synthetic.main.fragment_add_list.view.*

class AddListFragment : Fragment() {
    private lateinit var mListsViewModel: ListsViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_add_list, container, false)

        mListsViewModel = ViewModelProvider(this)[ListsViewModel::class.java]

        view.button2.setOnClickListener{
            insertListToDatabase()
        }

        return view
    }

    private fun insertListToDatabase() {
        val title = editTextTextPersonName.text.toString()
        if (!TextUtils.isEmpty(title)){
            val list = Lists(0, title)
            mListsViewModel.addList(list)
            Toast.makeText(requireContext(), "Successfully added!", Toast.LENGTH_LONG).show()
            findNavController().navigate(R.id.action_addListFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out field", Toast.LENGTH_LONG).show()
        }
    }
}