package br.dcx.appalpha.control.api;

import android.content.Context;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;


public interface ContextService{

    void find(Long id);

    void insert();

    void update();

    void delete(Long id);

    @GET("contexts")
    Call<List<Context>> findAll();

    void findPage(
        int page,
        int linesPerPage,
        String orderBy,
        String direction
    );
    
}