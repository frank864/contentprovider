package com.example.conctactcontentprovider;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG= "MainActivity";
    private TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= findViewById(R.id.textview);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},101);
        }
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_CONTACTS},101);
        }
        tempMethod();
    }

    private void tempMethod (){
        Cursor cursor = getContentResolver().query(ContactsContract.RawContacts.CONTENT_URI,null,null,null,null);

        if (null != cursor){
            if (cursor.moveToNext()){
                String text ="";

                for (int i=0; i<cursor.getCount();i++){
                    for (int j = 0; j<cursor.getColumnCount(); j++){
                        text +=cursor.getColumnName(j)+ ":" +cursor.getString(j) + "\n";
                    }
                    text+="********\n";
                    cursor.moveToNext();
                }
                textView.setText(text);
            }
            cursor.close();
        }
    }
}