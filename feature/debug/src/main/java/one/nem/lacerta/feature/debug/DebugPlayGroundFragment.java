package one.nem.lacerta.feature.debug;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import one.nem.lacerta.data.repository.Documents;

import one.nem.lacerta.data.repository.SharedPref;

import one.nem.lacerta.data.model.shared_pref.enums.SharedPrefType;

import one.nem.lacerta.source.db.LacertaDatabase;

import javax.inject.Inject;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint // HiltのDIを有効にするアノテーション
public class DebugPlayGroundFragment extends Fragment {

    @Inject // DI
    Documents documents;

    @Inject // DI
    SharedPref sharedPref;

    @Inject
    LacertaDatabase lacertaDatabase; // TODO-rca: Repositoryを噛ませる

    public DebugPlayGroundFragment() {
        // Required empty public constructor
    }

    public static DebugPlayGroundFragment newInstance() {
        DebugPlayGroundFragment fragment = new DebugPlayGroundFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_debug_play_ground, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // ここがいままでのonCreateと同等のものです。
        // findViewById(R.id.hoge)とかは view.findViewById(R.id.hoge) と書き換える必要アリ
        // (viewは引数として受け取ってるviewなので、別メソッドに切り出したりするなら渡してあげる）

        // ShardPrefの要素数をトーストする例
        Toast.makeText(getContext(), "DBの要素数" + lacertaDatabase.repositoriesDao(), Toast.LENGTH_SHORT).show();
    }
}