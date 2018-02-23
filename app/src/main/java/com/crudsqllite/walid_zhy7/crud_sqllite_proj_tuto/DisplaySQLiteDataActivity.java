package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

import android.widget.EditText;

import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses.SQLiteHelper;
import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses.ListAdapter;

/**
 * Created by HP on 16/02/2018.
 */


public class DisplaySQLiteDataActivity extends AppCompatActivity {

    SQLiteHelper sqLiteHelper;
    SQLiteDatabase sqLiteDatabase;
    Cursor cursor;
    ListAdapter listAdapter ;
    ListView LISTVIEW;

    ArrayList<String> ID_Array;
    ArrayList<String> FNAME_Array;
    ArrayList<String> LNAME_Array;
    ArrayList<String> Address_Array;
    ArrayList<String> PHONE_NUMBER_Array;

    public ArrayList<String> ListViewClickItemArray = new ArrayList<String>();
    String TempHolder ;

    EditText theFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_sqlite_data);

        LISTVIEW = (ListView) findViewById(R.id.listView1);
        theFilter = (EditText) findViewById(R.id.searchFilter);

        ID_Array = new ArrayList<String>();

        FNAME_Array = new ArrayList<String>();

        LNAME_Array = new ArrayList<String>();

        Address_Array = new ArrayList<String>();

        PHONE_NUMBER_Array = new ArrayList<String>();

        sqLiteHelper = new SQLiteHelper(this);

        LISTVIEW.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // TODO Auto-generated method stub

                Intent intent = new Intent(getApplicationContext(),ShowSingleRecordActivity.class);

                intent.putExtra("ListViewClickedItemValue", ListViewClickItemArray.get(position).toString());

                startActivity(intent);

            }
        });

        LISTVIEW.setTextFilterEnabled(true);

    }

    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        sqLiteDatabase = sqLiteHelper.getWritableDatabase();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM "+SQLiteHelper.TABLE_NAME+"", null);

        ID_Array.clear();
        FNAME_Array.clear();
        LNAME_Array.clear();
        Address_Array.clear();
        PHONE_NUMBER_Array.clear();

        if (cursor.moveToFirst()) {
            do {

                ID_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                //Inserting Column ID into Array to Use at ListView Click Listener Method.
                ListViewClickItemArray.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_ID)));

                FNAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_1_FirstName)));
                LNAME_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_2_LastName)));
                Address_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_3_Address)));
                PHONE_NUMBER_Array.add(cursor.getString(cursor.getColumnIndex(SQLiteHelper.Table_Column_4_PhoneNumb)));


            } while (cursor.moveToNext());
        }

        listAdapter = new ListAdapter(DisplaySQLiteDataActivity.this,

                ID_Array,
                FNAME_Array,
                LNAME_Array,
                Address_Array,
                PHONE_NUMBER_Array
        );

        LISTVIEW.setAdapter(listAdapter);

        cursor.close();

        theFilter.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //charSequence = theFilter.getText().toString().toLowerCase(Locale.getDefault());
                listAdapter.getFilter().filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {
               //String TextSearch = theFilter.getText().toString().toLowerCase(Locale.getDefault());
               //listAdapter.Filter(TextSearch);
            }
        });
    }
}

