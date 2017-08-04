package com.pechydev.iotproject01.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.pechydev.iotproject01.R;
import com.pechydev.iotproject01.adapter.Adapter_Device;
import com.pechydev.iotproject01.adapter.Adapter_DeviceHistory;

import java.util.ArrayList;

public class HistoryFragment extends Fragment {
    RecyclerView recyclerView;
    SwipeRefreshLayout mSwipeRefreshLayout;
    String TAG = "TimelineFragment";

    public HistoryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_history, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.dummyfrag_scrollableview);
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getDevice();
            }
        });
        getDevice();
        return view;
    }

    private void getDevice() {
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        ref.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<String> device = new ArrayList<String>();
                for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
                    device.add(childDataSnapshot.getKey());
                }
                initRecycleView(device);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initRecycleView(ArrayList<String> deviceName) {
        Adapter_DeviceHistory adapter = new Adapter_DeviceHistory(getActivity(), deviceName);
        recyclerView.setAdapter(adapter);

        try {
            mSwipeRefreshLayout.setRefreshing(false);
        } catch (Exception e) {

        }

        adapter.SetOnItemClickListener(new Adapter_DeviceHistory.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }
        });
    }
}
