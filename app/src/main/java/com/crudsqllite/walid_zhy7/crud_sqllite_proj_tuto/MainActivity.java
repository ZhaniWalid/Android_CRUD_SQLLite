package com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

/** Tutorial on this link : https://androidjson.com/sqlite-select-insert-update-delete-display/ **/

/** Another Import From Tuto**/

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
//import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/** End of : Another Import From Tuto**/

import com.crudsqllite.walid_zhy7.crud_sqllite_proj_tuto.SupportClasses.SQLiteHelper;

public class MainActivity extends AppCompatActivity {

    /** Declaring SQLiteDatabase, EditText, Button objects and String, Boolean variables. **/

    SQLiteDatabase sqLiteDatabaseObj;
    EditText editTextF_Name,editTextL_Name,editTextAddress, editTextPhoneNumber;
    String F_Name, L_Name,Email,Address,PhoneNumber,SQLiteDataBaseQueryHolder;
    Button btn_SubmitData, btn_DisplayData;
    Boolean EditTextIsEmpty;

    /** End Of Declaration  **/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_SubmitData = (Button)findViewById(R.id.buttonSubmit);
        btn_DisplayData = (Button)findViewById(R.id.buttonDisplay);

        editTextF_Name = (EditText)findViewById(R.id.FirstName);
        editTextL_Name = (EditText)findViewById(R.id.LastName);
        editTextAddress = (EditText)findViewById(R.id.Address);
        editTextPhoneNumber = (EditText)findViewById(R.id.PhoneNumb);

        // setOnClickListener on button.
        // Call all the 5 function inside the button click scope.
        btn_SubmitData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                SQLiteDataBaseBuild();

                SQLiteTableBuild();

                CheckEditTextStatus();

                InsertDataIntoSQLiteDatabase();

                EmptyEditTextAfterDataInsert();

            }
        });

       // Display Data when Click Button Display
        btn_DisplayData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

             // Aller du page MainActivity vers -> DisplaySQLiteDataActivity lorsqu'on appuie sur le boutton
                Intent intent = new Intent(MainActivity.this,DisplaySQLiteDataActivity.class);
                startActivity(intent);
            }
        });

       /* ma7achtnéch bih fl cas mte3na
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**  The 5 Functions eli bsh nst3mlouhm **/

    public void SQLiteDataBaseBuild(){
        sqLiteDatabaseObj = openOrCreateDatabase(SQLiteHelper.DATABASE_NAME,Context.MODE_PRIVATE,null);
    }

    public void SQLiteTableBuild() {

        /*sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME
                                  +"("+SQLiteHelper.Table_Column_ID+"INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
                                  +SQLiteHelper.Table_Column_1_FirstName+"TEXT ,"
                                  +SQLiteHelper.Table_Column_2_LastName+"TEXT ,"
                                  +SQLiteHelper.Table_Column_3_Address+"TEXT ,"
                                  +SQLiteHelper.Table_Column_4_PhoneNumb+"TEXT );");*/

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+SQLiteHelper.TABLE_NAME+" ("+SQLiteHelper.Table_Column_ID+" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+SQLiteHelper.Table_Column_1_FirstName+" VARCHAR, "+SQLiteHelper.Table_Column_2_LastName+" VARCHAR,"+SQLiteHelper.Table_Column_3_Address+" VARCHAR, "+SQLiteHelper.Table_Column_4_PhoneNumb+" VARCHAR );";
        sqLiteDatabaseObj.execSQL(CREATE_TABLE);
    }

    public void CheckEditTextStatus(){

        F_Name = editTextF_Name.getText().toString();
        L_Name = editTextL_Name.getText().toString();
        Address = editTextAddress.getText().toString();
        PhoneNumber = editTextPhoneNumber.getText().toString();

        if (TextUtils.isEmpty(F_Name) || TextUtils.isEmpty(L_Name) || TextUtils.isEmpty(Address) || TextUtils.isEmpty(PhoneNumber)){
            EditTextIsEmpty = true;
        }else{
            EditTextIsEmpty = false;
        }
    }

    public void InsertDataIntoSQLiteDatabase(){

        if(EditTextIsEmpty == false){

            SQLiteDataBaseQueryHolder = "INSERT INTO "+SQLiteHelper.TABLE_NAME+" ("+SQLiteHelper.Table_Column_1_FirstName+","+SQLiteHelper.Table_Column_2_LastName+","+SQLiteHelper.Table_Column_3_Address+","+SQLiteHelper.Table_Column_4_PhoneNumb+")VALUES"+"('"+F_Name+"', '"+L_Name+"', '"+Address+"', '"+PhoneNumber+"');";
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);
            sqLiteDatabaseObj.close();
            Toast.makeText(MainActivity.this,"Data Inserted Successfully", Toast.LENGTH_LONG).show();

        }else{

            Toast.makeText(MainActivity.this,"Please Fill All Required Fields",Toast.LENGTH_LONG).show();
        }
    }

    public void EmptyEditTextAfterDataInsert() {

        editTextF_Name.getText().clear();
        editTextL_Name.getText().clear();
        editTextAddress.getText().clear();
        editTextPhoneNumber.getText().clear();

    }

        /** End of 5 Functions **/

}
