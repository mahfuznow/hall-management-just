package just.cse.mahfuz.smrjust;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeActivity extends AppCompatActivity {

    Button search,profile;
    Button addStudent,addTransaction;
    Button pendingStudent,pendingTransaction;


    String mynumber;
    String checkeditem="myroom";

    FirebaseFirestore firebaseFirestore;

    String mycategory,uid;
    FirebaseAuth auth;

    ProgressDialog progressDialog;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_new);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        search=findViewById(R.id.search);
        profile=findViewById(R.id.profile);

        addStudent =findViewById(R.id.addStudent);
        addTransaction=findViewById(R.id.addTransaction);

        pendingStudent=findViewById(R.id.pendingAddStudent);
        pendingTransaction=findViewById(R.id.pendingTransaction);

        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        uid=auth.getUid();



        progressDialog= new ProgressDialog(HomeActivity.this);

        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                mycategory=task.getResult().getString("category");

                if ("admin".equals(mycategory)) {
                    addStudent.setVisibility(View.VISIBLE);
                    addTransaction.setVisibility(View.VISIBLE);

                    pendingStudent.setVisibility(View.VISIBLE);
                    pendingTransaction.setVisibility(View.VISIBLE);

                    progressDialog.dismiss();
                }
                else if ("provost".equals(mycategory)) {

                    pendingStudent.setVisibility(View.VISIBLE);
                    pendingTransaction.setVisibility(View.VISIBLE);

                    addStudent.setVisibility(View.VISIBLE);
                    addTransaction.setVisibility(View.VISIBLE);

                    progressDialog.dismiss();
                }
                else if ("general".equals(mycategory)) {
                    progressDialog.dismiss();

                }
                else if ("staff".equals(mycategory)) {
                    addStudent.setVisibility(View.VISIBLE);
                    addTransaction.setVisibility(View.VISIBLE);

                    progressDialog.dismiss();

                }
            }
        });





        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, SearchActivity.class));
            }
        });

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddStudentActivity.class));
            }
        });

        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, AddTransactionActivity.class));
            }
        });
        pendingStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this,PendingAddStudentActivity.class));
            }
        });
        pendingTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PendingTransactionActivity.class));
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ProfileActivity.class));
            }
        });
    }
}
