package com.geovra.red.item.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.geovra.red.R;
import com.geovra.red.app.ui.RedActivity;
import com.geovra.red.item.adapter.ItemRecyclerAdapter;
import com.geovra.red.dashboard.viewmodel.DashboardViewModel;

import java.util.Date;

public class ItemIndexFragment extends Fragment {
  private RedActivity activity;
  private DashboardViewModel vmDashboard;
  private Date date; // string => "MONDAY"

  public ItemIndexFragment(DashboardViewModel vmDashboard, Date date /* , RedActivity activity */)
  {
    // this.activity = activity;
    this.vmDashboard = vmDashboard;
    this.date = date;
  }


  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
  {
    // Inflate the layout for this fragment
    View view = inflater.inflate(R.layout.data_item_list_basic, container, false);

    // Initialize recycler
    setRecyclerView(view, date);

    return view;
  }


  public void setRecyclerView(View view, Date date)
  {
    // RecyclerView setup
    RecyclerView recyclerView = view.findViewById(R.id.item_rv);

    recyclerView.setLayoutManager( new LinearLayoutManager(getContext()) );
    ItemRecyclerAdapter adapter = new ItemRecyclerAdapter( (RedActivity) getActivity(), vmDashboard, date );
    recyclerView.setAdapter(adapter);

    // ItemRecycleAdapter adapter.setClickListener(this);
  }

}
