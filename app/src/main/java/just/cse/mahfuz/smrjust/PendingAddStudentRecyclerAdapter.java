package just.cse.mahfuz.smrjust;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingAddStudentRecyclerAdapter extends RecyclerView.Adapter<PendingAddStudentRecyclerAdapter.myViewHolder> {
    Context context;
    List<PendingAddStudentModel> pendingAddStudentModels;
    String mytime, myurl, myname, mydept, myroll, mysession, myreg, mydob, mybg, mygender, myphone, myemail, myhName, myhStatus, myroom, myenrollment, myfName, mymName, myaddress, myemergency;
    String myshortname, mycategory;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String uid;
    ProgressDialog progressDialog;

    public PendingAddStudentRecyclerAdapter(Context c, List<PendingAddStudentModel> tm) {
        context = c;
        pendingAddStudentModels = tm;

        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        uid = firebaseAuth.getUid();
        progressDialog = new ProgressDialog(context);

    }

    @NonNull
    @Override
    public PendingAddStudentRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.pending_add_student_row, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingAddStudentRecyclerAdapter.myViewHolder myViewHolder, int i) {


        mytime = pendingAddStudentModels.get(i).getTime();

        myurl = pendingAddStudentModels.get(i).getUrl();

        myname = pendingAddStudentModels.get(i).getfName();
        mydept = pendingAddStudentModels.get(i).getDept();
        myroll = pendingAddStudentModels.get(i).getRoll();
        mysession = pendingAddStudentModels.get(i).getSession();
        myreg = pendingAddStudentModels.get(i).getReg();
        mydob = pendingAddStudentModels.get(i).getDob();

        mybg = pendingAddStudentModels.get(i).getBg();
        mygender = pendingAddStudentModels.get(i).getGender();
        myphone = pendingAddStudentModels.get(i).getPhone();
        myemail = pendingAddStudentModels.get(i).getEmail();
        myhName = pendingAddStudentModels.get(i).gethName();
        myhStatus = pendingAddStudentModels.get(i).gethStatus();
        myroom = pendingAddStudentModels.get(i).getRoom();

        myenrollment = pendingAddStudentModels.get(i).getEnrollment();
        myfName = pendingAddStudentModels.get(i).getfName();
        mymName = pendingAddStudentModels.get(i).getmName();
        myemergency = pendingAddStudentModels.get(i).getEmergency();


        myViewHolder.roll.setText(myroll);
        myViewHolder.dept.setText(mydept);
        myViewHolder.session.setText(mysession);
        myViewHolder.name.setText(myname);


        if (i % 2 == 0) {
            myViewHolder.roll.setBackgroundResource(R.drawable.table_border_colored);
            //myViewHolder.roll.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.dept.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.session.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.name.setBackgroundResource(R.drawable.table_border_colored);

        }

        myViewHolder.details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(context);

                View view1 = LayoutInflater.from(context).inflate(R.layout.custom_layout, null);
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
                Button approve = view1.findViewById(R.id.approve);

                viewTransaction.setVisibility(View.GONE);
                approve.setVisibility(View.VISIBLE);

                Button close = view1.findViewById(R.id.close);


                Glide.with(context)
                        .load(myurl)
                        .thumbnail(0.1f)
                        .into(img);

                name.setText("Name : " + myname);
                dept.setText("Department : " + mydept);
                roll.setText("RollActivity : " + myroll);
                session.setText("Session :" + mysession);
                reg.setText("Registration No :" + myreg);

                dob.setText("Date Of Birth :" + mydob);
                bg.setText("Blood Group :" + myroom);
                gender.setText("Gender :" + mygender);
                phone.setText("Phone No :" + myphone);
                email.setText("Email :" + myemail);
                hName.setText("Hall Name :" + myhName);
                hStatus.setText("Hall Status :" + myhStatus);
                room.setText("RoomActivity No :" + myroom);
                enrollment.setText("Enrollment Date :" + myenrollment);

                fName.setText("Father's Name :" + myfName);
                mName.setText("Mother's Name :" + mymName);
                address.setText("Address :" + myaddress);
                emergency.setText("Emergency Contact : " + myemergency);


                approve.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        approve();
                    }
                });

                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }

        });

        myViewHolder.approval.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                approve();
            }
        });
    }

    @Override
    public int getItemCount() {

        return pendingAddStudentModels.size();
    }


    class myViewHolder extends RecyclerView.ViewHolder {
        TextView roll, dept, session, name, details, approval;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            roll = itemView.findViewById(R.id.roll);
            dept = itemView.findViewById(R.id.dept);
            session = itemView.findViewById(R.id.session);
            name = itemView.findViewById(R.id.name);
            details = itemView.findViewById(R.id.details);
            approval = itemView.findViewById(R.id.approve);

        }
    }


    public  void  approve() {
        progressDialog.setMessage("Approving...");
        progressDialog.show();

        firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                myshortname = task.getResult().getString("shortname");
                mycategory = task.getResult().getString("category");

                Map<String, Object> setData = new HashMap<>();

                setData.put("time", String.valueOf(System.currentTimeMillis()));

                setData.put("url", myurl);
                setData.put("name", myname);
                setData.put("dept", mydept);
                setData.put("roll", myroll);
                setData.put("session", mysession);
                setData.put("reg", myreg);
                setData.put("dob", mydob);
                setData.put("bg", mybg);
                setData.put("gender", mygender);
                setData.put("phone", myphone);
                setData.put("email", myemail);

                setData.put("hName", myhName);
                setData.put("hStatus", myhStatus);
                setData.put("room", myroom);
                setData.put("enrollment", myenrollment);

                setData.put("fName", myfName);
                setData.put("mName", mymName);
                setData.put("address", myaddress);
                setData.put("emergency", myenrollment);

                setData.put("authorize", myshortname + mycategory);


                firebaseFirestore.collection("students").document(myroll).set(setData).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        firebaseFirestore.collection("pendingAddStudents").whereEqualTo("time", mytime).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    for (DocumentSnapshot document : task.getResult()) {
                                        firebaseFirestore.collection("pendingAddStudents").document(document.getId()).delete();
                                    }
                                } else {
                                }
                            }
                        });
                    }
                });

                progressDialog.dismiss();
            }
        });
    }
}



