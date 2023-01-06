package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText name1 = (EditText) findViewById(R.id.n);
        EditText email1 = (EditText) findViewById(R.id.e);
        EditText phone1 = (EditText) findViewById(R.id.ph);
        EditText pass1 = (EditText) findViewById(R.id.pa);

        Button b1 = (Button) findViewById(R.id.b1);
        Button b2 = (Button) findViewById(R.id.b2);

        DBHelper DB=new DBHelper(this);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=name1.getText().toString();
                String email=email1.getText().toString();
                String phone=phone1.getText().toString();
                String pass=pass1.getText().toString();

                Boolean checkinsertdata=DB.insertdata(name,email,phone,pass);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this,"Data inserted Successfully",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(MainActivity.this,"Data Not Inserted",Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceType")
            @Override
            public void onClick(View view) {
                Cursor res=DB.getdata();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this,"No datas found",Toast.LENGTH_SHORT).show();
                }
                StringBuffer buffer=new StringBuffer();
                        if(res.moveToNext()){
                            buffer.append("Name:"+res.getString(0)+"\n");
                            buffer.append("Email:"+res.getString(1)+"\n");
                            buffer.append("Phone:"+res.getString(2)+"\n");
                            buffer.append("Password:"+res.getString(3)+"\n");

                AlertDialog.Builder Builder=new AlertDialog.Builder(MainActivity.this);
                        Builder.setCancelable(true);
                        Builder.setMessage(buffer.toString());
                        Builder.show();
            }
            }
        });
    }
}