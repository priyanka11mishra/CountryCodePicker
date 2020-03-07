package com.example.myapplication;



import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class LoginActivity extends Activity {
    FirebaseAuth auth;
    EditText e1,e2;

    PhoneAuthProvider.OnVerificationStateChangedCallbacks mcallbacks;
    String verify;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        e1=(EditText)findViewById(R.id.NumberText);
        e2=(EditText)findViewById(R.id.OTPText);
        auth=FirebaseAuth.getInstance();
        mcallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            }

            @Override
            public void onVerificationFailed(FirebaseException e) {

            }

            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verify=s;
                Toast.makeText(getApplicationContext(),"code sent",Toast.LENGTH_LONG).show();
            }
        };


    }

    public void sendsms(View v)
    {
        String no=e1.getText().toString();
        PhoneAuthProvider.getInstance().verifyPhoneNumber(no,60, TimeUnit.SECONDS,this,mcallbacks);

    }

    public void signin(PhoneAuthCredential Credential)
    {
        auth.signInWithCredential(Credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_LONG).show();
                }

            }
        });


    }

    public void verify(View v)
    {
        String input_code=e2.getText().toString();
        if(verify !=null)
        {
            verifyphonenumber(verify,input_code);


        }
    }

    public void verifyphonenumber(String vc,String inputcode)
    {
        PhoneAuthCredential credential=PhoneAuthProvider.getCredential(verify,inputcode);
        signin(credential);

    }
}
