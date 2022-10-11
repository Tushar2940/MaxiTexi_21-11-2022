package com.example.maxitexi.Fragment;

import android.content.Context;
import android.os.Bundle;
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
import com.example.maxitexi.BookingData_Activity;
import com.example.maxitexi.CustomProgressDialog;
import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.MyApplication;
import com.example.maxitexi.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllData extends Fragment {

    Context context;
    RecyclerView bookingData_rv,bookingDatapendding_rv;
    BookingDataAdapter bookingDataAdapter;
    PenddingDataAdapter penddingDataAdapter;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;
    public TextView nodatatxt;
    public CardView cardView,pendding_cardView;

    public AllData() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.alldata_layout, container, false);
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
                        progressDialog.show();

                        Call<BookingData> call1 = apiCalling.BookingData();
                        call1.enqueue(new Callback<BookingData>() {
                            @Override
                            public void onResponse(Call<BookingData> call, Response<BookingData> response) {
                                if (response.isSuccessful()) {
                                    BookingData data =response.body();
                                    List<BookingData.BookingDataList> list =  data.getResultData();
                                    if (list.size() > 0){
                                        ArrayList<BookingData.BookingDataList> filteredList = new ArrayList<>();
                                        if (list != null) {
                                            for (BookingData.BookingDataList item : list) {
                                                String loading = String.valueOf(item.getStatus());
                                                if (loading.toLowerCase().contains("2") || loading.toLowerCase().contains("3")) {
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
                                                bookingDataAdapter = new BookingDataAdapter(context,filteredList);
                                                bookingData_rv.setLayoutManager(new LinearLayoutManager(context));
                                                bookingData_rv.setAdapter(bookingDataAdapter);
                                                bookingDataAdapter.notifyDataSetChanged();
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
                                    }else {
                                        progressDialog.dismiss();
                                        bookingData_rv.setVisibility(View.GONE);
                                        nodatatxt.setVisibility(View.VISIBLE);
                                        Toast.makeText(context, "No Data", Toast.LENGTH_SHORT).show();
                                    }
                                }else {
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
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
}
