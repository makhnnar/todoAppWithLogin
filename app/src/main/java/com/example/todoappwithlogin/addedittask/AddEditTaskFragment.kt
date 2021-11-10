
package com.example.todoappwithlogin.addedittask

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todoappwithlogin.EventObserver
import com.example.todoappwithlogin.R
import com.example.todoappwithlogin.databinding.AddtaskFragBinding
import com.example.todoappwithlogin.tasks.ADD_EDIT_RESULT_OK
import com.example.todoappwithlogin.util.setupRefreshLayout
import com.example.todoappwithlogin.util.setupSnackbar
import com.google.android.material.snackbar.Snackbar
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * Main UI for the add task screen. Users can enter a task title and description.
 */
class AddEditTaskFragment : Fragment() {

    private lateinit var viewDataBinding: AddtaskFragBinding

    private val args: AddEditTaskFragmentArgs by navArgs()

    private val addEditVModel : AddEditTaskViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.addtask_frag, container, false)
        viewDataBinding = AddtaskFragBinding.bind(root).apply {
            this.viewmodel = addEditVModel
        }
        // Set the lifecycle owner to the lifecycle of the view
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupSnackbar()
        setupNavigation()
        this.setupRefreshLayout(viewDataBinding.refreshLayout)
        addEditVModel.start(args.taskId)
    }

    private fun setupSnackbar() {
        view?.setupSnackbar(this, addEditVModel.snackbarText, Snackbar.LENGTH_SHORT)
    }

    private fun setupNavigation() {
        addEditVModel.taskUpdatedEvent.observe(viewLifecycleOwner, EventObserver {
            val action = AddEditTaskFragmentDirections
                .actionAddEditTaskFragmentToTasksFragment(ADD_EDIT_RESULT_OK)
            findNavController().navigate(action)
        })
    }
}
