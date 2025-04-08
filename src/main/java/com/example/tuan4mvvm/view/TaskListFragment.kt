package com.example.tuan4mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tuan4mvvm.R
import com.example.tuan4mvvm.adapter.TaskAdapter
import com.example.tuan4mvvm.viewmodel.TaskViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class TaskListFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel
    private lateinit var adapter: TaskAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewModel
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        // Set up RecyclerView
        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        adapter = TaskAdapter()
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Observe task list changes
        viewModel.tasks.observe(viewLifecycleOwner) { tasks ->
            adapter.setTasks(tasks)
        }

        // Set up FAB for adding new tasks
        view.findViewById<FloatingActionButton>(R.id.fabAdd).setOnClickListener {
            findNavController().navigate(R.id.action_taskListFragment_to_taskAddFragment)
        }
    }
}