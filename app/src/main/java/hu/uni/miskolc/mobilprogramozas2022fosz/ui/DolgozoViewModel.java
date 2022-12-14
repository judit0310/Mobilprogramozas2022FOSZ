package hu.uni.miskolc.mobilprogramozas2022fosz.ui;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import hu.uni.miskolc.mobilprogramozas2022fosz.service.Dolgozo;
import hu.uni.miskolc.mobilprogramozas2022fosz.service.DolgozoService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DolgozoViewModel extends ViewModel {
    private MutableLiveData<List<Dolgozo>> dolgozok;

    public LiveData<List<Dolgozo>> getDolgozok(){
        if (dolgozok == null){
            dolgozok = new MutableLiveData<>();
            loadDolgozok();

        }
        return dolgozok;
    }

    private void loadDolgozok() {

        //region Retrofit HTTP
        Retrofit retrofit = new Retrofit.Builder().
                baseUrl("https://my-json-server.typicode.com/judit0310/dummyJsonServer/").
                addConverterFactory(GsonConverterFactory.create()).build();

        DolgozoService service = retrofit.create(DolgozoService.class);

        Call<List<Dolgozo>> listCall = service.getAllDolgozo();
        listCall.enqueue(new Callback<List<Dolgozo>>() {
            @Override
            public void onResponse(Call<List<Dolgozo>> call, Response<List<Dolgozo>> response) {
               dolgozok.postValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Dolgozo>> call, Throwable t) {

            }
        });

        //endregion
    }

}
