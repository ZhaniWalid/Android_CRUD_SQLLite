package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto;



/**
 * Created by HP on 16/02/2018.
 */

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses.SQLiteHelper;

public class EditSingleRecordActivity extends AppCompatActivity {

    EditText Fname, Lname, Address,phone_number;
    Button btn_update;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    String IDholder;
    String SQLiteDataBaseQueryHolder ;
    SQLiteDatabase sqLiteDatabaseObj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_single_record);

        Fname = (EditText) findViewById(R.id.EditTextFName);
        Lname = (EditText) findViewById(R.id.EditTextLName);
        Address = (EditText) findViewById(R.id.EditTextAddress);
        phone_number = (EditText) findViewById(R.id.EditTextPhoneNumb);

        btn_update = (Button) findViewById(R.id.buttonUpdate);

        sqLiteHelper = new SQLiteHelper(this);

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String GetFName = Fname.getText().toString();
                String GetLName = Fname.getText().toString();
                String GetAdr = Address.getText().toString();
                String GetPhoneNumber = phone_number.getText().toString();

                OpenSQLiteDataBase();

                if (TextUtils.isEmpty(GetFName) || TextUtils.isEmpty(GetLName) || TextUtils.isEmpty(GetAdr) || TextUtils.isEmpty(GetPhoneNumber)){

                    Toast.makeText(EditSingleRecordActivity.this,"Please fill all required fields,Data Update Failed", Toast.LENGTH_LONG).show();

                }else {

                    SQLiteDataBaseQueryHolder = "UPDATE " + SQLiteHelper.TABLE_NAME + " SET " + SQLiteHelper.Table_Column_1_FirstName + " = '" + GetFName + "' , " + SQLiteHelper.Table_Column_2_LastName + " = '" + GetLName + "' , " + SQLiteHelper.Table_Column_3_Address + " = '" + GetAdr + "' , " + SQLiteHelper.Table_Column_4_PhoneNumb + " = '" + GetPhoneNumber + "' WHERE id = " + IDholder + "";

                    sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

                    sqLiteDatabase.close();

                    Toast.makeText(EditSingleRecordActivity.this, "Data Updated Successfully", Toast.LENGTH_LONG).show();

                }

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSRecordInEditText();

        super.onResume();
    }

    public void ShowSRecordInEditText() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("EditID");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                Fname.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_FirstName)));
                Lname.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_LastName)));
                Address.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Address)));
                phone_number.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_PhoneNumb)));
            }
            while (cursor.moveToNext());

            cursor.close();

        }
    }

    public void OpenSQLiteDataBase(){

        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

}
