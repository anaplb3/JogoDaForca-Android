package br.ufpb.dcx.appalpha.control.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import br.ufpb.dcx.appalpha.control.dbhelper.DbHelper;
import br.ufpb.dcx.appalpha.model.bean.User;

public class UsersSqlService {
    private static UsersSqlService instance;
    private DbHelper db;
    private SQLiteDatabase writableDb;
    private SQLiteDatabase readableDb;

    public static UsersSqlService getInstance(Context context){
        if(instance == null){
            instance = new UsersSqlService(context);
        }
        return instance;
    }

    private UsersSqlService(Context context) {
        this.db = new DbHelper(context);
        this.writableDb = db.getWritableDatabase();
        this.readableDb = db.getReadableDatabase();
    }

    public void insert(User user){

    }

    public void update(User user){

    }

    public void delete(User user){

    }

    public void delete(Long id){

    }

    public User get(Long id){
        return null;
    }
}
