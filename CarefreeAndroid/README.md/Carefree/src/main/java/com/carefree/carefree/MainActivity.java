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
        int save=1;

        EditText firstName=(EditText) findViewById(R.id.first_name);
        EditText lastName=(EditText) findViewById(R.id.last_name);
        EditText age=(EditText) findViewById(R.id.age);
        EditText contactFirstName=(EditText) findViewById(R.id.contact_first_name);
        EditText contactLastName=(EditText) findViewById(R.id.contact_last_name);
        EditText contactPhoneNumber=(EditText) findViewById(R.id.contact_phone_number);
        EditText allergies=(EditText) findViewById(R.id.allergies);

        String data[7];

        data[0]=firstName.getText().toString();
        data[1]=lastName.getText().toString();
        data[2]=age.getText().toString();
        data[3]=allergies.getText().toString();
        data[4]=contactFirstName.getText().toString();
        data[5]=contactLastName.getText().toString();
        data[6]=contactPhoneNumber.getText().toString();
        for(int i=0;i<6;i++){
                   if(data[i].equals("")) save=0;

        }
    if(save){
            //Log.d("Debug1", "It works here");
            try{
                ostream=openFileOutput(filename,Context.MODE_PRIVATE);
                ostream.write(data[0].getBytes());
                ostream.write(data[1].getBytes());
                ostream.write(data[2].getBytes());
                ostream.write(data[3].getBytes());
                ostream.write(data[4].getBytes());
                ostream.write(data[5].getBytes());
                ostream.write(data[6].getBytes());
                ostream.close();
            }
            catch(Exception e){
                e.printStackTrace();
            }
    }
    else{
        Context context = getApplicationContext();
        CharSequence text = "You need to fill out all of the fields.";
        int duration = Toast.LENGTH_SHORT;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }


}
