package com.example.todoappwithlogin.di

import com.example.todoappwithlogin.addedittask.AddEditTaskViewModel
import com.example.todoappwithlogin.statistics.StatisticsViewModel
import com.example.todoappwithlogin.taskdetail.TaskDetailViewModel
import com.example.todoappwithlogin.tasks.TasksViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelInstance = module {
    viewModel {
        TasksViewModel(
            get()
        )
    }
}

val viewModelAddEdit = module {
    viewModel {
        AddEditTaskViewModel(
            get()
        )
    }
}

val viewModelStatistics = module {
    viewModel {
        StatisticsViewModel(
            get()
        )
    }
}

val viewModelDetail = module {
    viewModel {
        TaskDetailViewModel(
            get()
        )
    }
}