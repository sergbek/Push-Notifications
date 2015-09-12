package com.example.sergbek.pushnotifications.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.sergbek.pushnotifications.R;
import com.example.sergbek.pushnotifications.database.Database;
import com.example.sergbek.pushnotifications.model.NotificationEntity;
import com.example.sergbek.pushnotifications.utils.NotificationHelper;

import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<NotificationEntity> mNotificationList;
    private Context mContext;

    public RecyclerViewAdapter(List<NotificationEntity> notificationList,Context context) {
        mNotificationList = notificationList;
        this.mContext=context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.adapter_recycler_view, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
        NotificationEntity notification=mNotificationList.get(position);

        myViewHolder.setNotificationText(notification);
    }

    @Override
    public int getItemCount() {
        return mNotificationList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView mTextView;
        private Button mButton;
        private Database mDatabase;

        public MyViewHolder(View itemView) {
            super(itemView);

            mButton     =   (Button)itemView.findViewById(R.id.btn_delete_ARV);
            mTextView   =   (TextView)itemView.findViewById(R.id.tv_ARV);

            mDatabase=new Database(mContext);
            mButton.setOnClickListener(this);
            mTextView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (v==mButton){
                mDatabase.deleteNotification(mNotificationList.get(getAdapterPosition()));
                mNotificationList.remove(getAdapterPosition());
                notifyDataSetChanged();
            }
             if (v==mTextView)
                 NotificationHelper.sendNotification(mContext,mNotificationList.get(getAdapterPosition()));

        }

        public void setNotificationText(NotificationEntity _notification) {
            mTextView.setText(
                            "Message: " + _notification.getMessage() + '\n' +
                            "Title: " + _notification.getTitle() + '\n' +
                            "Subtitle: " + _notification.getSubtitle() + '\n' +
                            "TickerText:  " + _notification.getTickerText()
            );
        }
    }
}
