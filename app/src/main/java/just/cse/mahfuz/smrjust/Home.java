package just.cse.mahfuz.smrjust;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class Home extends AppCompatActivity {

    RadioGroup radioGroup;
    EditText number;
    Button search;

    String mynumber;
    String checkeditem = "myroom";

    FirebaseFirestore firebaseFirestore;

    String myurl, myname, mydept, myroll, mysession, myreg, mydob, mybg, mygender, myphone, myemail, myhName, myhStatus, myroom, myenrollment, myfName, mymName, myaddress, myemergency;

    String mycategory, uid;
    FirebaseAuth auth;

    ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        radioGroup = findViewById(R.id.radioGroup);
        number = findViewById(R.id.number);
        search = findViewById(R.id.search);

        firebaseFirestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        uid = auth.getUid();

        progressDialog = new ProgressDialog(Home.this);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {


                if (checkedId == R.id.room) {
                    number.setHint("Enter Room number");
                    checkeditem = "myroom";
                } else {
                    number.setHint("Enter Roll number");
                    checkeditem = "roll";
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mynumber = number.getText().toString();
                if (!TextUtils.isEmpty(mynumber)) {


                    if ("myroom".equals(checkeditem)) {
                        Intent intent = new Intent(Home.this, Room.class);
                        intent.putExtra("room", mynumber);
                        startActivity(intent);
                    } else {


                        progressDialog.setMessage("Please wait...");
                        progressDialog.show();

                        firebaseFirestore.collection("students").document(mynumber).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<DocumentSnapshot> task) {


                                progressDialog.dismiss();

                                myurl = (String) task.getResult().get("url");

                                myname = (String) task.getResult().get("name");
                                mydept = (String) task.getResult().get("dept");
                                myroll = (String) task.getResult().get("roll");
                                mysession = (String) task.getResult().get("session");
                                myreg = (String) task.getResult().get("reg");
                                mydob = (String) task.getResult().get("dob");
                                mybg = (String) task.getResult().get("bg");
                                mygender = (String) task.getResult().get("gender");
                                myphone = (String) task.getResult().get("phone");
                                myemail = (String) task.getResult().get("email");

                                myhName = (String) task.getResult().get("hName");
                                myhStatus = (String) task.getResult().get("hStatus");
                                myroom = (String) task.getResult().get("room");
                                myenrollment = (String) task.getResult().get("enrollment");

                                myfName = (String) task.getResult().get("fName");
                                mymName = (String) task.getResult().get("mName");
                                myaddress = (String) task.getResult().get("address");
                                myemergency = (String) task.getResult().get("emergency");


                                if (myroll != null) {

                                    final AlertDialog.Builder builder = new AlertDialog.Builder(Home.this);

                                    View view1 = LayoutInflater.from(Home.this).inflate(R.layout.custom_layout, null);
                                    builder.setView(view1);
                                    builder.setCancelable(true);
                                    final AlertDialog alertDialog = builder.create();


                                    ImageView img = view1.findViewById(R.id.Img);

                                    TextView name = view1.findViewById(R.id.name);
                                    TextView dept = view1.findViewById(R.id.dept);
                                    TextView roll = view1.findViewById(R.id.roll);
                                    TextView session = view1.findViewById(R.id.session);
                                    TextView reg = view1.findViewById(R.id.reg);
                                    TextView dob = view1.findViewById(R.id.dob);
                                    TextView bg = view1.findViewById(R.id.bg);
                                    TextView gender = view1.findViewById(R.id.gender);
                                    TextView phone = view1.findViewById(R.id.phone);
                                    TextView email = view1.findViewById(R.id.email);

                                    TextView hName = view1.findViewById(R.id.hName);
                                    TextView hStatus = view1.findViewById(R.id.hStatus);
                                    TextView room = view1.findViewById(R.id.room);
                                    TextView enrollment = view1.findViewById(R.id.enrollment);

                                    TextView fName = view1.findViewById(R.id.fName);
                                    TextView mName = view1.findViewById(R.id.mName);
                                    TextView address = view1.findViewById(R.id.address);
                                    TextView emergency = view1.findViewById(R.id.emergancy);


                                    Button viewTransaction = view1.findViewById(R.id.viewTransaction);
                                    Button close = view1.findViewById(R.id.close);


                                    Glide.with(Home.this)
                                            .load(myurl)
                                            .thumbnail(0.1f)
                                            .into(img);

                                    //img.setImageResource(R.drawable.passport);


                                    name.setText("Name : " + myname);
                                    dept.setText("Department : " + mydept);
                                    roll.setText("Roll : " + myroll);
                                    session.setText("Session :" + mysession);
                                    reg.setText("Registration No :" + myreg);

                                    dob.setText("Date Of Birth :" + mydob);
                                    bg.setText("Blood Group :" + myroom);
                                    gender.setText("Gender :" + mygender);
                                    phone.setText("Phone No :" + myphone);
                                    email.setText("Email :" + myemail);
                                    hName.setText("Hall Name :" + myhName);
                                    hStatus.setText("Hall Status :" + myhStatus);
                                    room.setText("Room No :" + myroom);
                                    enrollment.setText("Enrollment Date :" + myenrollment);

                                    fName.setText("Father's Name :" + myfName);
                                    mName.setText("Mother's Name :" + mymName);
                                    address.setText("Address :" + myaddress);
                                    emergency.setText("Emergency Contact : " + myemergency);


                                    close.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            alertDialog.dismiss();
                                        }
                                    });
                                    viewTransaction.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent(Home.this, transaction.class);
                                            intent.putExtra("roll", myroll);
                                            startActivity(intent);
                                        }
                                    });

                                    alertDialog.show();
                                } else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Home.this, "Couldn't find!!", Toast.LENGTH_SHORT).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                progressDialog.dismiss();
                            }
                        });


                    }
                } else {
                    Toast.makeText(Home.this, "Please Fill the required field", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
