package com.example.maxitexi.API;

import com.example.maxitexi.Model.BookingData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCalling {

    @GET(ApiConstant.BASE_URL)
    Call<BookingData> BookingData();

    @GET(ApiConstant.BASE_URL)
    Call<String> token(@Query("token") String token);

    @GET(ApiConstant.mail)
    Call<String> sendMail(@Query("To") String To,
                           @Query("pickup") String pickup,
                           @Query("drop") String drop,
                           @Query("name") String name,
                           @Query("id") int id,
                           @Query("pessenger") String pessenger,
                           @Query("ContactNo") String ContactNo,
                           @Query("flightno") String flightno,
                           @Query("bookingdate") String bookingdate,
                           @Query("time") String time,
                           @Query("price") String price,
                           @Query("payment") String payment,
                           @Query("totaltime") String totaltime,
                           @Query("distance") String distance,
                           @Query("Remarks") String Remarks
    );

    @GET(ApiConstant.insertStatus)
    Call<String> status(@Query("id") int id, @Query("name") String name,@Query("To") String To);
}
