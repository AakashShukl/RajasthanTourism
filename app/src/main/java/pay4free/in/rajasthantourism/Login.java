package pay4free.in.rajasthantourism;

/**
 * Created by AAKASH on 07-10-2017.
 */

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;



public class Login extends AppCompatActivity {

    EditText MobileNumber,OTPEditview;
    Button Submit,OTPButton,Resend;
    TextView Textview,Otp;
    // [START declare_auth]
    private FirebaseAuth mAuth;

    // [END declare_auth]
    ProgressDialog progressDialog;
    boolean mVerificationInProgress = false;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    FirebaseUser firebaseUser;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDialog=new ProgressDialog(this);
        MobileNumber = (EditText) findViewById(R.id.mobile);
        Submit = (Button) findViewById(R.id.btn2);

        mAuth = FirebaseAuth.getInstance();
        progressDialog.setMessage("Sending OTP");

        if(firebaseUser!=null)
        {
            startActivity(new Intent(Login.this,MainActivity.class));


        }

        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                //Toast.makeText(logup.this,"verifucation done"+ phoneAuthCredential,Toast.LENGTH_LONG).show();

                firebaseUser=mAuth.getCurrentUser();
                startActivity(new Intent(Login.this,MainActivity.class));


                finish();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(Login.this,"verifucation fail",Toast.LENGTH_LONG).show();
                if (e instanceof FirebaseAuthInvalidCredentialsException) {
                    // Invalid request
                    // [START_EXCLUDE]
                    Toast.makeText(Login.this,"invalid mob no",Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                } else if (e instanceof FirebaseTooManyRequestsException) {
                    // The SMS quota for the project has been exceeded
                    // [START_EXCLUDE]
                    Toast.makeText(Login.this,"quta over" ,Toast.LENGTH_LONG).show();
                    // [END_EXCLUDE]
                }
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                // The SMS verification code has been sent to the provided phone number, we
                // now need to ask the user to enter the code and then construct a credential
                // by combining the code with a verification ID.
                //Log.d(TAG, "onCodeSent:" + verificationId);
                Toast.makeText(Login.this,"Verification code sent to mobile",Toast.LENGTH_LONG).show();
                // Save verification ID and resending token so we can use them later
                mVerificationId = verificationId;
                mResendToken = token;
                MobileNumber.setVisibility(View.GONE);
                Submit.setVisibility(View.GONE);
                Textview.setVisibility(View.GONE);
                progressDialog.dismiss();
                OTPButton.setVisibility(View.VISIBLE);

                Resend.setVisibility(View.VISIBLE);
                OTPEditview.setVisibility(View.VISIBLE);
                Otp.setVisibility(View.VISIBLE);
                progressDialog.setMessage("Resending OTP");
                // ...
            }
        };



        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        "+91"+MobileNumber.getText().toString(),        // Phone number to verify
                        60,                 // Timeout duration
                        TimeUnit.SECONDS,   // Unit of timeout
                        Login.this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
            }

        });

        OTPButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, OTPEditview.getText().toString());
                signInWithPhoneAuthCredential(credential);
            }
        });
        Resend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                resendVerificationCode(MobileNumber.getText().toString(), mResendToken);
                Toast.makeText(getApplicationContext(),"OTP was sent",Toast.LENGTH_SHORT).show();
                progressDialog.dismiss();
            }
        });

    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithCredential:success");
                            Toast.makeText(Login.this, "Verification done", Toast.LENGTH_LONG).show();

                            startActivity(new Intent(Login.this,MainActivity.class));
                            firebaseUser = task.getResult().getUser();

                            finish();
                            // ...
                        } else {
                            // Sign in failed, display a message and update the UI
                            //Log.w(TAG, "signInWithCredential:failure", task.getException());
                            if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                                Toast.makeText(Login.this, "Verification failed code invalid", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                });
    }

    private void resendVerificationCode(String phone,
                                        PhoneAuthProvider.ForceResendingToken token) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91"+MobileNumber.getText().toString(),        // Phone number to verify
                60,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                Login.this,               // Activity (for callback binding)
                mCallbacks,token);        // OnVerificationStateChangedCallbacks
    }         // ForceResendingToken from callbacks


}


