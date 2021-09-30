package com.example.lockthelost;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Password.class},version = 1,exportSchema = false)
public abstract class PasswordDb extends RoomDatabase {
   // public abstract StudentsDao studentsDao();
    private static PasswordDb INSTANCE;
    public static PasswordDb getDbInstance(Context context){
        if(INSTANCE==null){
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),PasswordDb.class,"Password.db")
                    .allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }

}
