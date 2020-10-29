package net.decenternet.technicalexam.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import net.decenternet.technicalexam.model.Task2


@Database(entities = arrayOf(Task2::class), version = 1, exportSchema = false)
abstract class TaskRoomDB : RoomDatabase(){

    abstract fun taskDao(): TaskDao

    private class DatabaseCallback(
            private val scope: CoroutineScope
    ): RoomDatabase.Callback(){
        override fun onOpen(db: SupportSQLiteDatabase) {
            super.onOpen(db)
            INSTANCE?.let {
                database ->
                scope.launch {
                    var taskDao = database.taskDao()
                }
            }
        }
    }

    //singleton
    companion object{
        @Volatile
        private var INSTANCE: TaskRoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): TaskRoomDB{
            return INSTANCE ?: synchronized(this){
                val instance = Room.databaseBuilder(context.applicationContext, TaskRoomDB::class.java,"task_db")
                        .fallbackToDestructiveMigration().addCallback(DatabaseCallback(scope)).build()

                INSTANCE = instance
                instance
            }

        }
    }

}