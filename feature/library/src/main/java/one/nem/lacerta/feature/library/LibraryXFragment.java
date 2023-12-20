package one.nem.lacerta.feature.library;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

public class LibraryXFragment extends Fragment {

    // フラグメントが作成された際に呼ばれるメソッド
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // フラグメントに渡されたデータを取得
        if (getArguments() != null) {
            String receivedData = getArguments().getString("key_name");

            // ここで receivedData を使って何か処理を行う
        }
    }

    // Factory method for creating a new instance of the fragment
    public static LibraryXFragment newInstance(String data) {
        LibraryXFragment fragment = new LibraryXFragment();

        // フラグメントにデータを渡す
        Bundle args = new Bundle();
        args.putString("key_name", data);
        fragment.setArguments(args);

        return fragment;
    }
}
