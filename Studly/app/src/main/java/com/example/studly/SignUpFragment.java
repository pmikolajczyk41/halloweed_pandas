package com.example.studly;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;



/**
 *  Main fragment for class of singing up.
 */

public class SignUpFragment extends Fragment {
    private EditText mLogin;
    private EditText mPassword;
    private EditText mPasswordConfirmation;
    private EditText mEmail;
    private EditText mName;
    private EditText mSurname;
    private Button mSignUp;
    //private JsonService jsonService;

    public SignUpFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        //mAPIService = ApiUtils.getAPIService();


        mLogin = rootView.findViewById(R.id.sing_up_login_edittext);
        mPassword = rootView.findViewById(R.id.sing_up_password_edittext);
        mPasswordConfirmation = rootView.findViewById(R.id.sing_up_password_confirm_edittext);
        mEmail = rootView.findViewById(R.id.sing_up_email_textedit);
        mName = rootView.findViewById(R.id.sing_up_first_name_edittext);
        mSurname = rootView.findViewById(R.id.sing_up_last_name_edittex);
        mSignUp = rootView.findViewById(R.id.sign_up_button);
        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SignUpFragment.this.validData()) {
                    String name = mName.getText().toString() + " " + mSurname.getText().toString();
                    //json
                }
            }
        });



        return rootView;
    }


    private boolean validData(){
        if(!(mLogin.getText().toString().trim().length() > 0)){
            showToast("empty");
            return false;
        }
        if(mPassword.getText().toString().trim().length() > 0 && mPasswordConfirmation.getText().toString().trim().length() > 0){
            if(!mPassword.getText().toString().equals(mPasswordConfirmation.getText().toString()))
                showToast("different passwords");
            else{
                if(mPassword.getText().toString().length()<8)
                    showToast("too short");
            }

        }else{
            showToast("empty");
            return false;
        }
        if(!(mEmail.getText().toString().trim().length() > 0)){
            showToast("empty email");
            return false;
        }
        if(!(mName.getText().toString().trim().length() > 0)){
            showToast("empty email");
            return false;
        }
        return true;

    }
    private void showToast(String message){
        Toast toast = Toast.makeText(getContext(), message, Toast.LENGTH_SHORT);
        toast.show();
    }
}