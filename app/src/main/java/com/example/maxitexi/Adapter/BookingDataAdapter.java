package com.example.maxitexi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitexi.API.ApiCalling;
import com.example.maxitexi.CustomProgressDialog;
import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.MyApplication;
import com.example.maxitexi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

    public class BookingDataAdapter extends RecyclerView.Adapter<BookingDataAdapter.ViewHolder> {

    Context context;
    List<BookingData.BookingDataList> bookingDataAdapterList;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;

    public BookingDataAdapter(Context context, List<BookingData.BookingDataList> bookingDataAdapterList) {
        this.context = context;
        this.bookingDataAdapterList = bookingDataAdapterList;
    }

    @NonNull
    @Override
    public BookingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_data_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BookingDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.bookingNo.setText("" + bookingDataAdapterList.get(position).getContactNo());
            holder.date.setText("" + bookingDataAdapterList.get(position).getDate());
            holder.name.setText("" + bookingDataAdapterList.get(position).getName());
            holder.pickup.setText("" + bookingDataAdapterList.get(position).getPick_Up_Address());
            holder.dropoff.setText("" + bookingDataAdapterList.get(position).getDrop_Off_Address());
            holder.totalPassager.setText("" + bookingDataAdapterList.get(position).getNo_off_passanger());
            holder.price.setText("" + bookingDataAdapterList.get(position).getPrice());
            holder.totalDistance.setText("" + bookingDataAdapterList.get(position).getTotalDistance());
            holder.totalTime.setText("" + bookingDataAdapterList.get(position).getTotalTime());
            holder.pickupTime.setText("" + bookingDataAdapterList.get(position).getTime());
            holder.flightNo.setText("" + bookingDataAdapterList.get(position).getFlightno());
            holder.discription.setText("" + bookingDataAdapterList.get(position).getRemarks());
            holder.email.setText("" + bookingDataAdapterList.get(position).getEmail());
            holder.status.setText("" + bookingDataAdapterList.get(position).getStatus());
            /*if (bookingDataAdapterList.get(position).getPaymentType().equals("1")) {
                holder.paymentType.setText("Cash");
            } else {
                holder.paymentType.setText("Card");
            }*/

        if (bookingDataAdapterList.get(position).getStatus().equals("1")) {
            holder.status.setText("Pending");
        } else if (bookingDataAdapterList.get(position).getStatus().equals("2")){
            holder.status.setText("Complete");
        }else {
            holder.status.setText("Not Confirm");
        }



        holder.imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdown.setVisibility(View.VISIBLE);
                holder.imgup.setVisibility(View.GONE);
                holder.datalinear.setVisibility(View.GONE);
                holder.linearLine.setVisibility(View.GONE);
            }
        });
        holder.imgdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdown.setVisibility(View.GONE);
                holder.imgup.setVisibility(View.VISIBLE);
                holder.datalinear.setVisibility(View.VISIBLE);
                holder.linearLine.setVisibility(View.VISIBLE);
            }
        });

        holder.relative1.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return bookingDataAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookingNo,email,status,date,name,pickup,pickupTime,dropoff,totalPassager,price,totalDistance,totalTime,flightNo,discription,paymentType;
        Button btnConfirm,btnNotConfirm;
        ImageView imgdown,imgup;
        LinearLayout datalinear,linearLine;
        RelativeLayout relative1;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bookingNo = itemView.findViewById(R.id.bookingNo);
            email = itemView.findViewById(R.id.email);
            date = itemView.findViewById(R.id.date);
            name = itemView.findViewById(R.id.name);
            pickup = itemView.findViewById(R.id.pickup);
            dropoff = itemView.findViewById(R.id.dropoff);
            totalPassager = itemView.findViewById(R.id.totalPassager);
            price = itemView.findViewById(R.id.price);
            totalDistance = itemView.findViewById(R.id.totalDistance);
            totalTime = itemView.findViewById(R.id.totalTime);
            flightNo = itemView.findViewById(R.id.flightNo);
            /*paymentType = itemView.findViewById(R.id.paymentType);*/
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnNotConfirm = itemView.findViewById(R.id.btnNotConfirm);
            imgdown = itemView.findViewById(R.id.imgdown);
            imgup = itemView.findViewById(R.id.imgup);
            datalinear = itemView.findViewById(R.id.datalinear);
            relative1 = itemView.findViewById(R.id.relative1);
            linearLine = itemView.findViewById(R.id.linearLine);
            discription = itemView.findViewById(R.id.discription);
            status = itemView.findViewById(R.id.status);
            pickupTime = itemView.findViewById(R.id.pickupTime);
            progressDialog = new CustomProgressDialog(context);

            apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        }
    }
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void filterList(List<BookingData.BookingDataList> filteredList) {
        bookingDataAdapterList = filteredList;
        notifyDataSetChanged();
    }

}
