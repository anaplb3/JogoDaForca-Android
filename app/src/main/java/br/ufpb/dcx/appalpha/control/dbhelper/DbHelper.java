package br.ufpb.dcx.appalpha.control.dbhelper;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DbHelper extends SQLiteOpenHelper {
    private final String TAG = "DbHelper";
    private static final int VERSION = 1;
    private static final String NAME = "AppAlpha";
    /* Table names */
    public final static String RECORDS_TABLE = "records";
    public final static String THEMES_TABLE = "themes";
    public final static String CHALLENGES_TABLE = "challenges";
    public final static String USERS_TABLE = "users";
    public final static String CHALLENGE_THEME_TABLE = "challenge_theme";

    public DbHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String activateForeignKey = "PRAGMA foreign_keys = ON";
        String recordsSql = "CREATE TABLE IF NOT EXISTS " + RECORDS_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, points REAL)";
        String themesSql = "CREATE TABLE IF NOT EXISTS " + THEMES_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, soundUrl VARCHAR, videoUrl VARCHAR, imageUrl VARCHAR, user_creator INTEGER, FOREIGN KEY (user_creator) REFERENCES users(id))";
        String challengesSql = "CREATE TABLE IF NOT EXISTS " + CHALLENGES_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR, soundUrl VARCHAR, videoUrl VARCHAR, imageUrl VARCHAR, user_creator INTEGER, FOREIGN KEY (user_creator) REFERENCES users(id))";
        String usersSql = "CREATE TABLE IF NOT EXISTS " + USERS_TABLE + "(id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR, email VARCHAR, password VARCHAR)";
        String challenge_themeSql = "CREATE TABLE IF NOT EXISTS " + CHALLENGE_THEME_TABLE + "(challenge_id, theme_id INTEGER, FOREIGN KEY (challenge_id) REFERENCES challenges(id), FOREIGN KEY (theme_id) REFERENCES themes(id))";

        List<String> sqls = new ArrayList<>(Arrays.asList(activateForeignKey, recordsSql, themesSql, challengesSql, usersSql, challenge_themeSql));

        for(String s: sqls){
            try {
                db.execSQL(s);
                Log.i(TAG, s);
            } catch(SQLException e) {
                Log.i(TAG, e.getMessage());
            }
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
