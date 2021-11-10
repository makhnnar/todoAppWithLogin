package com.example.todoappwithlogin.di

import androidx.room.Room
import com.example.todoappwithlogin.data.source.DefaultTasksRepository
import com.example.todoappwithlogin.data.source.local.TasksLocalDataSource
import com.example.todoappwithlogin.data.source.remote.TasksRemoteDataSource
import com.example.todoappwithlogin.data.source.local.ToDoDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dbInstance = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            ToDoDatabase::class.java,
            "taskRepository.db"
        ).build()
    }
}

val localRepository = module{
    single{
        TasksLocalDataSource(
            get<ToDoDatabase>().taskDao()
        )
    }
}

val remoteRepository = module{
    single{
        TasksRemoteDataSource()
    }
}

val defaultRepository = module{
    single{
        DefaultTasksRepository(
            get(),
            get()
        )
    }
}