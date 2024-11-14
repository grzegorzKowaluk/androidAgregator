package edu.sci.agregator.ui.task02;

import androidx.annotation.DrawableRes;
import androidx.lifecycle.ViewModelProvider;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import edu.sci.agregator.R;

public class Task02Fragment extends Fragment {

    private Task02ViewModel mViewModel;
    private int[] photos = {R.drawable.task02_1, R.drawable.task02_2, R.drawable.task02_3, R.drawable.task02_4, R.drawable.task02_5, R.drawable.task02_6, R.drawable.task02_7};
    private String[] texts = {
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec id rutrum justo. Sed eu mi purus. Quisque dignissim, diam ornare dapibus mollis, justo arcu dignissim felis, vitae sodales risus mauris eget metus. In egestas lectus nec vestibulum facilisis. Donec egestas leo id diam mattis, vitae laoreet lectus volutpat. Mauris tincidunt libero purus, sit amet eleifend est suscipit at. Sed non dapibus quam. Aliquam aliquam magna eget ante rutrum, non vulputate orci mollis. Donec a risus eget nulla ornare gravida in quis nunc. Aenean non ipsum nibh.",
            "Donec ullamcorper tortor nec ultricies dictum. Donec cursus turpis tempor urna laoreet condimentum. Aenean semper diam justo, at fringilla quam sagittis sit amet. Mauris lobortis dapibus lacus at ornare. Mauris tellus eros, placerat id velit id, ultrices aliquam mi. Aenean felis elit, vulputate eget volutpat eget, feugiat nec felis. Vivamus at ligula auctor, iaculis lorem sed, porttitor felis.",
            "Curabitur hendrerit nulla enim, ut sodales sem placerat ac. Maecenas scelerisque velit eget varius ultrices. Integer non posuere ex. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Cras a ultrices lorem, vitae ultrices arcu. Ut ac orci pharetra, eleifend felis a, volutpat turpis. In hac habitasse platea dictumst.",
            "Donec ut placerat metus. Morbi tincidunt, lorem non dignissim bibendum, ex dolor dictum augue, eu posuere elit erat sed sem. Etiam sed arcu sagittis, volutpat quam eget, tempor orci. Nulla sagittis rutrum laoreet. Nam maximus ipsum ut aliquet aliquam. Nam pretium tincidunt sollicitudin. Quisque in ultricies est. Integer euismod eget erat non congue. In bibendum feugiat velit, ut sagittis ipsum feugiat in.",
            "Nam orci leo, varius eget eros ac, pretium hendrerit felis. Mauris sit amet augue quis arcu vehicula condimentum vel vel quam. Curabitur vitae quam vel mi gravida consequat vel quis lorem. Etiam at neque aliquam, elementum velit id, tristique nunc. Sed quis pulvinar lectus, sit amet imperdiet ipsum. In hac habitasse platea dictumst. Maecenas vestibulum viverra urna, et vestibulum elit euismod ac.",
            "Proin volutpat a odio eget suscipit. Integer consectetur lacus non tortor maximus, at dignissim leo commodo. Morbi tempor diam eu pharetra bibendum. Mauris rutrum maximus nibh, at ornare elit. In consequat justo iaculis, condimentum massa non, efficitur nunc. Phasellus vel placerat ante. Maecenas id lacus a augue blandit condimentum vitae vel est. ",
            "Nullam nunc lectus, dapibus id imperdiet ac, pulvinar vel felis. Cras egestas magna elit, id aliquam nulla congue sit amet. Suspendisse potenti. Vestibulum tempus vehicula sem, quis vulputate tortor suscipit sit amet. Aliquam at lacus felis. Pellentesque tempus lorem et quam viverra, eu maximus purus viverra."
    };
    private int shownPhoto = 0;

    public static Task02Fragment newInstance() {
        return new Task02Fragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_task02, container, false);

        Button prevButton = view.findViewById(R.id.buttonPrevious);
        Button nextButton = view.findViewById(R.id.buttonNext);
        ImageView imageView = view.findViewById(R.id.imageViewCarousel);
        TextView textView = view.findViewById(R.id.textViewContainer);

        imageView.setImageResource(photos[shownPhoto]);
        textView.setText(texts[shownPhoto]);
        prevButton.setEnabled(false);

        prevButton.setOnClickListener(v->{
            if (shownPhoto == 0) {
                prevButton.setEnabled(false);
            } else {
                nextButton.setEnabled(true);
                shownPhoto--;
                imageView.setImageResource(photos[shownPhoto]);
                textView.setText(texts[shownPhoto]);
            }
        });

        nextButton.setOnClickListener(v->{
            if (shownPhoto == photos.length - 1) {
                nextButton.setEnabled(false);
            } else {
                prevButton.setEnabled(true);
                shownPhoto++;
                imageView.setImageResource(photos[shownPhoto]);
                textView.setText(texts[shownPhoto]);
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(Task02ViewModel.class);
        // TODO: Use the ViewModel
    }
}