package com.example.maxitexi.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.maxitexi.API.ApiCalling;
import com.example.maxitexi.CustomProgressDialog;
import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.Model.DriverModel;
import com.example.maxitexi.Model.ResponseModel;
import com.example.maxitexi.MyApplication;
import com.example.maxitexi.R;
import com.example.maxitexi.SharedPrefrences.Preferences;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class driverListAdapter extends RecyclerView.Adapter<driverListAdapter.ViewHolder>{
    Context context;
    List<DriverModel.DriverData> driverModelList;
    ApiCalling apiCalling;
    CustomProgressDialog progressDialog;

    public driverListAdapter(Context context, List<DriverModel.DriverData> driverModelList) {
        this.context = context;
        this.driverModelList = driverModelList;
    }

    @NonNull
    @Override
    public driverListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.driverlist_adapter,parent,false);
        return new driverListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull driverListAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.number.setText(""+position);
        holder.driverName.setText(driverModelList.get(position).getDriverName());

        holder.background.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Preferences.getInstance(context).setInt(Preferences.DRIVER_ID,driverModelList.get(position).getDriverID());
                holder.background.setBackgroundColor(Color.GREEN);
                int id = Preferences.getInstance(context).getInt(Preferences.DRIVER_ID);
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.AlertDialogTheme);
                View view = LayoutInflater.from(context).inflate(
                        R.layout.layout_warning_dailog,
                        (ConstraintLayout) v.findViewById(R.id.layoutDialogContainer)
                );
                builder.setView(view);
                ((TextView) view.findViewById(R.id.textTitle)).setText("Warning");
                ((TextView) view.findViewById(R.id.textMessage)).setText("Are You Sure Want to Assign Job To "+driverModelList.get(position).getDriverName());
                ((Button) view.findViewById(R.id.buttonYes)).setText("Yes");
                ((Button) view.findViewById(R.id.buttonNo)).setText("No");
                ((ImageView) view.findViewById(R.id.imageIcon)).setImageResource(R.drawable.warning);

                final AlertDialog alertDialog = builder.create();

                view.findViewById(R.id.buttonYes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        progressDialog.show();
                        Call<ResponseModel> call = apiCalling.assignjob(Preferences.getInstance(context).getInt(Preferences.BOOKING_ID),driverModelList.get(position).getDriverID());
                        call.enqueue(new Callback<ResponseModel>() {
                            @Override
                            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                                if (response.isSuccessful())
                                {
                                    progressDialog.dismiss();
                                    ResponseModel model = response.body();
                                    alertDialog.dismiss();
                                    Toast.makeText(context, ""+model.getMessage().toString(), Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    alertDialog.dismiss();
                                    progressDialog.dismiss();
                                    Toast.makeText(context, "Something Went Wrong", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseModel> call, Throwable t) {
                                alertDialog.dismiss();
                                progressDialog.dismiss();
                                Toast.makeText(context, ""+t.getMessage().toString(), Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });

                view.findViewById(R.id.buttonNo).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        alertDialog.dismiss();
                        progressDialog.dismiss();
                    }
                });

                if (alertDialog.getWindow() != null) {
                    alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
                }
                alertDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return driverModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView number,driverName;
        LinearLayout background;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            number = itemView.findViewById(R.id.number);
            driverName = itemView.findViewById(R.id.driverName);
            background = itemView.findViewById(R.id.background);

            progressDialog = new CustomProgressDialog(context);
            apiCalling = MyApplication.getRetrofit().create(ApiCalling.class);
        }
    }
}
