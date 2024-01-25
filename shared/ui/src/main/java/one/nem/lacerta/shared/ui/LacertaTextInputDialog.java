package one.nem.lacerta.shared.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LacertaTextInputDialog#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LacertaTextInputDialog extends Fragment {

    String title;
    String message;

    public LacertaTextInputDialog() {
        // Required empty public constructor
    }

    public static LacertaTextInputDialog newInstance(String title, String message) {
        LacertaTextInputDialog fragment = new LacertaTextInputDialog();
        Bundle args = new Bundle();
        args.putString("title", title);
        args.putString("message", message);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            title = getArguments().getString("title");
            message = getArguments().getString("message");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lacerta_text_input_dialog, container, false);
    }
}