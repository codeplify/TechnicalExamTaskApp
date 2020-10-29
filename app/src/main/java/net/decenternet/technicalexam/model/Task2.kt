package net.decenternet.technicalexam.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "task_table")
class Task2 (@PrimaryKey
              @SerializedName("id")
              @ColumnInfo(name = "id") val id:Int,

              @SerializedName("description")
              @ColumnInfo(name = "description") val description:String,

              @SerializedName("isCompleted")
              @ColumnInfo(name = "isCompleted") val isCompleted:Int

): Serializable {
}