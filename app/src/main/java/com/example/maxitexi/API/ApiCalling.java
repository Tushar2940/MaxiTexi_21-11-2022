package com.example.maxitexi.API;

import com.example.maxitexi.Model.BookingData;
import com.example.maxitexi.Model.DriverModel;
import com.example.maxitexi.Model.ModelData;
import com.example.maxitexi.Model.ResponseModel;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiCalling {

    @GET(ApiConstant.BOOKING_DATA)
    Call<BookingData> BookingData();

    @GET(ApiConstant.TOKEN)
    Call<String> token(@Query("token") String token);

    @HTTP(method = "POST", path = ApiConstant.mail, hasBody = true)
    Call<String> sendMail(@Body ModelData data);

    @GET(ApiConstant.insertStatus)
    Call<String> status(@Query("id") int id, @Query("name") String name,@Query("To") String To);

    @GET(ApiConstant.GetAllDriver)
    Call<DriverModel> getallDriver();

    @POST(ApiConstant.AssignJob)
    Call<ResponseModel> assignjob(@Query("BookingID") int BookingID, @Query("DriverID") int DriverID);
}
