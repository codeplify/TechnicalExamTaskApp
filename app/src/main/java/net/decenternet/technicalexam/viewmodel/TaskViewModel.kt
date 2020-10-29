package net.decenternet.technicalexam.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import net.decenternet.technicalexam.data.TaskLocalServiceProvider
import androidx.lifecycle.*
import net.decenternet.technicalexam.data.TaskRepository
import net.decenternet.technicalexam.db.TaskRoomDB
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.decenternet.technicalexam.model.Task2

class TaskViewModel(application: Application) : AndroidViewModel(application){
    private val repository: TaskRepository
    var tasks: LiveData<List<Task2>>

    init {
        val taskDao = TaskRoomDB.getDatabase(application, viewModelScope).taskDao()

        repository = TaskRepository(taskDao)
        tasks = repository.allTasks
    }


    fun save(task: Task2) = viewModelScope.launch{
        repository.insert(task)
    }

    fun update(task: Task2) = viewModelScope.launch{
        repository.updateTask(task)
    }

    fun delete(task: Task2) = viewModelScope.launch{
        repository.deleteTask(task)
    }
}