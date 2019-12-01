package br.ufpb.dcx.appalpha.control.service;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.appalpha.control.api.RetrofitInitializer;
import br.ufpb.dcx.appalpha.model.bean.Theme;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ThemesApiService{
    private static final String TAG = "ThemesApiService";
    private List<Theme> themes;
    private Theme themeResult;
    private static ThemesApiService instance;

    private ThemesApiService(){}

    public static ThemesApiService getInstance(){
        if(instance == null){
            instance = new ThemesApiService();
        }
        return instance;
    }

    public Theme find(Long id){
        themeResult = null;
        Call call = new RetrofitInitializer().contextService().find(id);
        call.enqueue(new Callback<Theme>() {
            @Override
            public void onResponse(Call<Theme> call, Response<Theme> response) {
                if(response != null){
                    themeResult = response.body();
                }
            }

            @Override
            public void onFailure(Call<Theme> call, Throwable t) {
                Log.e(TAG, "Erro ao recuperar tema: "+t.getMessage());
            }
        });

        return themeResult;
    }

    public void insert(){

    }

    public void update(){

    }

    public void delete(Long id){

    }

    public List<Theme> findAll(){
        themes = new ArrayList<>();
        Call call = new RetrofitInitializer().contextService().findAll();
        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                themes = response.body();
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                Log.e(TAG, "Erro ao recuperar temas: "+t.getMessage());
            }
        });
        return themes;
    }

    public void findPage(
            int page,
            int linesPerPage,
            String orderBy,
            String direction
    ) {

    }
}
