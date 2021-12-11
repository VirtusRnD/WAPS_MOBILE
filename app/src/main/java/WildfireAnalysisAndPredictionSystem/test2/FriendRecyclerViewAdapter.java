package WildfireAnalysisAndPredictionSystem.test2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


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
        //TODO Looking for image to image-bitmap casting.
        //holder.avatar.setImageBitmap(friends.get(position).getAvatar());
        holder.name.setText(friends.get(position).getName());
        //TODO Looking for image to image-bitmap casting. Same problem is here.
        //holder.added_or_not.setImageIcon(friends.get(position).getAdded_or_not());
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public class FriendViewHolder extends RecyclerView.ViewHolder{
        ImageView avatar;
        TextView name;
        ImageButton added_or_not;
        public FriendViewHolder(@NonNull View itemView) {
            super(itemView);
            avatar = itemView.findViewById(R.id.avatar);
            name = itemView.findViewById(R.id.single_friend_name);
            added_or_not = itemView.findViewById(R.id.added_or_not);
        }
    }

}
