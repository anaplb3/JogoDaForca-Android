package br.ufpb.dcx.appalpha.view.activities.theme;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.widget.ImageView;

import br.ufpb.dcx.appalpha.control.CuidandoDeTudo;
import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.Som.Som;
import br.ufpb.dcx.appalpha.control.TemaFactory;
import br.ufpb.dcx.appalpha.control.api.RetrofitInitializer;
import br.ufpb.dcx.appalpha.control.config.ButtonDelay;
import br.ufpb.dcx.appalpha.model.bean.Theme;
import br.ufpb.dcx.appalpha.view.activities.ForcaActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.ArrayList;
import java.util.List;


public class ThemeActivity extends AppCompatActivity {
    private final String TAG = "ThemeActivity";
    Som som;
    CuidandoDeTudo facade;
    int idSom;
    Intent intent;
    private LinearLayoutManager layManager;
    private RecyclerView recyclerView;
    private List<Theme> themes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        getLayoutInflater().inflate(R.layout.activity_tema, null);
        som = new Som();

        recyclerView = findViewById(R.id.rcThemes);
        layManager = new LinearLayoutManager(getApplicationContext());

    }

    public void fillRecycleView(List<Theme> themes){
        recyclerView.setLayoutManager(layManager);
        recyclerView.setAdapter(new ThemeAdapter(themes, getApplicationContext()));
    }

    @Override
    protected void onResume() {
        super.onResume();
        addDefaultThemes();
        getAllChallengesFromService();
        fillRecycleView(themes);
    }

    public void addDefaultThemes(){
        themes.add(new Theme("Comida", R.drawable.comida, null, null));
        themes.add(new Theme("Cidade", R.drawable.cidade, null, null));
        themes.add(new Theme("Cores", R.drawable.cores, null, null));
        themes.add(new Theme("Cozinha", R.drawable.cozinha, null, null));
        themes.add(new Theme("Natureza", R.drawable.natureza, null, null));
        themes.add(new Theme("Frutas", R.drawable.frutas, null, null));
    }

    public void getAllChallengesFromService(){
        Call call = new RetrofitInitializer().contextService().findAll();
        call.enqueue(new Callback<List<Theme>>() {
            @Override
            public void onResponse(Call<List<Theme>> call, Response<List<Theme>> response) {
                List<Theme> reponseBody = response.body();
                for(Theme t : reponseBody){
                    themes.add(t);
                }
                fillRecycleView(themes);
            }

            @Override
            public void onFailure(Call<List<Theme>> call, Throwable t) {
                Log.e(TAG, "Erro ao recuperar temas: "+t.getMessage());
            }
        });
    }

    public void botaoEscolha(ImageView img_button) {

        som.playSound(getApplicationContext(), idSom);
        int millis = som.getDuracao();

        Log.i("millis tema", ""+millis);

        intent = new Intent(ThemeActivity.this, ForcaActivity.class);

        Log.i("botao", "botaoEscolha");

        TemaFactory factory = new TemaFactory();

        //Vocabulario p = factory.pegandoPalavra(img_button);

        //facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        //Log.i("size", ""+p.retornandoNomes().size());

        ArrayList<String> palavrasUsadas = new ArrayList<>();

        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("palavrasUsadas", palavrasUsadas);
        intent.putExtra("erros", 0);
        intent.putExtra("progresso", 0);
        intent.putExtra("tempo", 0.0);
        //intent.putExtra("objeto", p);
        intent.putExtra("somaErros", 0);


        Handler handler = new Handler();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                som.stopSound();
                if(ButtonDelay.testClique(1000)) {
                    startActivity(intent);
                }

            }
        }, millis);

    }

}
