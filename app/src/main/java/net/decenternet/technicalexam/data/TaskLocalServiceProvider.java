package net.decenternet.technicalexam.data;

import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import net.decenternet.technicalexam.db.TaskDao;
import net.decenternet.technicalexam.domain.Task;
import net.decenternet.technicalexam.model.Task2;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import kotlin.Unit;
import kotlin.coroutines.Continuation;

public class TaskLocalServiceProvider implements TaskLocalService {

    private final SharedPreferences sharedPreferences;
    private Collection<Task> localTasks;


    public TaskLocalServiceProvider(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
        localTasks = new Gson()
                .fromJson(
                        sharedPreferences.getString("tasks", "[]"),
                        new TypeToken<List<Task>>(){}.getType()
                );
    }

    public TaskLocalServiceProvider(TaskDao taskDao, SharedPreferences sharedPreferences){

        this.sharedPreferences = sharedPreferences;
    }

    @Override
    public void save(Task task) {
        ArrayList<Task> tasks = new ArrayList<>(localTasks);
        task.setId(getNextId());
        tasks.add(task);

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("tasks", new Gson().toJson(tasks));
        editor.apply();

        localTasks = tasks;
    }

    @Override
    public List<Task> findAll() {
        return new ArrayList<>(localTasks);
    }

    private Integer getNextId() {
        return localTasks.size() + 1;
    }
}
