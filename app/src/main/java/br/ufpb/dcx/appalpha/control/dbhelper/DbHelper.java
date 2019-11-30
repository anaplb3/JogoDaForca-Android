package br.ufpb.dcx.appalpha.control.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DbHelper extends SQLiteOpenHelper {
    private static int VERSION = 1;
    private static String NAME = "appAlpha";
    public static String nomeTabela = "recordes";

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String criandoTabela = "CREATE TABLE IF NOT EXISTS " + nomeTabela + "(indice INTEGER PRIMARY KEY AUTOINCREMENT, nome VARCHAR, pontuacao REAL)";

        try {
            db.execSQL(criandoTabela);
            Log.i("info db", "pelo jeito deu certo hein");
        } catch(Exception e) {
            Log.i("info db", "então né, deu merda");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
