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

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import 	android.content.Context;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

public class MainActivity extends Activity {
    public final static String EXTRA_MESSAGE = "com.Carefree.Carefree.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //string filePath="mycard.txt";
        //File file = new File(filePath);
        //if(file.exists()){
            //Intent intent= new Intent(this,)

        //}

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

    public void saveCard() {
        String filename = getString(R.string.local_file_name);
        FileOutputStream ostream;
        boolean save = true;

        EditText firstName = (EditText) findViewById(R.id.first_name);
        EditText lastName = (EditText) findViewById(R.id.last_name);
        EditText age = (EditText) findViewById(R.id.age);
        EditText contactFirstName = (EditText) findViewById(R.id.contact_first_name);
        EditText contactLastName = (EditText) findViewById(R.id.contact_last_name);
        EditText contactPhoneNumber = (EditText) findViewById(R.id.contact_phone_number);
        EditText allergies = (EditText) findViewById(R.id.allergies);

        ArrayList<String> data = new ArrayList<String>();

        data.add(firstName.getText().toString());
        data.add(lastName.getText().toString());
        data.add(age.getText().toString());
        data.add(allergies.getText().toString());
        data.add(contactFirstName.getText().toString());
        data.add(contactLastName.getText().toString());
        data.add(contactPhoneNumber.getText().toString());

        for (int i = 0; i < 7; i++) {
            if (data.get(i).equals("")) save = false;

        }
        //Log.d("Debug1", "It works here");
        if (save) try {
            ostream = openFileOutput(filename, Context.MODE_PRIVATE);
            ostream.write(getString(R.string.local_file_header).getBytes());
            for (int i = 0; i < 7; ++i) {
                if (i != 0) ostream.write('\t');
                ostream.write(data.get(i).getBytes());
            }
            ostream.write('\n');
            ostream.close();
            uploadPatientInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
        else {
            Context context = getApplicationContext();
            CharSequence text = "You need to fill out all of the fields.";
            int duration = Toast.LENGTH_SHORT;

            Toast toast = Toast.makeText(context, text, duration);
            toast.show();
        }
    }

    public void uploadPatientInfo() {
        Firebase f = new Firebase(getString(R.string.firebase_url));

        String csvString = "";
        String filename = getString(R.string.local_file_name);
        FileInputStream fis;

        try {
            fis = openFileInput(filename);
            byte[] input = new byte[fis.available()];
            while (fis.read(input) != -1) {
                csvString += new String(input);
            }
            String[] keys = csvString.split("\n")[0].split("\t");
            String[] vals = csvString.split("\n")[1].split("\t");

            Map<String, String> map = new HashMap<String, String>();
            for (int i = 0; i < keys.length; ++i)
                map.put(keys[i], vals[i]);
            f.child("Patients").push().setValue(map);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
