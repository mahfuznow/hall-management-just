package just.cse.mahfuz.smrjust;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class addTransaction extends AppCompatActivity {

    EditText date,roll,ref,purpose,amount,authorized;
    String mytime,myroll,myref,mypurpose,myamount,myauthorized;
    Button add;

    FirebaseFirestore firebaseFirestore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);


        date=findViewById(R.id.date);
        roll=findViewById(R.id.roll);
        ref=findViewById(R.id.ref);
        purpose=findViewById(R.id.purpose);
        amount=findViewById(R.id.amount);
        authorized=findViewById(R.id.authorizedBy);
        add=findViewById(R.id.add);
        firebaseFirestore=FirebaseFirestore.getInstance();

        final String timeInMill=String.valueOf(System.currentTimeMillis());
        mytime= DateFormat.format("dd.MM.yy", Long.parseLong(timeInMill)).toString();

        date.setText(mytime);


        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mytime=date.getText().toString().trim();
                myroll=roll.getText().toString().trim();
                myref=ref.getText().toString().trim();
                mypurpose=purpose.getText().toString().trim();
                myamount=amount.getText().toString().trim();
                myauthorized= authorized.getText().toString().trim();

                if (!TextUtils.isEmpty(myroll) && !TextUtils.isEmpty(myref) && !TextUtils.isEmpty(mypurpose) && !TextUtils.isEmpty(myamount) && !TextUtils.isEmpty(myamount)) {

                    Map<String,Object> setData= new HashMap<>();

                    setData.put("date",mytime);
                    setData.put("time",timeInMill);
                    setData.put("roll",myroll);
                    setData.put("ref",myref);
                    setData.put("purpose",mypurpose);
                    setData.put("amount",myamount);
                    setData.put("authorized",myauthorized);

                    firebaseFirestore.collection("transactions").document().set(setData);
                    Toast.makeText(addTransaction.this,"Transaction added successfully",Toast.LENGTH_SHORT).show();
                    finish();
                }
                else {
                    Toast.makeText(addTransaction.this,"Please fill up all the required field",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
