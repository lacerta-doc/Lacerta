package one.nem.lacerta.feature.library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

public class LibraryDocFragment extends Fragment {

    private TextView textView;  // フィールドとして TextView を定義

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_library_file, container, false);

        textView = view.findViewById(R.id.textView);

        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フラグメントに渡されたデータを取得
        if (getArguments() != null) {
            String receivedData = getArguments().getString("key_name");

            // onCreateView メソッドで取得した TextView にデータをセットする
            if (textView != null) {
                textView.setText(receivedData);
            }
        }
    }

    // Factory method for creating a new instance of the fragment
    public static LibraryDocFragment newInstance(String data) {
        LibraryDocFragment fragment = new LibraryDocFragment();

        // フラグメントにデータを渡す
        Bundle args = new Bundle();
        args.putString("key_name", data);
        fragment.setArguments(args);

        return fragment;
    }
}
