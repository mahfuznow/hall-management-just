package just.cse.mahfuz.smrjust;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;

import javax.annotation.Nullable;

public class pendingTransaction extends AppCompatActivity {


    RecyclerView recyclerView;
    FirebaseFirestore firebaseFirestore;
    pendingTransactionRecyclerAdapter mPendingTransactionRecyclerAdapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pending_transaction);

        recyclerView=findViewById(R.id.recyclerView);

        firebaseFirestore = FirebaseFirestore.getInstance();

        recyclerView.setLayoutManager(new LinearLayoutManager(pendingTransaction.this));



        Query query = firebaseFirestore.collection("pendingTransactions").orderBy("time", Query.Direction.DESCENDING);



        query.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {

                if (e != null) {
                    return;
                }
                List<pendingTransactionModel> pendingTransactionModels = queryDocumentSnapshots.toObjects(pendingTransactionModel.class);

                mPendingTransactionRecyclerAdapter = new pendingTransactionRecyclerAdapter(pendingTransaction.this, pendingTransactionModels);
                recyclerView.setAdapter(mPendingTransactionRecyclerAdapter);
            }
        });





    }
}
