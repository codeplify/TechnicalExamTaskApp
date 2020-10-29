package net.decenternet.technicalexam.data

import net.decenternet.technicalexam.model.Task2

interface TaskListener {
    fun onEdit(task: Task2)
    fun onDelete(task: Task2)
}