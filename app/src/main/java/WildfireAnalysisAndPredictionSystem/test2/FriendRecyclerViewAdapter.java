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
public class FriendRecyclerViewAdapter extends RecyclerView.Adapter<FriendRecyclerViewAdapter.FriendViewHolder> {

    private ArrayList<Friend> friends;

    public FriendRecyclerViewAdapter(ArrayList<Friend> friends) {
        this.friends = friends;
    }

    @NonNull
    @Override
    public FriendViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_friend,parent,false);
        return new FriendViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FriendViewHolder holder, int position) {

        holder.name.setText(friends.get(position).getName());
        holder.email.setText(friends.get(position).getEmail());

    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{
        TextView email;
        TextView name;

        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.email_single_friend);
            name = itemView.findViewById(R.id.user_name_single_friend);

        }
    }

}
