package hu.uni.miskolc.mobilprogramozas2022fosz.service;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface DolgozoService {
    @GET("dolgozok")
    public Call<List<Dolgozo>> getAllDolgozo();
}
