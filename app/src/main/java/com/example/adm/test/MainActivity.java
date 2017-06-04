package com.example.adm.test;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteException;
import android.preference.DialogPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstname, lastname;
    TextView textView;
    DB_Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstname = (EditText)findViewById(R.id.firstname);
        lastname = (EditText)findViewById(R.id.lastname);
        textView = (TextView) findViewById(R.id.textView);

        controller = new DB_Controller(this, "",null,1);

    }

    public void btnClick(View view) {

        switch (view.getId()){
            case R.id.btnSave:
                try {

                }catch (SQLiteException e){
                    Toast.makeText(MainActivity.this, "ALREADY EXISTS", Toast.LENGTH_SHORT).show();
                }
                controller.insert_student(firstname.getText().toString(),lastname.getText().toString());
                EditText x = firstname;



                break;

            case R.id.btnDelete:
                controller.delete_student(firstname.getText().toString());
                break;

            case R.id.btnAtt:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ENTER NEW FIRSTNAME");

                final EditText new_firstname = new EditText(this);
                dialog.setView(new_firstname);

                dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                     controller.update_student(firstname.getText().toString(),new_firstname.getText().toString());

                    }
                });

                dialog.show();

                break;
            case R.id.btnList:
                controller.list_all_students(textView);
                break;

        }
    }
}
