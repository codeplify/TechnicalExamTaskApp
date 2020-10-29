package net.decenternet.technicalexam.db

import androidx.lifecycle.LiveData
import androidx.room.*
import net.decenternet.technicalexam.model.Task2

@Dao
interface TaskDao {

    @androidx.room.Query("SELECT * FROM task_table")
    fun getAllTasks(): LiveData<List<Task2>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(task: Task2)

    @Query("SELECT * FROM task_table WHERE id = :id")
    fun getTask(id:Int): Task2

    @Query("SELECT COUNT(*) FROM task_table")
    fun getCountTask():Int

    @Delete
    suspend fun deleteTask(vararg task: Task2)

    @Update
    suspend fun updateTask(vararg task: Task2)

}