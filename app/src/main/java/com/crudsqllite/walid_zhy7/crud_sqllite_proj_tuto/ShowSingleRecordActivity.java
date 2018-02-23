package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto;



/**
 * Created by HP on 16/02/2018.
 */

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses.SQLiteHelper;

public class ShowSingleRecordActivity extends AppCompatActivity {

    String IDholder;
    TextView id, Fname, Lname, Address, phone_number;
    SQLiteDatabase sqLiteDatabase;
    SQLiteHelper sqLiteHelper;
    Cursor cursor;
    Button btn_Delete, btn_Edit;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;

    boolean isDeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_single_record);

        id = (TextView) findViewById(R.id.textViewID);
        Fname = (TextView) findViewById(R.id.textViewFName);
        Lname = (TextView) findViewById(R.id.textViewLName);
        Address = (TextView) findViewById(R.id.textViewAddress);
        phone_number = (TextView) findViewById(R.id.textViewPhoneNumber);

        btn_Delete = (Button)findViewById(R.id.buttonDelete);
        btn_Edit = (Button)findViewById(R.id.buttonEdit);

        sqLiteHelper = new SQLiteHelper(this);



        btn_Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                OpenSQLiteDataBase();

                SQLiteDataBaseQueryHolder = "DELETE FROM "+SQLiteHelper.TABLE_NAME+" WHERE id = "+IDholder+"";

                sqLiteDatabase.execSQL(SQLiteDataBaseQueryHolder);

                isDeleted = true;

                sqLiteDatabase.close();

                if(isDeleted){
                    Toast.makeText(ShowSingleRecordActivity.this,"Data Deleted Succesfuly", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(ShowSingleRecordActivity.this,"Error,Data Can't be Deleted", Toast.LENGTH_LONG).show();
                }

                finish();

            }
        });

        btn_Edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),EditSingleRecordActivity.class);

                intent.putExtra("EditID", IDholder);

                startActivity(intent);

            }
        });

    }

    @Override
    protected void onResume() {

        ShowSingleRecordInTextView();

        super.onResume();
    }

    public void ShowSingleRecordInTextView() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        IDholder = getIntent().getStringExtra("ListViewClickedItemValue");

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM " + SQLiteHelper.TABLE_NAME + " WHERE id = " + IDholder + "", null);

        if (cursor.moveToFirst()) {

            do {
                id.setText(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));
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

