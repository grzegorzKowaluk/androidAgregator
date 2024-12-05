package edu.sci.agregator.ui.task03;

import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.behavior.SwipeDismissBehavior;

import java.util.ArrayList;
import java.util.List;

import edu.sci.agregator.R;

public class Task03Fragment extends Fragment {

    private Task03ViewModel mViewModel;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;
    private EditText editTextTask;
    private Button buttonAddTask;
    private TodoDatabaseHelper dbHelper;
    private List<String> todoList;

    public static Task03Fragment newInstance() {
        return new Task03Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task03, container, false);

        // Initialize Views
        recyclerView = view.findViewById(R.id.todoList);
        editTextTask = view.findViewById(R.id.addTodoText);
        buttonAddTask = view.findViewById(R.id.addTodoButton);

        // Initialize Database Helper and List
        dbHelper = new TodoDatabaseHelper(view.getContext());
        todoList = new ArrayList<>();

        // Setup RecyclerView
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        todoAdapter = new TodoAdapter(view.getContext(), todoList);
        recyclerView.setAdapter(todoAdapter);

        // Load existing tasks
        loadTodos();

        // Handle Add Task button click
        buttonAddTask.setOnClickListener(v -> {
            String task = editTextTask.getText().toString().trim();
            if (!task.isEmpty()) {
                dbHelper.addTodo(task);
                todoList.add(task);
                todoAdapter.notifyItemInserted(todoList.size() - 1);
                editTextTask.setText(""); // Clear input field
            }
        });

        return view;
    }

    private void loadTodos() {
        todoList.clear();
        todoList.addAll(dbHelper.getAllTodos());
        todoAdapter.notifyDataSetChanged();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Task03ViewModel.class);
    }
}