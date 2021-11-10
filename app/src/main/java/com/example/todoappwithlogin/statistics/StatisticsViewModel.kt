

package com.example.todoappwithlogin.statistics

import android.app.Application
import androidx.lifecycle.*
import com.example.todoappwithlogin.data.Result
import com.example.todoappwithlogin.data.Result.Error
import com.example.todoappwithlogin.data.Result.Success
import com.example.todoappwithlogin.data.Task
import com.example.todoappwithlogin.data.source.DefaultTasksRepository
import kotlinx.coroutines.launch

/**
 * ViewModel for the statistics screen.
 */
class StatisticsViewModel(
    private val tasksRepository: DefaultTasksRepository
) : ViewModel() {

    private val tasks: LiveData<Result<List<Task>>> = tasksRepository.observeTasks()
    private val _dataLoading = MutableLiveData<Boolean>(false)
    private val stats: LiveData<StatsResult?> = tasks.map {
        if (it is Success) {
            getActiveAndCompletedStats(it.data)
        } else {
            null
        }
    }

    val activeTasksPercent = stats.map {
        it?.activeTasksPercent ?: 0f }
    val completedTasksPercent: LiveData<Float> = stats.map { it?.completedTasksPercent ?: 0f }
    val dataLoading: LiveData<Boolean> = _dataLoading
    val error: LiveData<Boolean> = tasks.map { it is Error }
    val empty: LiveData<Boolean> = tasks.map { (it as? Success)?.data.isNullOrEmpty() }

    fun refresh() {
        _dataLoading.value = true
            viewModelScope.launch {
                tasksRepository.refreshTasks()
                _dataLoading.value = false
            }
    }
}
