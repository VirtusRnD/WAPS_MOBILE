package WildfireAnalysisAndPredictionSystem.test2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

/**
 * @author hasanaliozkan
 * **/
public class TempNotificationRecyclerViewAdapter extends RecyclerView.Adapter<TempNotificationRecyclerViewAdapter.NotificationViewHolder>{
    private ArrayList<TempNotifications> notifications;

    public TempNotificationRecyclerViewAdapter(ArrayList<TempNotifications> notifications) {
        this.notifications = notifications;
    }

    @NonNull
    @Override
    public NotificationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =  LayoutInflater.from(parent.getContext()).inflate(R.layout.single_notification,parent,false);

        return new NotificationViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NotificationViewHolder holder, int position) {
        holder.sentence.setText(notifications.get(position).getSentence());
    }

    @Override
    public int getItemCount() {
        return notifications.size();
    }

    public class NotificationViewHolder extends RecyclerView.ViewHolder{
        TextView sentence;


        public NotificationViewHolder(@NonNull View itemView) {
            super(itemView);
            sentence = itemView.findViewById(R.id.sentence);

        }
    }
}
