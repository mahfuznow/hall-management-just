package just.cse.mahfuz.smrjust;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PendingTransactionRecyclerAdapter extends RecyclerView.Adapter<PendingTransactionRecyclerAdapter.myViewHolder> {
    Context context;
    List<PendingTransactionModel> pendingTransactionModels;
    String mydate,mytime,myroll,myref,mypurpose,myamount,myhall;
    String myshortname,mycategory;

    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;
    String uid;
    ProgressDialog progressDialog;

    public PendingTransactionRecyclerAdapter(Context c, List<PendingTransactionModel> tm) {
        context=c;
        pendingTransactionModels=tm;

        firebaseFirestore= FirebaseFirestore.getInstance();
        firebaseAuth= FirebaseAuth.getInstance();
        uid=firebaseAuth.getUid();
        progressDialog=new ProgressDialog(context);

    }

    @NonNull
    @Override
    public PendingTransactionRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.pending_transaction_row, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PendingTransactionRecyclerAdapter.myViewHolder myViewHolder, int i) {


        mytime=pendingTransactionModels.get(i).getTime();
        mydate=pendingTransactionModels.get(i).getDate();
        myroll = pendingTransactionModels.get(i).getRoll();
        myref = pendingTransactionModels.get(i).getRef();
        mypurpose = pendingTransactionModels.get(i).getPurpose();
        myamount = pendingTransactionModels.get(i).getAmount();
        myhall = pendingTransactionModels.get(i).getHall();


        myViewHolder.date.setText(mydate);
        myViewHolder.roll.setText(myroll);
        myViewHolder.ref.setText(myref);
        myViewHolder.purpose.setText(mypurpose);
        myViewHolder.amount.setText(myamount);


        if (i%2==0) {
            myViewHolder.date.setBackgroundResource(R.drawable.table_border_colored);
            //myViewHolder.roll.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.ref.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.purpose.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.amount.setBackgroundResource(R.drawable.table_border_colored);

        }

        myViewHolder.approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.setMessage("Approving...");
                progressDialog.show();

                firebaseFirestore.collection("user").document(uid).get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        myshortname=task.getResult().getString("shortname");
                        mycategory=task.getResult().getString("category");

                        Map<String,Object> setData= new HashMap<>();
                        setData.put("date",mydate);
                        setData.put("time",mytime);
                        setData.put("roll",myroll);
                        setData.put("ref",myref);
                        setData.put("purpose",mypurpose);
                        setData.put("amount",myamount);
                        setData.put("hall",myhall);
                        setData.put("authorized",myshortname+","+mycategory);

                        firebaseFirestore.collection("transactions").document().set(setData).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                firebaseFirestore.collection("pendingTransactions").whereEqualTo("time",mytime).get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {
                                            for (DocumentSnapshot document : task.getResult()) {
                                                firebaseFirestore.collection("pendingTransactions").document(document.getId()).delete();
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
        });
    }

    @Override
    public int getItemCount() {

        return pendingTransactionModels.size();
    }



    class myViewHolder extends RecyclerView.ViewHolder {
        TextView date,roll,ref,purpose,amount;
        TextView approve;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.time);
            roll=itemView.findViewById(R.id.roll);
            ref=itemView.findViewById(R.id.ref);
            purpose=itemView.findViewById(R.id.purpose);
            amount=itemView.findViewById(R.id.amount);

            approve=itemView.findViewById(R.id.approve);



        }
    }
}



