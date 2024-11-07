package edu.sci.agregator.ui.task01;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import edu.sci.agregator.R;

public class Task01Fragment extends Fragment {

    private Task01ViewModel mViewModel;

    public static Task01Fragment newInstance() {
        return new Task01Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task01, container, false);

        Button button = (Button) view.findViewById(R.id.buttonId3);
        EditText editText = (EditText) view.findViewById(R.id.editTextId2);
        CheckBox checkBox = (CheckBox) view.findViewById(R.id.checkBoxId5);

        button.setOnClickListener(v -> {
            String text = "NIE WPISANO TEKSTU";
            if(!editText.getText().toString().isEmpty()) {
                text = editText.getText().toString();
            }
            if(checkBox.isChecked()) {
                Toast.makeText(view.getContext(), text, Toast.LENGTH_SHORT).show();
            } else {
                Snackbar.make(view, text, Snackbar.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Task01ViewModel.class);
        // TODO: Use the ViewModel
    }
}