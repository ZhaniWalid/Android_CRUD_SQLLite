package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses;



/**
 * Created by HP on 16/02/2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SQLiteHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME="AndroidCRUD_db_tuto.db";

    public static final String TABLE_NAME="AndroidJSonCRUDTable";

    public static final String Table_Column_ID="Id";

    public static final String Table_Column_1_FirstName="FirstName";

    public static final String Table_Column_2_LastName="LastName";

    public static final String Table_Column_3_Address="Address";

    public static final String Table_Column_4_PhoneNumb="PhoneNumber";

    public SQLiteHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+Table_Column_1_FirstName+" VARCHAR, "+Table_Column_2_LastName+" VARCHAR,"+Table_Column_3_Address+" VARCHAR, "+Table_Column_4_PhoneNumb+" VARCHAR );";

        /*String CREATE_TABLE = "CREATE TABLE IF NOT EXISTS "+TABLE_NAME
                +"("+Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                +Table_Column_1_FirstName+"VARCHAR,"
                +Table_Column_2_LastName+"VARCHAR,"
                +Table_Column_3_Address+"VARCHAR,"
                +Table_Column_4_PhoneNumb+"VARCHAR ";*/

        database.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);

    }
}
