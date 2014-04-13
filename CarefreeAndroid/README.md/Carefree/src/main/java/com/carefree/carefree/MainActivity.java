package com.carefree.carefree;

import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.Button;
import android.widget.EditText;
import java.io.FileOutputStream;
import 	android.content.Context;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.Carefree.Carefree.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn = (Button) findViewById(R.id.button_save);
        btn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Perform action on click
                saveCard();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /** Called when the user clicks the Send button */
    public void sendMessage(View view) {
        // Do something in response to button
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.first_name);
        String message = editText.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    public void saveCard(){
        String filename="mycard";
       FileOutputStream ostream;

        EditText firstName=(EditText) findViewById(R.id.first_name);
        EditText lastName=(EditText) findViewById(R.id.last_name);
        EditText contactFirstName=(EditText) findViewById(R.id.contact_first_name);
        EditText contactLastName=(EditText) findViewById(R.id.contact_last_name);
        EditText contactPhoneNumber=(EditText) findViewById(R.id.contact_phone_number);
        EditText allergies=(EditText) findViewById(R.id.allergies);

        String fn=firstName.getText().toString();
        String ln=lastName.getText().toString();
        String cfn=contactFirstName.getText().toString();
        String cln=contactLastName.getText().toString();
        String cpn=contactPhoneNumber.getText().toString();
        String al=allergies.getText().toString();
        Log.d("Debug1", "It works here");
        try{
            ostream=openFileOutput(filename,Context.MODE_PRIVATE);
            ostream.write(fn.getBytes());
            ostream.write(ln.getBytes());
            ostream.write(cfn.getBytes());
            ostream.write(cln.getBytes());
            ostream.write(cpn.getBytes());
            ostream.write(al.getBytes());
            ostream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }



}
