package just.cse.mahfuz.smrjust;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class RoomActivity extends AppCompatActivity {

    String myroom;
    TextView room;

    TextView p1,p2,p3,p4;

    String img1="https://firebasestorage.googleapis.com/v0/b/fir-2-a7056.appspot.com/o/mahfuz.jpg?alt=media&token=c2167a49-a7c7-474b-a967-c822cdccd5bc";



    @Override
    protected void onCreate(Bundle savedInstanceState) {



        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        room=findViewById(R.id.room);
        p1=findViewById(R.id.p1);
        p2=findViewById(R.id.p2);
        p3=findViewById(R.id.p3);
        p4=findViewById(R.id.p4);

        myroom =getIntent().getExtras().getString("room");

        room.setText("RoomActivity No: "+myroom);




        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RoomActivity.this);

                View view1 = LayoutInflater.from(RoomActivity.this).inflate(R.layout.custom_layout, null);
                builder.setView(view1);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();


                ImageView img=view1.findViewById(R.id.Img);
                TextView name=view1.findViewById(R.id.name);
                TextView roll=view1.findViewById(R.id.roll);
                TextView reg=view1.findViewById(R.id.reg);
                TextView dept=view1.findViewById(R.id.dept);
                TextView phone=view1.findViewById(R.id.phone);


                Button close =view1.findViewById(R.id.close);

                Glide.with(RoomActivity.this)
                        .load(img1)
                        .thumbnail(0.1f)
                        .into(img);

                //img.setImageResource(R.drawable.passport);
                name.setText("Name : "+"Mahfuzur Rahman");
                roll.setText("RollActivity : "+"160121");
                reg.setText("Registration No : 1601016");
                dept.setText("Department : Computer Science & Engineering");
                phone.setText("Phone : 01773991774");


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });







        p2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RoomActivity.this);

                View view1 = LayoutInflater.from(RoomActivity.this).inflate(R.layout.custom_layout, null);
                builder.setView(view1);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();


                ImageView img=view1.findViewById(R.id.Img);
                TextView name=view1.findViewById(R.id.name);
                TextView roll=view1.findViewById(R.id.roll);
                TextView reg=view1.findViewById(R.id.reg);
                TextView dept=view1.findViewById(R.id.dept);
                TextView phone=view1.findViewById(R.id.phone);


                Button close =view1.findViewById(R.id.close);


                //img.setImageResource(R.drawable.passport);
                name.setText("Name : "+"Ashav Noman");
                roll.setText("RollActivity : "+"160136");
                reg.setText("Registration No : 1601036");
                dept.setText("Department : Computer Science & Engineering");
                phone.setText("Phone : 01815212155");


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });



        p3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RoomActivity.this);

                View view1 = LayoutInflater.from(RoomActivity.this).inflate(R.layout.custom_layout, null);
                builder.setView(view1);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();


                ImageView img=view1.findViewById(R.id.Img);
                TextView name=view1.findViewById(R.id.name);
                TextView roll=view1.findViewById(R.id.roll);
                TextView reg=view1.findViewById(R.id.reg);
                TextView dept=view1.findViewById(R.id.dept);
                TextView phone=view1.findViewById(R.id.phone);


                Button close =view1.findViewById(R.id.close);


                //img.setImageResource(R.drawable.passport);
                name.setText("Name : "+"Rabiul Islam");
                roll.setText("RollActivity : "+"160124");
                reg.setText("Registration No : 1601024");
                dept.setText("Department : Computer Science & Engineering");
                phone.setText("Phone : 01945151116");


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });





        p4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(RoomActivity.this);

                View view1 = LayoutInflater.from(RoomActivity.this).inflate(R.layout.custom_layout, null);
                builder.setView(view1);
                builder.setCancelable(true);
                final AlertDialog alertDialog = builder.create();


                ImageView img=view1.findViewById(R.id.Img);
                TextView name=view1.findViewById(R.id.name);
                TextView roll=view1.findViewById(R.id.roll);
                TextView reg=view1.findViewById(R.id.reg);
                TextView dept=view1.findViewById(R.id.dept);
                TextView phone=view1.findViewById(R.id.phone);


                Button close =view1.findViewById(R.id.close);


                //img.setImageResource(R.drawable.passport);
                name.setText("Name : "+"Anik Dey Joy");
                roll.setText("RollActivity : "+"160148");
                reg.setText("Registration No : 1601048");
                dept.setText("Department : Computer Science & Engineering");
                phone.setText("Phone : 01645646465");


                close.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                    }
                });

                alertDialog.show();
            }
        });

    }
}
