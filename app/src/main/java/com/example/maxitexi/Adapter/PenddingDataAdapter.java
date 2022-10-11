package com.example.maxitexi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
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
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitexi.API.ApiCalling;
import com.example.maxitexi.BookingData_Activity;
import com.example.maxitexi.CustomProgressDialog;
import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.MyApplication;
import com.example.maxitexi.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PenddingDataAdapter extends RecyclerView.Adapter<PenddingDataAdapter.ViewHolder> {

    Context context;
    List<BookingData.BookingDataList> bookingDataAdapterList;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;
    List<String> list = new ArrayList<>();

    public PenddingDataAdapter(Context context, List<BookingData.BookingDataList> bookingDataAdapterList) {
        this.context = context;
        this.bookingDataAdapterList = bookingDataAdapterList;
    }

    @NonNull
    @Override
    public PenddingDataAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.booking_data_design,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PenddingDataAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {

            holder.bookingNo.setText("" + bookingDataAdapterList.get(position).getContactNo());
            holder.date.setText("" + bookingDataAdapterList.get(position).getDate());
            holder.name.setText("" + bookingDataAdapterList.get(position).getName());
            holder.pickup.setText("" + bookingDataAdapterList.get(position).getPick_Up_Address());
            holder.dropoff.setText("" + bookingDataAdapterList.get(position).getDrop_Off_Address());
            holder.totalPassager.setText("" + bookingDataAdapterList.get(position).getNo_off_passanger());
            holder.price.setText("" + bookingDataAdapterList.get(position).getPrice());
            holder.totalDistance.setText("" + bookingDataAdapterList.get(position).getTotalDistance());
            holder.totalTime.setText("" + bookingDataAdapterList.get(position).getTotalTime());
            holder.flightNo.setText("" + bookingDataAdapterList.get(position).getFlightno());
            holder.discription.setText("" + bookingDataAdapterList.get(position).getRemarks());
            holder.email.setText("" + bookingDataAdapterList.get(position).getEmail());
            if (bookingDataAdapterList.get(position).getPaymentType().equals("1")) {
                holder.paymentType.setText("Cash");
            } else {
                holder.paymentType.setText("Card");
            }

        if (bookingDataAdapterList.get(position).getStatus().equals("1")) {
            holder.status.setText("Pending");
        } else {
            holder.status.setText("Complete");
        }

        holder.imgup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdown.setVisibility(View.VISIBLE);
                holder.imgup.setVisibility(View.GONE);
                holder.datalinear.setVisibility(View.GONE);
                holder.relative1.setVisibility(View.GONE);
                holder.linearLine.setVisibility(View.GONE);
            }
        });
        holder.imgdown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.imgdown.setVisibility(View.GONE);
                holder.imgup.setVisibility(View.VISIBLE);
                holder.datalinear.setVisibility(View.VISIBLE);
                holder.relative1.setVisibility(View.VISIBLE);
                holder.linearLine.setVisibility(View.VISIBLE);
            }
        });

        holder.btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                Call<String> call = apiCalling.sendMail(bookingDataAdapterList.get(position).getEmail(),
                        bookingDataAdapterList.get(position).getPick_Up_Address(),
                        bookingDataAdapterList.get(position).getDrop_Off_Address(),
                        bookingDataAdapterList.get(position).getName(),
                        bookingDataAdapterList.get(position).getId(),bookingDataAdapterList.get(position).getNo_off_passanger(),
                        bookingDataAdapterList.get(position).getContactNo(),bookingDataAdapterList.get(position).getFlightno(),
                        bookingDataAdapterList.get(position).getDate(),bookingDataAdapterList.get(position).getTime(),
                        bookingDataAdapterList.get(position).getPrice(),bookingDataAdapterList.get(position).getPaymentType(),
                        bookingDataAdapterList.get(position).getTotalTime(),bookingDataAdapterList.get(position).getTotalDistance(),
                        bookingDataAdapterList.get(position).getRemarks());
                    call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()){
                            try {

                                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                                View view = LayoutInflater.from(context).inflate(
                                        R.layout.layout_warning_dailog,
                                        (ConstraintLayout)v.findViewById(R.id.layoutDialogContainer)
                                );
                                builder.setView(view);
                                ((TextView) view.findViewById(R.id.textTitle)).setText("Warning");
                                ((TextView) view.findViewById(R.id.textMessage)).setText("Confirm email Sent to "+bookingDataAdapterList.get(position).getEmail()+ ".");
                                ((Button) view.findViewById(R.id.buttonYes)).setText("Yes");
                                ((Button) view.findViewById(R.id.buttonNo)).setText("No");
                                ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

                                final AlertDialog alertDialog = builder.create();

                                view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        String s = response.body();
                                        Toast.makeText(context, "Confirm email Sent to "+bookingDataAdapterList.get(position).getEmail()+ ".", Toast.LENGTH_LONG).show();
                                        notifyItemChanged(position);
                                        bookingDataAdapterList.remove(position);
                                        progressDialog.dismiss();
                                        alertDialog.dismiss();
                                    }
                                });

                                view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        alertDialog.dismiss();
                                        progressDialog.dismiss();
                                    }
                                });

                                if (alertDialog.getWindow() != null){
                                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                                }
                                alertDialog.show();

                            }catch (Exception e){
                                Toast.makeText(context, ""+e.toString(), Toast.LENGTH_SHORT).show();
                            }
                        }else {
                            progressDialog.dismiss();
                            Toast.makeText(context, "Something Went Wrong.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        holder.btnNotConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressDialog.show();
                progressDialog.setCancelable(false);
                Call<String> call = apiCalling.status(bookingDataAdapterList.get(position).getId(),bookingDataAdapterList.get(position).getName(),
                        bookingDataAdapterList.get(position).getEmail());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        if (response.isSuccessful()) {

                            AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                            View view = LayoutInflater.from(context).inflate(
                                    R.layout.layout_warning_dailog,
                                    (ConstraintLayout)v.findViewById(R.id.layoutDialogContainer)
                            );
                            builder.setView(view);
                            ((TextView) view.findViewById(R.id.textTitle)).setText("Warning");
                            ((TextView) view.findViewById(R.id.textMessage)).setText("Not Confirm email Sent to "+bookingDataAdapterList.get(position).getEmail()+ ".");
                            ((Button) view.findViewById(R.id.buttonYes)).setText("Yes");
                            ((Button) view.findViewById(R.id.buttonNo)).setText("No");
                            ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

                            final AlertDialog alertDialog = builder.create();

                            view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                    notifyItemChanged(position);
                                    Toast.makeText(context, "Not Confirm email Sent to "+bookingDataAdapterList.get(position).getEmail()+ ".", Toast.LENGTH_LONG).show();
                                    bookingDataAdapterList.remove(position);
                                    progressDialog.dismiss();
                                }
                            });

                            view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    alertDialog.dismiss();
                                    progressDialog.dismiss();
                                }
                            });

                            if (alertDialog.getWindow() != null){
                                alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                            }
                            alertDialog.show();

                        }
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(context,""+t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    @Override
    public int getItemCount() {
        return bookingDataAdapterList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView bookingNo,email,date,name,pickup,dropoff,totalPassager,price,totalDistance,totalTime,flightNo,discription,paymentType;
        Button btnConfirm,btnNotConfirm;
        ImageView imgdown,imgup;
        LinearLayout datalinear,linearLine,statuslayout;
        RelativeLayout relative1;
        TextView nodata,status;

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
            paymentType = itemView.findViewById(R.id.paymentType);
            btnConfirm = itemView.findViewById(R.id.btnConfirm);
            btnNotConfirm = itemView.findViewById(R.id.btnNotConfirm);
            imgdown = itemView.findViewById(R.id.imgdown);
            imgup = itemView.findViewById(R.id.imgup);
            datalinear = itemView.findViewById(R.id.datalinear);
            relative1 = itemView.findViewById(R.id.relative1);
            linearLine = itemView.findViewById(R.id.linearLine);
            discription = itemView.findViewById(R.id.discription);
            statuslayout = itemView.findViewById(R.id.statuslayout);
            status = itemView.findViewById(R.id.status);
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

}
