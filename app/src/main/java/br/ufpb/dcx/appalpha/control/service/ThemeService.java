package br.ufpb.dcx.appalpha.control.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.appalpha.control.dbhelper.DbHelper;
import br.ufpb.dcx.appalpha.model.bean.Challenge;
import br.ufpb.dcx.appalpha.model.bean.Theme;
import br.ufpb.dcx.appalpha.model.bean.User;

public class ThemeService {
    private final String TAG = "ThemeService";
    private static ThemeService instance;
    private DbHelper db;
    private SQLiteDatabase writableDb;
    private SQLiteDatabase readableDb;
    private ChallengeService challengeService;
    private UsersService usersService;

    public static ThemeService getInstance(Context context){
        if(instance == null){
            instance = new ThemeService(context);
        }
        return instance;
    }

    private ThemeService(Context context) {
        this.db = new DbHelper(context);
        this.writableDb = db.getWritableDatabase();
        this.readableDb = db.getReadableDatabase();
        this.challengeService = ChallengeService.getInstance(context);
        this.usersService = UsersService.getInstance(context);
    }

    public Long insert(Theme theme, @Nullable List<Challenge> relatedChallenges){
        ContentValues cv = new ContentValues();
        Long id = -1l;
        try {
            cv.put("name", theme.getName());
            cv.put("soundUrl", theme.getSoundUrl());
            cv.put("videoUrl", theme.getVideoUrl());
            cv.put("imageUrl", theme.getImageUrl());
            cv.put("user_creator", theme.getCreator() != null ? theme.getCreator().getId() : null);

            id = this.writableDb.insert(DbHelper.THEMES_TABLE, null, cv);
            Log.i(TAG, theme.getName() + " added in db!");

            insertThemeRelatedChallenges(id, relatedChallenges);
        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cv.clear();
        }

        return id;
    }

    private void insertThemeRelatedChallenges(Long theme_id, List<Challenge> relatedChallenges){
        ContentValues cv = new ContentValues();

        for(Challenge c : relatedChallenges){
            try {
                cv.put("challenge_id", challengeService.insert(c));
                cv.put("theme_id", theme_id);
                this.writableDb.insert(DbHelper.CHALLENGE_THEME_TABLE, null, cv);

                Log.i(TAG, c.getWord() + " added relationship by theme id " + theme_id + ".");

            } catch(Exception e) {
                Log.e(TAG, e.getMessage());
            }
            cv.clear();
        }
    }

    public void update(Theme theme){

    }

    public void delete(Theme theme){

    }

    public void delete(Long id){

    }

    public Theme get(Long id){
        String name, soundUrl, videoUrl, imageUrl;
        name = soundUrl = videoUrl = imageUrl = "";

        String selectQuery = "SELECT name, soundUrl, videoUrl, imageUrl FROM " + DbHelper.THEMES_TABLE + " WHERE id = ?";

        Cursor cursor = readableDb.rawQuery(selectQuery, new String[] { Long.toString(id) });

        if(cursor.moveToFirst()){
            name = cursor.getString(0);
            soundUrl = cursor.getString(1);
            videoUrl = cursor.getString(2);
            imageUrl = cursor.getString(3);
        }

        cursor.close();

        return new Theme(name, soundUrl, videoUrl, imageUrl);
    }

    public List<Theme> getAll(){
        String name, soundUrl, videoUrl, imageUrl;
        Long id, user_creator;
        name = soundUrl = videoUrl = imageUrl = "";

        List<Theme> themes = new ArrayList<>();

        String selectQuery = "SELECT id, name, user_creator, soundUrl, videoUrl, imageUrl FROM " + DbHelper.THEMES_TABLE;

        Cursor cursor = readableDb.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                id = cursor.getLong(0);
                name = cursor.getString(1);
                user_creator = cursor.getLong(2);
                soundUrl = cursor.getString(3);
                videoUrl = cursor.getString(4);
                imageUrl = cursor.getString(5);
                List<Challenge> relatedChallenges = challengeService.getRelatedChallenges(id);
                User creator = usersService.get(user_creator);
                Theme t = new Theme(id, name, creator, imageUrl, soundUrl, videoUrl, relatedChallenges);
                themes.add(t);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return themes;
    }
}
