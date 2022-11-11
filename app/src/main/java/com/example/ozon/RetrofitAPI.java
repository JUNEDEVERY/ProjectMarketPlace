package com.example.ozon;



import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitAPI {

    @GET("nameofproducts/{id}")
    Call<DataModal> getDATA(@Path("id") int id);

    @POST("nameofproducts")
    Call<DataModal> createPost(@Body DataModal dataModal);

    @PUT("nameofproducts/{id}")
    Call<DataModal> updateData(@Query("id") int id, @Body DataModal dataModal);

    @DELETE("nameofproducts/{id}")
    Call<Void> deleteData(@Path("id") int id);


}

