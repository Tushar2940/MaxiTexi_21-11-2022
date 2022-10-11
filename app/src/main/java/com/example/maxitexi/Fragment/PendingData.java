package com.example.maxitexi.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitexi.API.ApiCalling;
import com.example.maxitexi.Adapter.BookingDataAdapter;
import com.example.maxitexi.Adapter.PenddingDataAdapter;
import com.example.maxitexi.CustomProgressDialog;
import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.MyApplication;
import com.example.maxitexi.R;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PendingData extends Fragment {

    Context context;
    RecyclerView bookingData_rv;
    BookingDataAdapter bookingDataAdapter;
    PenddingDataAdapter penddingDataAdapter;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;
    public TextView nodatatxt;
    public CardView cardView,pendding_cardView;

    public PendingData() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pendingdata_layout, container, false);

        context = getActivity();
        nodatatxt = view.findViewById(R.id.nodatatxt);
        cardView = view.findViewById(R.id.cardView);
        apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        progressDialog = new CustomProgressDialog(context);
        bookingData_rv = view.findViewById(R.id.bookingData_rv);

        Bundle bundle = this.getArguments();
        String myValue = bundle.getString("message");

            progressDialog.show();
            Call<String> call1 = apiCalling.token(myValue);
            call1.enqueue(new Callback<String>() {
                @Override
                public void onResponse(Call<String> call, Response<String> response) {
                    if(response.isSuccessful()){
                        /*Toast.makeText(context, ""+token, Toast.LENGTH_SHORT).show();*/
                        progressDialog.show();

                        Call<BookingData> call1 = apiCalling.BookingData();
                        call1.enqueue(new Callback<BookingData>() {
                            @Override
                            public void onResponse(Call<BookingData> call, Response<BookingData> response) {
                                if (response.isSuccessful()) {
                                    BookingData data = response.body();
                                    List<BookingData.BookingDataList> list = data.getResultData();
                                    if (list.size() > 0) {
                                        ArrayList<BookingData.BookingDataList> filteredList = new ArrayList<>();
                                        if (list != null) {
                                            for (BookingData.BookingDataList item : list) {
                                                String loading = String.valueOf(item.getStatus());
                                                if (loading.toLowerCase().contains("1")) {
                                                    filteredList.add(item);
                                                }
                                            }
                                            if (!filteredList.isEmpty()) {
                                                Collections.sort(filteredList, new Comparator<BookingData.BookingDataList>() {
                                                    @Override
                                                    public int compare(BookingData.BookingDataList o1, BookingData.BookingDataList o2) {
                                                        return Long.valueOf(o2.getId()).compareTo(Long.valueOf(o1.getId()));
                                                    }
                                                });
                                                penddingDataAdapter = new PenddingDataAdapter(context, filteredList);
                                                bookingData_rv.setLayoutManager(new WrapContentLinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
                                                bookingData_rv.setAdapter(penddingDataAdapter);
                                                penddingDataAdapter.notifyDataSetChanged();
                                                progressDialog.dismiss();
                                                bookingData_rv.setVisibility(View.VISIBLE);
                                                nodatatxt.setVisibility(View.GONE);
                                            } else {
                                                bookingData_rv.setVisibility(View.GONE);
                                                nodatatxt.setVisibility(View.VISIBLE);
                                                Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            }
                                        }
                                    } else {
                                        bookingData_rv.setVisibility(View.GONE);
                                        nodatatxt.setVisibility(View.VISIBLE);
                                        Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }
                                }
                            }

                            @Override
                            public void onFailure(Call<BookingData> call, Throwable t) {
                                progressDialog.dismiss();
                                Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        Toast.makeText(context, ""+response.message(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<String> call, Throwable t) {
                    Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                }
            });

        return view;
    }

    public class WrapContentLinearLayoutManager extends LinearLayoutManager {
        public WrapContentLinearLayoutManager(Context context) {
            super(context);
        }

        public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
            super(context, orientation, reverseLayout);
        }

        public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
            super(context, attrs, defStyleAttr, defStyleRes);
        }

        @Override
        public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
            try {
                super.onLayoutChildren(recycler, state);
            } catch (IndexOutOfBoundsException e) {
                Log.e("TAG", "meet a IOOBE in RecyclerView");
            }
        }
    }
}
