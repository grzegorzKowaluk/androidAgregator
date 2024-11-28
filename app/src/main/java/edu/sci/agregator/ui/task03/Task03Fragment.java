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

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import com.google.android.material.behavior.SwipeDismissBehavior;

import java.util.ArrayList;

import edu.sci.agregator.R;

public class Task03Fragment extends Fragment {

    private Task03ViewModel mViewModel;
    private TodoListSQLHelper todoListSQLHelper;

    public static Task03Fragment newInstance() {
        return new Task03Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task03, container, false);
        final ArrayList<String> list = new ArrayList<>();
        final MyCustomAdapter adapter = new MyCustomAdapter(view.getContext(), list);
        ImageButton fab = view.findViewById(R.id.fab_image_button);

        SwipeDismissBehavior.OnDismissListener touchListener = new SwipeDismissBehavior.OnDismissListener() {
            @Override
            public void onDismiss(View v) {
                String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
                        " WHERE " + TodoListSQLHelper._ID+ " = '" + v.getId() + "'";

                todoListSQLHelper = new TodoListSQLHelper(view.getContext());
                SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
                sqlDB.execSQL(deleteTodoItemSql);
            }

            @Override
            public void onDragStateChanged(int i) {

            }
        };

        fab.setOnClickListener(v -> {
            AlertDialog.Builder todoTaskBuilder = new AlertDialog.Builder(view.getContext());
            todoTaskBuilder.setTitle("Add a List item.");
            todoTaskBuilder.setMessage("Describe the item.");
            final EditText todoET = new EditText(view.getContext());
            todoTaskBuilder.setView(todoET);
            todoTaskBuilder.setPositiveButton("Add Item", (dialogInterface, i) -> {
                String todoTaskInput = todoET.getText().toString();
                todoListSQLHelper = new TodoListSQLHelper(view.getContext());
                SQLiteDatabase sqLiteDatabase = todoListSQLHelper.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.clear();

                values.put(TodoListSQLHelper.COL1_TASK, todoTaskInput);
                sqLiteDatabase.insertWithOnConflict(TodoListSQLHelper.TABLE_NAME, null, values, SQLiteDatabase.CONFLICT_IGNORE);

                list.add(todoTaskInput);
                adapter.notifyDataSetChanged();
            });

            todoTaskBuilder.setNegativeButton("Cancel", null);

            todoTaskBuilder.create().show();
        });
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Task03ViewModel.class);
    }
}