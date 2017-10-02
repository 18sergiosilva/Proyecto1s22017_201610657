package com.example.andriod.edd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class subirArchivoNube extends AppCompatActivity {

    private EditText archivo;
    private EditText user;
    private Button subir;

    public static final String ROOT_URL = "http://192.168.1.7:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_archivo_nube);

        archivo = (EditText)findViewById(R.id.editText11);
        user = (EditText)findViewById(R.id.editText12);
        subir = (Button)findViewById(R.id.button12);

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArchivo();
            }
        });
    }



    public void subirArchivo(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.subirArchivo(
                archivo.getText().toString(),
                user.getText().toString(),

                new Callback<Response>(){
                    @Override
                    public void success(Response result, Response response){
                        BufferedReader reader = null;
                        String output = "";
                        try{
                            reader = new BufferedReader(new InputStreamReader(result.getBody().in()));
                            output = reader.readLine();

                        } catch (IOException e){
                            e.printStackTrace();
                        }
                        if (output.equals("valido")){
                            Intent perri = new Intent(subirArchivoNube.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(subirArchivoNube.this,"ARCHIVO SUBIDO CORRECTAMENTE",Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(subirArchivoNube.this,"ARCHIVO NO SUBIDO CORRECTAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(subirArchivoNube.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }

}
