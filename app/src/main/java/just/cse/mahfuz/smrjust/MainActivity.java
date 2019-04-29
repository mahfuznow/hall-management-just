package just.cse.mahfuz.smrjust;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MainActivity extends AppCompatActivity {

    Button login;
    EditText email,pass;
    String myemail, mypass;
    ProgressDialog dialog;
    FirebaseAuth mauth;


    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mauth.getCurrentUser();
        if (currentUser!=null){
            Intent intent = new Intent(MainActivity.this, HomeNew.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        login=findViewById(R.id.login);
        email =findViewById(R.id.email);
        pass=findViewById(R.id.pass);

        dialog = new ProgressDialog(MainActivity.this);

        FirebaseApp.initializeApp(MainActivity.this);
        mauth=FirebaseAuth.getInstance();



        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dialog.setMessage("logging In ...");
                dialog.show();

                myemail = email.getText().toString().trim();
                mypass = pass.getText().toString().trim();


                if ((!TextUtils.isEmpty(myemail)) && (!TextUtils.isEmpty(mypass))) {

                    mauth.signInWithEmailAndPassword(myemail, mypass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                dialog.dismiss();
                                Intent intent = new Intent(MainActivity.this, HomeNew.class);
                                startActivity(intent);

                            } else if (!isNetworkAvailable()) {
                                Toast.makeText(MainActivity.this, "Please check your internet connection and try again", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            } else {
                                Toast.makeText(MainActivity.this, "LogIn failed. Please input correct Email & password", Toast.LENGTH_SHORT).show();
                                dialog.dismiss();
                            }
                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "Please input both Email & password", Toast.LENGTH_SHORT).show();
                    dialog.dismiss();
                }


            }
        });


    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Home.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

}
