package br.ufpb.dcx.appalpha.control.service.interfaces;

import java.util.List;

import br.ufpb.dcx.appalpha.model.bean.Theme;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.Path;


public interface ThemesApiServiceInterface {

    @GET("contexts/{id}")
    Call<Theme> find(@Path("id") Long id);

    void insert();

    void update();

    void delete(Long id);

    @GET("contexts")
    Call<List<Theme>> findAll();

    void findPage(
        int page,
        int linesPerPage,
        String orderBy,
        String direction
    );
    
}