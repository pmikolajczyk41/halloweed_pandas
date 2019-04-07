package com.example.studly;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Main fragment for class of logging.
 */


public class LoginFragment extends Fragment {
    private Button signUp;
    private Button signIn;
    private TextView login;
    private TextView password;
    //private JsonService jsonService;


    public LoginFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_login, container, false);

        login = rootView.findViewById(R.id.titleAdded);
        password = rootView.findViewById(R.id.password);
        signUp = rootView.findViewById(R.id.sign_up);
        signUp.setOnClickListener(v -> {
            String emailS;
            String passwordS;
            emailS = login.getText().toString();
            passwordS = password.getText().toString();

            Bundle bundle = new Bundle();
            bundle.putString("email", emailS);
            bundle.putString("password", passwordS);

            SignUpFragment signUpFragment = new SignUpFragment();
            signUpFragment.setArguments(bundle);

            FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fragment_container, signUpFragment);
            onSaveInstanceState(savedInstanceState);
            fragmentTransaction.commit();

        });
        signIn = rootView.findViewById(R.id.sign_in);
        signIn.setOnClickListener(v -> {
            if (TextUtils.isEmpty(login.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
                Toast.makeText(getContext(), "fields cannot be empty", Toast.LENGTH_SHORT).show();
                return;
            }
            signIn.setEnabled(false);
        });
        return rootView;
    }


}