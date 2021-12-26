package WildfireAnalysisAndPredictionSystem.test2.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import WildfireAnalysisAndPredictionSystem.test2.Friend;
import WildfireAnalysisAndPredictionSystem.test2.FriendRecyclerViewAdapter;
import WildfireAnalysisAndPredictionSystem.test2.R;


public class FriendsFragment extends Fragment {

    private ArrayList<Friend> friends;
    private RecyclerView recyclerView;
    private FriendRecyclerViewAdapter friendRecyclerViewAdapter;
    private View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_friends, container, false);
        viewSettings();
        fillTheArray();
        friendRecyclerViewAdapter.notifyDataSetChanged();
        return view;
    }


    private void fillTheArray() {


        //TODO solve the problem in the Friends adapter class then fill this code block by getting data from the firebase.
    }

    private void viewSettings() {
        recyclerView = view.findViewById(R.id.county_list);
        friends = new ArrayList<>();
        friendRecyclerViewAdapter = new FriendRecyclerViewAdapter(friends);
        recyclerView.setAdapter(friendRecyclerViewAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

}