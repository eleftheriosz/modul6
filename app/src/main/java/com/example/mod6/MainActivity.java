package com.example.mod6;

import androidx.appcompat.app.AppCompatActivity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{
    private EditText editTextName;
    private EditText editTextJus;
    private EditText editTextEma;

    private Button buttonAdd;
    private Button buttonView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inisialisasi dari View
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextJus = (EditText) findViewById(R.id.editTextJurs);
        editTextEma = (EditText) findViewById(R.id.editTextMail);

        buttonAdd = (Button) findViewById(R.id.buttonAdd);
        buttonView = (Button) findViewById(R.id.buttonView);

        //Setting listeners to button
      buttonAdd.setOnClickListener(this);
       buttonView.setOnClickListener(this);
    }
    private void tambahmah(){

        final String name = editTextName.getText().toString().trim();
        final String jurusan = editTextJus.getText().toString().trim();
        final String email = editTextEma.getText().toString().trim();

        class tambahmah extends AsyncTask<Void,Void,String>{

            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                loading = ProgressDialog.show(MainActivity.this,"Menambahkan...","Tunggu...",false,false);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                loading.dismiss();
                Toast.makeText(MainActivity.this,s,Toast.LENGTH_LONG).show();
            }

            @Override
            protected String doInBackground(Void... v) {
                HashMap<String,String> params = new HashMap<>();
                params.put(konfigurasi.KEY_EMP_NAMA,name);
                params.put(konfigurasi.KEY_EMP_JURUSAN,jurusan);
                params.put(konfigurasi.KEY_EMP_EMAIL,email);

                RequestHandler rh = new RequestHandler();
                String res = rh.sendPostRequest(konfigurasi.URL_ADD, params);
                return res;
            }
        }

        tambahmah ae = new tambahmah();
        ae.execute();
    }

    @Override
    public void onClick(View v) {
        if(v == buttonAdd){
            tambahmah();
        }

        if(v == buttonView){
            startActivity(new Intent(this, TampilSemuaMhs.class));
        }
    }
}
