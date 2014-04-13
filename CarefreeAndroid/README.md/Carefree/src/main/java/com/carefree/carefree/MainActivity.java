package com.carefree.carefree;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Intent;
import android.widget.EditText;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.Carefree.Carefree.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
        try{
            outputStream=openFileOutput(filename,Context.MODE_PRIVATE)
            outputStream.write(fn.getBytes());
            outputStream.write(ln.getBytes());
            outputStream.write(cfn.getBytes());
            outputStream.write(cln.getBytes());
            outputStream.write(cpn.getBytes());
            outputStream.write(al.getBytes());
            outputStream.close();
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }



}
