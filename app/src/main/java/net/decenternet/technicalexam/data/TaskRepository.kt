package net.decenternet.technicalexam.data

import androidx.lifecycle.LiveData
import net.decenternet.technicalexam.db.TaskDao
import net.decenternet.technicalexam.model.Task2

class TaskRepository(private val taskDao: TaskDao) {
    val allTasks: LiveData<List<Task2>> = taskDao.getAllTasks()

    suspend fun insert(task: Task2){
        taskDao.insert(task)
    }

    suspend fun getTask(id: Int): Task2{
        return taskDao.getTask(id)
    }

    suspend fun deleteTask(task: Task2){
        return taskDao.deleteTask(task)
    }

    suspend fun updateTask(task: Task2){
        taskDao.updateTask(task)
    }
}