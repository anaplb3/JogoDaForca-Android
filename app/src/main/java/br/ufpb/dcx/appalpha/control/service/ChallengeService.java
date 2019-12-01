package br.ufpb.dcx.appalpha.control.service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.ufpb.dcx.appalpha.control.dbhelper.DbHelper;
import br.ufpb.dcx.appalpha.model.bean.Challenge;
import br.ufpb.dcx.appalpha.model.bean.User;

public class ChallengeService {
    private final String TAG = "ChallengeService";
    private static ChallengeService instance;
    private DbHelper db;
    private SQLiteDatabase writableDb;
    private SQLiteDatabase readableDb;
    private UsersService usersService;

    public static ChallengeService getInstance(Context context){
        if(instance == null){
            instance = new ChallengeService(context);
        }
        return instance;
    }

    private ChallengeService(Context context) {
        this.db = new DbHelper(context);
        this.writableDb = db.getWritableDatabase();
        this.readableDb = db.getReadableDatabase();
        this.usersService = UsersService.getInstance(context);
    }

    public Long insert(Challenge challenge){
        ContentValues cv = new ContentValues();
        Long id = -1L;
        try {
            cv.put("word", challenge.getWord());
            cv.put("soundUrl", challenge.getSoundUrl());
            cv.put("videoUrl", challenge.getVideoUrl());
            cv.put("imageUrl", challenge.getImageUrl());
            cv.put("user_creator", challenge.getCreator() != null ? challenge.getCreator().getId() : null);

            id = this.writableDb.insert(DbHelper.CHALLENGES_TABLE, null, cv);
            Log.i(TAG, challenge.getWord() + " added in db!");

        } catch(Exception e) {
            Log.e(TAG, e.getMessage());
        } finally {
            cv.clear();
        }

        return id;
    }

    public void update(Challenge challenge){

    }

    public void delete(Challenge challenge){

    }

    public void delete(Long id){

    }

    public Challenge get(Long id){
        return null;
    }

    public List<Challenge> getRelatedChallenges(Long theme_id){
        String word, soundUrl, videoUrl, imageUrl;
        Long id, user_creator;
        word = soundUrl = videoUrl = imageUrl = "";

        Log.i(TAG, ""+theme_id);

        List<Challenge> challenges = new ArrayList<>();

        String selectQuery = "SELECT " + DbHelper.CHALLENGES_TABLE + ".id, " + DbHelper.CHALLENGES_TABLE + ".word, " + DbHelper.CHALLENGES_TABLE + ".user_creator, " + DbHelper.CHALLENGES_TABLE + ".soundUrl, " + DbHelper.CHALLENGES_TABLE + ".videoUrl, " + DbHelper.CHALLENGES_TABLE + ".imageUrl FROM "
                + DbHelper.CHALLENGES_TABLE +
                " INNER JOIN " +
                DbHelper.CHALLENGE_THEME_TABLE + " CT ON "
                + DbHelper.CHALLENGES_TABLE + ".id = CT.challenge_id" +
                " AND CT.theme_id = " + theme_id;

        Cursor cursor = readableDb.rawQuery(selectQuery, null);

        if(cursor.moveToFirst()){
            do {
                id = cursor.getLong(0);
                word = cursor.getString(1);
                user_creator = cursor.getLong(2);
                soundUrl = cursor.getString(3);
                videoUrl = cursor.getString(4);
                imageUrl = cursor.getString(5);

                User creator = usersService.get(user_creator);

                Challenge c = new Challenge(id,word, creator, soundUrl, videoUrl, imageUrl);
                challenges.add(c);
            } while (cursor.moveToNext());
        }

        cursor.close();
        Log.i(TAG, "TAMANHO " + challenges.size());
        return challenges;
    }
}
