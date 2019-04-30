package just.cse.mahfuz.smrjust;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.WindowManager;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

public class ViewTransactionActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    TransactionRecyclerAdapter mtransactionRecyclerAdapter;

    String myroll;

    TextView title;
    TextView totalAmount;

    int total=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


        title=findViewById(R.id.title);
        totalAmount=findViewById(R.id.total);
        recyclerView=findViewById(R.id.recyclerView);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(ViewTransactionActivity.this));



        myroll=getIntent().getExtras().getString("roll");


        title.setText("Transaction History of "+myroll);


        Query query = firebaseFirestore.collection("transactions").whereEqualTo("roll",myroll).orderBy("time", Query.Direction.DESCENDING);
/*
        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                if (e != null) {
                 return;
                }


                List<TransactionModel> transactionModels = queryDocumentSnapshots.toObjects(TransactionModel.class);


                mtransactionRecyclerAdapter = new TransactionRecyclerAdapter(ViewTransactionActivity.this, transactionModels);
                recyclerView.setAdapter(mtransactionRecyclerAdapter);


                //calculating total amount
                for (int i=0;i<transactionModels.size();i++) {
                   String amount= transactionModels.get(i).getAmount();
                   total+= Integer.parseInt(amount);
                }

                totalAmount.setText(String.valueOf(total));


            }
        });

        */

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                List<TransactionModel> transactionModels = task.getResult().toObjects(TransactionModel.class);

                mtransactionRecyclerAdapter = new TransactionRecyclerAdapter(ViewTransactionActivity.this, transactionModels);
                recyclerView.setAdapter(mtransactionRecyclerAdapter);


                //calculating total amount
                for (int i=0;i<transactionModels.size();i++) {
                    String amount= transactionModels.get(i).getAmount();
                    total+= Integer.parseInt(amount);
                }

                totalAmount.setText(String.valueOf(total));
            }
        });





    }
}
