package net.decenternet.technicalexam.ui.tasks;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.lifecycle.ViewModelProviders;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import net.decenternet.technicalexam.R;
import net.decenternet.technicalexam.adapter.TaskListAdapter;
import net.decenternet.technicalexam.data.TaskListener;
import net.decenternet.technicalexam.domain.Task;
import net.decenternet.technicalexam.model.Task2;
import net.decenternet.technicalexam.viewmodel.TaskViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TasksActivity extends AppCompatActivity  implements TasksContract.View , TaskListener {

    private TasksContract.Presenter presenter;
    private FloatingActionButton floatingActionButton;
    private Button button;
    private LinearLayoutManager linearLayoutManager;
    private RecyclerView recyclerViewTaskList;
    private ArrayList<Task2> taskList;
    private TaskViewModel taskViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_activity);

        floatingActionButton = findViewById(R.id.float_create_task);
        recyclerViewTaskList = findViewById(R.id.recycler_view_task);
        taskList = new ArrayList<>();

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPrompt(false, null);
            }
        });


        /**
         * Finish this simple task recording app.
         * The following are the remaining todos for this project:
         * 1. Make sure all defects are fixed.
         * 2. Showing a dialog to add/edit the task.
         * 3. Allowing the user to toggle it as completed.
         * 4. Allowing the user to delete a task.
         *
         */

        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewTaskList.setLayoutManager(linearLayoutManager);
        TaskListAdapter listAdapter = new TaskListAdapter(taskList);
        listAdapter.setTaskListener(this);
        recyclerViewTaskList.setAdapter(listAdapter);


        taskViewModel = ViewModelProviders.of(this).get(TaskViewModel.class);
        taskViewModel.getTasks().observe(this, new Observer<List<Task2>>() {
            @Override
            public void onChanged(List<Task2> task2s) {
                taskList.clear();
                taskList.addAll(task2s);
                recyclerViewTaskList.getAdapter().notifyDataSetChanged();
            }
        });

    }

    private void showPrompt(final boolean isEditOrDelete, final Task2 task){

        LayoutInflater li = LayoutInflater.from(getBaseContext());
        View promptView = li.inflate(R.layout.task_prompt_layout, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(promptView);


        final EditText taskInput = (EditText) promptView.findViewById(R.id.editTextDialogUserInput);
        final TextView tvTitle = (TextView) promptView.findViewById(R.id.tv_title);

        String title = "";

        if(isEditOrDelete == true){
            title = "Update Task";
        }else{
            title = "Add Task";
        }

        if(task != null){
            taskInput.setText(task.getDescription());
        }

        tvTitle.setText(title);

        alertDialogBuilder.setCancelable(false).setPositiveButton( task == null ? "Save":"Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Random random = new Random();
                int randomNumber = random.nextInt(999) + 1;
                if(isEditOrDelete == true && task != null){
                    taskViewModel.update(new Task2(task.getId(), taskInput.getText().toString(), 0));
                }

                if(task == null){
                    taskViewModel.save(new Task2(randomNumber, taskInput.getText().toString(),0));
                }
            }
        }).setNegativeButton(isEditOrDelete == true ? "complete":"cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                if(task != null){
                    taskViewModel.update(new Task2(task.getId(), task.getDescription(), 1));
                }else{
                }
            }
        });

        if(isEditOrDelete == true && task != null){
            alertDialogBuilder.setNeutralButton("Delete", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    taskViewModel.delete(task);
                }
            });
        }

        alertDialogBuilder.show();
    }

    @Override
    public void displayTasks(List<Task> tasks) {

    }

    @Override
    public void addTaskToList(Task task) {

    }

    @Override
    public void removeTaskFromList(Task task) {

    }

    @Override
    public void updateTaskInList(Task task) {

    }

    @Override
    public void popupTaskAddingDialog() {

    }

    @Override
    public void popupTaskEditorDialog(Task task) {

    }

    @Override
    public void onEdit(@NotNull Task2 task) {
        //Toast.makeText(this, task.getDescription(), Toast.LENGTH_LONG).show();
        showPrompt(true, task);
    }

    @Override
    public void onDelete(@NotNull Task2 task) {

    }
}
