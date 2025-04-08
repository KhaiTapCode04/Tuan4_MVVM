package com.example.tuan4mvvm.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.tuan4mvvm.R
import com.example.tuan4mvvm.viewmodel.TaskViewModel

class TaskAddFragment : Fragment() {

    private lateinit var viewModel: TaskViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_task_add, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up ViewModel
        viewModel = ViewModelProvider(requireActivity())[TaskViewModel::class.java]

        val titleEditText: EditText = view.findViewById(R.id.editTextTitle)
        val descriptionEditText: EditText = view.findViewById(R.id.editTextDescription)
        val addButton: Button = view.findViewById(R.id.buttonAdd)

        addButton.setOnClickListener {
            val title = titleEditText.text.toString()
            val description = descriptionEditText.text.toString()

            if (title.isNotEmpty()) {
                viewModel.addTask(title, description)
                findNavController().navigateUp()
            }
        }

        // Back button click
        view.findViewById<View>(R.id.backButton).setOnClickListener {
            findNavController().navigateUp()
        }
    }
}