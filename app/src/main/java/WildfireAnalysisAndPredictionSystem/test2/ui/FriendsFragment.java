package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;

import WildfireAnalysisAndPredictionSystem.test2.Friend;
import WildfireAnalysisAndPredictionSystem.test2.FriendRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.R;
import WildfireAnalysisAndPredictionSystem.test2.User;

/**
 * @author hasanaliozkan
 * **/
public class FriendsFragment extends Fragment {

    private ArrayList<Friend> friends;


    private RecyclerView recyclerView;
    private FriendRecyclerViewAdapter friendRecyclerViewAdapter;
    private View view;
    private FirebaseFirestore db;
    private FirebaseAuth auth;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_friends, container, false);
        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();
        viewSettings();
        Integer[] list=new Integer[1];
        list[0] = 0;
        AsyncTask<Integer,Integer,Integer> loadFriends = new LoadTask();
        loadFriends.execute(list);
        friendRecyclerViewAdapter.notifyDataSetChanged();
        return view;
    }



    private void viewSettings() {
        recyclerView = view.findViewById(R.id.firends_list);
        friends = new ArrayList<>();
        friendRecyclerViewAdapter = new FriendRecyclerViewAdapter(friends);
        recyclerView.setAdapter(friendRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public class LoadTask extends AsyncTask<Integer,Integer,Integer> {

        @Override
        protected Integer doInBackground(Integer... integers) {
            FirebaseUser currentUser = auth.getCurrentUser();

            db.collection("users").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public  void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()){
                        for(DocumentSnapshot doc :task.getResult()){
                            if (doc.getId().equals(currentUser.getUid())){
                                User user = doc.toObject(User.class);
                                Log.d("TAG",user.getUsername());
                                Log.d("TAG",user.getEmail());
                                for(DocumentReference element: user.getFriends()){
                                    element.addSnapshotListener(new EventListener<DocumentSnapshot>() {
                                        @SuppressLint("NotifyDataSetChanged")
                                        @Override
                                        public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                                            Log.d("TAG",value.getString("username"));
                                            Friend friend = new Friend();
                                            friend.setName(value.getString("username"));
                                            friend.setEmail(value.getString("email"));
                                            friends.add(friend);
                                            friendRecyclerViewAdapter.notifyDataSetChanged();
                                            Log.d("TAGG",user.getUsername());
                                            Log.d("TAGG",user.getEmail());
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });

            return null;
        }
    }

}