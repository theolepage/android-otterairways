package com.thdoteo.otterairways.Admin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.thdoteo.otterairways.AppRoom;
import com.thdoteo.otterairways.MainActivity;
import com.thdoteo.otterairways.R;
import com.thdoteo.otterairways.Transaction.Transaction;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class AdminLogsActivity extends AppCompatActivity {

    private LogsAdapter logsAdapter;
    private List<Transaction> logs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_logs);

        setTitle("Admin Logs");

        // show logs in list
        RecyclerView rv = findViewById(R.id.admin_logs_list);
        rv.setLayoutManager(new LinearLayoutManager(this));
        logsAdapter = new LogsAdapter();
        rv.setAdapter(logsAdapter);
    }

    public void goto_admin_flight(View v)
    {
        // go to new activity
    }





    private class LogsAdapter extends RecyclerView.Adapter<ItemHolder> {

        public LogsAdapter(){
            logs = AppRoom.getDatabase(AdminLogsActivity.this).dao().getTransactions();
        }

        @Override
        public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(AdminLogsActivity.this);
            return new ItemHolder(layoutInflater, parent);
        }

        @Override
        public void onBindViewHolder(ItemHolder holder, int position) {
            Transaction transaction = logs.get(position);
            if (transaction.getType().equals("New account"))
            {
                holder.bind(transaction.getType() + ": " + transaction.getData() + " (" + transaction.getDatetime() + ")");
            }
            else
            {
                holder.bind( "Transaction: " + transaction.getType() + " (" + transaction.getDatetime() + ")");
            }
        }

        @Override
        public int getItemCount() {
            return logs.size();
        }
    }

    private class ItemHolder extends RecyclerView.ViewHolder {

        public ItemHolder(LayoutInflater inflater, ViewGroup parent) {
            super(inflater.inflate(R.layout.item, parent, false));
        }

        public void bind(String str){
            TextView v = itemView.findViewById(R.id.item_id);
            v.setText(str);
        }
    }


}