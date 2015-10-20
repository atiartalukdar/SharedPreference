package com.nothing2says.talukdar.sharedpreference;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText ed;
    TextView tv;
    protected final static int DEFAULT = 0;
    int temp = DEFAULT,input = DEFAULT ;
    //String[] mriTable = {"AND","ADD","LDA","STA","BUN","BSA","ISZ"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ed = (EditText) findViewById(R.id.editText);
        tv = (TextView) findViewById(R.id.textView);
        SharedPreferences sharedPreferences = getSharedPreferences("MyData", Context.MODE_PRIVATE);

        temp = sharedPreferences.getInt("highScore",DEFAULT);
        if (temp==DEFAULT){
            Toast.makeText(this, "Data can't Load", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(this,"Load Successfull",Toast.LENGTH_LONG).show();
            tv.setText(""+temp);
        }
    }


    public void convert(View view){

        if (ed.getText().toString().trim().length()==0){
            showMessage("Error", "Please Enter all values");
            return;
        }
        else{
            input = Integer.parseInt(ed.getText().toString());
            if(input>temp){
                temp = input;
            }
            SharedPreferences sharedPreferences =  getSharedPreferences("MyData",Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();

            editor.putInt("highScore", temp);
            editor.commit();

            ed.setText("");
            tv.setText(""+temp);
        }

    }


    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);

        builder.show();
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
}
