package com.example.lockthelost;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity
public class Password
{




        @PrimaryKey(autoGenerate = true)
        public int passwordId;

        @ColumnInfo(name = "password")
        String password;


        public Password(String password) {
            this.password = password;
        }

        public int getPasswordId() {
            return passwordId;
        }

        public void setPasswordId(int passwordId) {
            this.passwordId = passwordId;
        }


        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }


}
