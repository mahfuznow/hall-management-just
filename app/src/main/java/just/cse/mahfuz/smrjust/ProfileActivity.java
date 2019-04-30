package just.cse.mahfuz.smrjust;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class ProfileActivity extends AppCompatActivity {

    FirebaseFirestore firebaseFirestore;

    ImageView img;
    TextView name,designation,dept,phone,email,enrollment;
    String myimg,myname,mydesignation,mydept,myphone,myemail,myenrollment;

    String mycategory,uid;
    FirebaseAuth auth;

    Button logOut;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        img=findViewById(R.id.Img);
        name=findViewById(R.id.name);
        designation=findViewById(R.id.designation);
        dept=findViewById(R.id.dept);
        phone=findViewById(R.id.phone);
        email=findViewById(R.id.email);
        enrollment=findViewById(R.id.enrollment);

        logOut =findViewById(R.id.logOut);


        firebaseFirestore=FirebaseFirestore.getInstance();
        auth=FirebaseAuth.getInstance();
        uid=auth.getUid();

        progressDialog= new ProgressDialog(ProfileActivity.this);

        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                myimg=task.getResult().getString("img");
                myname=task.getResult().getString("name");
                mydesignation=task.getResult().getString("designation");
                mydept=task.getResult().getString("dept");
                myphone=task.getResult().getString("phone");
                myemail=task.getResult().getString("email");
                myenrollment=task.getResult().getString("enrollment");



                Glide.with(ProfileActivity.this)
                        .load(myimg)
                        .thumbnail(0.1f)
                        .into(img);

                name.setText(myname);
                designation.setText(mydesignation);
                dept.setText(mydept);
                phone.setText(myphone);
                email.setText(myemail);
                enrollment.setText(myenrollment);


                progressDialog.dismiss();
            }
        });

        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Logging out....");
                progressDialog.show();
                auth.signOut();
                finish();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }
}
