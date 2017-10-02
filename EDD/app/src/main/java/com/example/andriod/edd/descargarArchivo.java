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

public class descargarArchivo extends AppCompatActivity {

    private EditText user;
    private EditText archivo;
    private EditText password;
    private Button descargar;



    public static final String ROOT_URL = "http://192.168.1.7:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_descargar_archivo);

        user = (EditText) findViewById(R.id.editText13);
        archivo = (EditText) findViewById(R.id.editText9);
        password = (EditText) findViewById(R.id.editText10);
        descargar = (Button)findViewById(R.id.button11);

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                descargarArchivo();
            }
        });
    }


    public void descargarArchivo(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.descargarArchivo(
                archivo.getText().toString(),
                password.getText().toString(),
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
                            Intent perri = new Intent(descargarArchivo.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(descargarArchivo.this,"SE DESCARGO: " + output,Toast.LENGTH_LONG).show();
                        }  else {
                            Toast.makeText(descargarArchivo.this,"NO SE DESCARGO EL ARCHIVO",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(descargarArchivo.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }


}
