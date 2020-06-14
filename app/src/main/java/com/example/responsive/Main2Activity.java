package com.example.responsive;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class Main2Activity extends AppCompatActivity {

    EditText editText2, editText3;
    Button button;
    Database databasel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main2 );
        editText2 = findViewById( R.id.ed1 );
        editText3 = findViewById( R.id.ed2 );
        button = findViewById( R.id.log );
        databasel = new Database( this );
        button.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s3 = editText2.getText().toString();
                String s4 = editText3.getText().toString();
                boolean check1 = databasel.check( s3, s4 );
                if (check1) {
                    Toast.makeText( Main2Activity.this,
                                    "Login Successfully", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( Main2Activity.this,
                                    "Login Unsuccessfully", Toast.LENGTH_SHORT ).show();
                }
            }
        } );
    }
}
