package com.example.responsive;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {

    Button button1, button2, button3;
    EditText editText1, editText2;
    TextView textView;
    Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        button1 = findViewById( R.id.kow );
        button2 = findViewById( R.id.join_us );
        button3 = findViewById( R.id.logi );
        textView = findViewById( R.id.forget );
        editText1 = findViewById( R.id.eail );
        editText2 = findViewById( R.id.password );
        database = new Database( this );
        button1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent( MainActivity.this, Main2Activity.class );
                startActivity( intent );
            }
        } );
        button2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor ifView = database.view_data();
                if (ifView.getCount()==0){
                    showMessage( "ERROR", "NOT FOUND" );
                }else {
                    StringBuilder stringBuilder = new StringBuilder(  );
                    while (ifView.moveToNext()){
                        stringBuilder.append( "ID: " ).append( ifView.getString( 0) ).append( "\n" );
                        stringBuilder.append( "EMAIL: " ).append( ifView.getString( 1) ).append( "\n" );
                        stringBuilder.append( "PASSWORD: " ).append( ifView.getString( 2) ).append( "\n" );
                    }
                    showMessage( "SQLite DATA", stringBuilder.toString() );
                }
            }
        } );
        button3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s1 = editText1.getText().toString();
                String s2 = editText2.getText().toString();
                boolean ifAdded = database.add_data( s1, s2 );
                if (ifAdded) {
                    Toast.makeText( MainActivity.this, "Registration completed", Toast.LENGTH_SHORT ).show();
                } else {
                    Toast.makeText( MainActivity.this, "Registration NOT completed", Toast.LENGTH_SHORT ).show();

                }
            }
        } );
        textView.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText( MainActivity.this, "Forgot password ??", Toast.LENGTH_SHORT ).show();
            }
        } );
    }
    public void showMessage(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder( this );
        builder.setTitle( title );
        builder.setMessage( message );
        builder.show();
    }
}
