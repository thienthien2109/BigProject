package com.example.bigproject1.Fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bigproject1.Adapter.BookReVAdapter;
import com.example.bigproject1.MainActivity;
import com.example.bigproject1.Model.User;
import com.example.bigproject1.R;
import com.example.bigproject1.SQLite.SQLiteBookHelper;
import com.google.firebase.auth.FirebaseAuth;

public class FragmentUser extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private TextView tvUser;
    private Button btnSetting,btnLogOut;
    private BookReVAdapter adapter;
    private SQLiteBookHelper sqLiteBookHelper;
    FirebaseAuth auth ;

    private String mParam1;
    private String mParam2;

    public FragmentUser() {
    }

    public static FragmentUser newInstance(String param1, String param2) {
        FragmentUser fragment = new FragmentUser();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tvUser = view.findViewById(R.id.tvUser);
        btnSetting = view.findViewById(R.id.btnSetting);
        btnLogOut = view.findViewById(R.id.btnLogOut);

        auth = FirebaseAuth.getInstance();

        adapter = new BookReVAdapter();
        sqLiteBookHelper = new SQLiteBookHelper(getContext());

        User user = new User();
        tvUser.setText(user.getUserName());

        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"You Clicked Setting!",Toast.LENGTH_LONG);
            }
        });

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                auth.signOut();
                Intent intent = new Intent(getContext(), com.example.bigproject1.SignInActivity.class);
                startActivity(intent);
            }
        });

    }
}