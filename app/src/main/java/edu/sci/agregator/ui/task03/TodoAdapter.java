package edu.sci.agregator.ui.task03;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import edu.sci.agregator.R;
public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private List<String> todoList;
    private Context context;
    private TodoDatabaseHelper dbHelper;

    public TodoAdapter(Context context, List<String> todoList) {
        this.context = context;
        this.todoList = todoList;
        this.dbHelper = new TodoDatabaseHelper(context);
    }

    @Override
    public TodoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.task03_item, parent, false);
        return new TodoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TodoViewHolder holder, int position) {
        final String task = todoList.get(position);
        holder.taskTextView.setText(task);

        // Set up the delete button
        holder.deleteButton.setOnClickListener(v -> {
            dbHelper.deleteTodo(task);
            todoList.remove(position);
            notifyItemRemoved(position);
        });
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        TextView taskTextView;
        Button deleteButton;

        public TodoViewHolder(View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.todoText);
            deleteButton = itemView.findViewById(R.id.todoDelete);
        }
    }
}