package net.decenternet.technicalexam.adapter

import android.graphics.Paint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.task_list_item.view.*
import net.decenternet.technicalexam.R
import net.decenternet.technicalexam.data.TaskListener
import net.decenternet.technicalexam.model.Task2

class TaskListAdapter(private val tasks:List<Task2>) : RecyclerView.Adapter<TaskListAdapter.ViewHolder>(){

    private val onClickListener: View.OnClickListener
    var taskListener: TaskListener? = null

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as Task2
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val taskId: TextView = view.task_id
        val taskDescription: TextView = view.task_description
        val taskHolder : LinearLayout = view.task_holder
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.task_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tasks.size
    }

    override fun onBindViewHolder(p0: ViewHolder, position: Int) {
        val item = tasks[position]

        p0.taskHolder.setOnClickListener {
           taskListener!!.onEdit(item)
        }

        p0.taskId.text = "Task No. " + item.id.toString()

        if(item.isCompleted == 1){
            p0.taskDescription.paintFlags = Paint.STRIKE_THRU_TEXT_FLAG
            p0.taskDescription.text = item.description
        }else{
            p0.taskDescription.paintFlags = p0.taskDescription.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
            p0.taskDescription.text = item.description
        }




    }

}