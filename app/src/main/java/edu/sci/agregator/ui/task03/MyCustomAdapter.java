package edu.sci.agregator.ui.task03;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import edu.sci.agregator.R;

public class MyCustomAdapter extends BaseAdapter {
    private ArrayList<String> mListItems;
    private LayoutInflater mLayoutInflater;

    private TodoListSQLHelper todoListSQLHelper;
    public MyCustomAdapter(Context context, ArrayList<String> arrayList){
        mListItems = arrayList;
        //get the layout inflater
        mLayoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        //getCount() represents how many items are in the list
        return mListItems.size();
    }
    @Override
    //get the data of an item from a specific position
    //i represents the position of the item in the list
    public Object getItem(int i) {
        return null;
    }
    @Override
    //get the position id of the item from the list
    public long getItemId(int i) {
        return 0;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        // create a ViewHolder reference
        ViewHolder holder;
        //check to see if the reused view is null or not, if is not null then reuse it
        if (view == null) {
            holder = new ViewHolder();
            view = mLayoutInflater.inflate(R.layout.due, null);
            holder.itemName = (TextView) view.findViewById(R.id.due_text_view);
            ImageButton button =  view.findViewById(R.id.delete_button);
            button.setOnClickListener(buttonView->{
                Toast.makeText(viewGroup.getContext(), "TEST", Toast.LENGTH_SHORT).show();
//                View v = (View) buttonView.getParent();
//                TextView todoTV = (TextView) v.findViewById(R.id.due_text_view);
//                String todoTaskItem = todoTV.getText().toString();
//
//                String deleteTodoItemSql = "DELETE FROM " + TodoListSQLHelper.TABLE_NAME +
//                        " WHERE " + TodoListSQLHelper.COL1_TASK + " = '" + todoTaskItem + "'";
//
//                todoListSQLHelper = new TodoListSQLHelper(v.getContext());
//                SQLiteDatabase sqlDB = todoListSQLHelper.getWritableDatabase();
//                sqlDB.execSQL(deleteTodoItemSql);
//                sqlDB.close();
            });

            holder.imageButton = button;

            // the setTag is used to store the data within this view
            view.setTag(holder);
        } else {
            // the getTag returns the viewHolder object set as a tag to the view
            holder = (ViewHolder)view.getTag();
        }
        //get the string item from the position "position" from array list to put it on the TextView
        String stringItem = mListItems.get(position);
        if (stringItem != null) {
            if (holder.itemName != null) {
                //set the item name on the TextView
                holder.itemName.setText(stringItem);
            }
        }
        //this method must return the view corresponding to the data at the specified position.
        return view;
    }

    /**
     * Static class used to avoid the calling of "findViewById" every time the getView() method is called,
     * because this can impact to your application performance when your list is too big. The class is static so it
     * cache all the things inside once it's created.
     */
    private static class ViewHolder {
        protected TextView itemName;
        protected ImageButton imageButton;
    }
}
