package just.cse.mahfuz.smrjust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class addTransaction extends AppCompatActivity {

    EditText date,roll,ref,purpose,amount;
    String mytime,myroll,myref,mypurpose,myamount;
    Button add;
    CheckBox admit,idcard,seat,meal,fine;
    FirebaseFirestore firebaseFirestore;
    Spinner hall;
    String myhall;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


        date=findViewById(R.id.date);
        roll=findViewById(R.id.roll);
        ref=findViewById(R.id.ref);
        amount=findViewById(R.id.amount);
        add=findViewById(R.id.add);

        admit=findViewById(R.id.admit);
        idcard=findViewById(R.id.id_card);
        seat=findViewById(R.id.seat);
        meal=findViewById(R.id.meal);
        fine=findViewById(R.id.fine);

        hall=findViewById(R.id.hall);

        firebaseFirestore=FirebaseFirestore.getInstance();

        final String timeInMill=String.valueOf(System.currentTimeMillis());
        mytime= DateFormat.format("dd.MM.yy", Long.parseLong(timeInMill)).toString();

        date.setText(mytime);



        hall.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                myhall = (String) adapterView.getItemAtPosition(i);

                if ("--Select Hall--".equals(myhall)) {
                    myhall = "null";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

                myhall="null";
            }
        });

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mytime=date.getText().toString().trim();
                myroll=roll.getText().toString().trim();
                myref=ref.getText().toString().trim();
                //mypurpose=purpose.getText().toString().trim();
                myamount=amount.getText().toString().trim();

                mypurpose=" ";
                if(admit.isChecked()){
                    mypurpose += " Admit ";
                }
                if(idcard.isChecked()){
                    mypurpose += " IDcard ";
                }
                if(seat.isChecked()){
                    mypurpose += " Seat ";
                }
                if(fine.isChecked()){
                    mypurpose += " Fine ";
                }

                if (!TextUtils.isEmpty(myroll) && !TextUtils.isEmpty(myref) && !TextUtils.isEmpty(mypurpose) && !TextUtils.isEmpty(myamount) && !TextUtils.isEmpty(myamount) && !"null".equals(myhall)) {

                    Map<String,Object> setData= new HashMap<>();

                    setData.put("date",mytime);
                    setData.put("time",timeInMill);
                    setData.put("roll",myroll);
                    setData.put("ref",myref);
                    setData.put("purpose",mypurpose);
                    setData.put("amount",myamount);
                    setData.put("hall",myhall);
                   // setData.put("authorized",myauthorized);

                    firebaseFirestore.collection("pendingTransactions").document().set(setData);
                    Toast.makeText(addTransaction.this,"Transaction added successfully into Pending",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(addTransaction.this,"Please fill up all the required field",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
