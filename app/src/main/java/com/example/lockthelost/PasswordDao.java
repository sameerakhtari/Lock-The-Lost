package com.example.lockthelost;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface PasswordDao {
    @Query("SELECT * FROM Password")
  //  List<Students> getPassword();

    @Insert
    void insertPassword(Password password);

    @Query("DELETE FROM Password")
    void truncateTable();

    @Query("DELETE FROM Password WHERE passwordId=:passId")
    void deleteFromPasswordId(int passId);

    @Query("UPDATE Password SET password=:password WHERE passwordId=:passId")
    void updateExistingRow(int passId, String password);


}
