package edu.sci.agregator.ui.task07;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.sci.agregator.R;
import edu.sci.agregator.ui.task04.Task04ViewModel;

public class Task07Fragment extends Fragment {

    private Task07ViewModel mViewModel;

    public static Task07Fragment newInstance() {
        return new Task07Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_task07, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Task07ViewModel.class);
        // TODO: Use the ViewModel
    }
}