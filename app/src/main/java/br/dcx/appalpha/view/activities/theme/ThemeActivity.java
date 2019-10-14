package br.dcx.appalpha.view.activities.theme;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import br.dcx.appalpha.control.CuidandoDeTudo;
import com.example.anaplb.appalpha.R;
import br.dcx.appalpha.control.Som.Som;
import br.dcx.appalpha.control.TemaFactory;
import br.dcx.appalpha.control.api.RetrofitInitializer;
import br.dcx.appalpha.control.config.ButtonDelay;
import br.dcx.appalpha.model.Vocabulario;
import br.dcx.appalpha.model.bean.Challenge;
import br.dcx.appalpha.model.bean.Theme;
import br.dcx.appalpha.view.activities.ForcaActivity;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tema);

        getLayoutInflater().inflate(R.layout.activity_tema, null);
        som = new Som();

    }

    @Override
    public View onCreateView(View parent, String name, Context context, AttributeSet attrs) {
        View v = super.onCreateView(parent, name, context, attrs);
        recyclerView = v.findViewById(R.id.rcThemes);
        layManager = new LinearLayoutManager(getApplicationContext());
        return v;
    }

    public void fillRecycleView(List<Theme> themes){
        recyclerView.setLayoutManager(layManager);
        recyclerView.setAdapter(new ThemeAdapter(themes));
    }

    public void getAllChallengesFromService(){
        Call call = new RetrofitInitializer().challengeService().findAll();
        call.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                fillRecycleView((List<Theme>) response.body());
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e(TAG, "Erro ao recuperar temas");
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

        Vocabulario p = factory.pegandoPalavra(img_button);

        facade = new CuidandoDeTudo(p.getPalavras(), p.getAudios(), p.getImgs());
        Log.i("size", ""+p.retornandoNomes().size());

        ArrayList<String> palavrasUsadas = new ArrayList<>();

        intent = facade.colocandoEmIntent(intent);
        intent.putExtra("palavrasUsadas", palavrasUsadas);
        intent.putExtra("erros", 0);
        intent.putExtra("progresso", 0);
        intent.putExtra("tempo", 0.0);
        intent.putExtra("objeto", p);
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
