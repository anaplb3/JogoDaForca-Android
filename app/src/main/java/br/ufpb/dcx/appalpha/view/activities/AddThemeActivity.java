package br.ufpb.dcx.appalpha.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import br.ufpb.dcx.appalpha.R;
import br.ufpb.dcx.appalpha.control.api.RetrofitInitializer;
import br.ufpb.dcx.appalpha.control.service.ThemeSqlService;
import br.ufpb.dcx.appalpha.control.service.ThemesApiService;
import br.ufpb.dcx.appalpha.model.bean.Theme;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddThemeActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AddThemeActivity";
    private TextInputLayout tlIdTheme;
    private Button btnImport;
    private ThemesApiService themesApiService;
    private ThemeSqlService themeSqlService;
    private ImageButton back_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_theme);
        tlIdTheme = findViewById(R.id.tlIdTheme);
        btnImport = findViewById(R.id.btnImport);
        themesApiService = ThemesApiService.getInstance();
        themeSqlService = ThemeSqlService.getInstance(getApplicationContext());
        back_btn = findViewById(R.id.back_btn);
        back_btn.setOnClickListener(this);

        btnImport.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case(R.id.btnImport):
                Long id = Long.parseLong(tlIdTheme.getEditText().getText().toString());

                Call call = new RetrofitInitializer().contextService().find(id);
                call.enqueue(new Callback<Theme>() {
                    @Override
                    public void onResponse(Call<Theme> call, Response<Theme> response) {
                        if(response.body() != null){
                            Theme theme = response.body();
                            themeSqlService.insert(theme, null);
                            Toast.makeText(getApplicationContext(), "Tema " + theme.getName() + " importado com sucesso!", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Theme de id " + id + " recuperado com sucesso!");
                            finish();

                        }else{
                            Toast.makeText(getApplicationContext(), "Erro ao recuperar tema, verifique se o id inserido é válido.", Toast.LENGTH_SHORT).show();
                            Log.i(TAG, "Erro ao recuperar theme com id " + id);
                        }
                    }

                    @Override
                    public void onFailure(Call<Theme> call, Throwable t) {
                        Log.e(TAG, "Erro ao recuperar tema: "+t.getMessage());
                    }
                });



                break;

            case(R.id.back_btn):
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
