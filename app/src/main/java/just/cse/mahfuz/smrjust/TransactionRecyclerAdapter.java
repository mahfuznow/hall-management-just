package just.cse.mahfuz.smrjust;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class TransactionRecyclerAdapter extends RecyclerView.Adapter<TransactionRecyclerAdapter.myViewHolder> {
    Context context;
    List<TransactionModel> transactionModels;
    String mydate,mytime,myroll,myref,mypurpose,myamount,myauthorized;

    public TransactionRecyclerAdapter(Context c, List<TransactionModel> tm) {
        context=c;
        transactionModels=tm;
    }

    @NonNull
    @Override
    public TransactionRecyclerAdapter.myViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View view = inflater.inflate(R.layout.transaction_row, viewGroup, false);
        return new myViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TransactionRecyclerAdapter.myViewHolder myViewHolder, int i) {

        mydate=transactionModels.get(i).getDate();
        myroll = transactionModels.get(i).getRoll();
        myref = transactionModels.get(i).getRef();
        mypurpose = transactionModels.get(i).getPurpose();
        myamount = transactionModels.get(i).getAmount();
        myauthorized = transactionModels.get(i).getAuthorized();


        //String timeInMill=transactionModels.get(i).getTime();
       // mytime= DateFormat.format("dd/MM/yyyy hh:mm:ss", Long.parseLong(timeInMill)).toString();
        //mytime= DateFormat.format("dd.MM.yy", Long.parseLong(timeInMill)).toString();

        myViewHolder.date.setText(mydate);
        myViewHolder.roll.setText(myroll);
        myViewHolder.ref.setText(myref);
        myViewHolder.purpose.setText(mypurpose);
        myViewHolder.amount.setText(myamount);
        myViewHolder.authorizedBy.setText(myauthorized);


        if (i%2==0) {
            myViewHolder.date.setBackgroundResource(R.drawable.table_border_colored);
            //myViewHolder.roll.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.ref.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.purpose.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.amount.setBackgroundResource(R.drawable.table_border_colored);
            myViewHolder.authorizedBy.setBackgroundResource(R.drawable.table_border_colored);

        }
    }

    @Override
    public int getItemCount() {

        return transactionModels.size();
    }



    class myViewHolder extends RecyclerView.ViewHolder {
        TextView date,roll,ref,purpose,amount,authorizedBy;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.time);
            roll=itemView.findViewById(R.id.roll);
            ref=itemView.findViewById(R.id.ref);
            purpose=itemView.findViewById(R.id.purpose);
            amount=itemView.findViewById(R.id.amount);
            authorizedBy=itemView.findViewById(R.id.authorizedBy);

        }
    }
}



