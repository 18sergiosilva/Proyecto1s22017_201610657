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

public class subirArchivoCarpeta extends AppCompatActivity {


    private EditText nombreArchivo;
    private EditText nombreCarpeta;
    private EditText nombreArchivo2;
    private Button subirMiCarpeta;
    private Button subirACarpeta;





    public static final String ROOT_URL = "http://192.168.1.7:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_archivo_carpeta);

        nombreArchivo = (EditText)findViewById(R.id.editText6);
        nombreCarpeta = (EditText)findViewById(R.id.editText7);
        nombreArchivo2 = (EditText)findViewById(R.id.editText8);
        subirMiCarpeta = (Button)findViewById(R.id.button9);
        subirACarpeta = (Button)findViewById(R.id.button10);

        subirMiCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArchivoMiCarpeta();
            }
        });


        subirACarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subirArchivoACarpeta();
            }
        });

    }



    public void subirArchivoMiCarpeta(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.subirArchivoMiCarpeta(
                MainActivity.usuareo,
                nombreArchivo.getText().toString(),


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
                        if (output.equals("ARCHIVO NO SUBIDO")){
                            Toast.makeText(subirArchivoCarpeta.this,"NO SE SUBIO EL ARCHIVO",Toast.LENGTH_LONG).show();
                        } else {
                            Intent perri = new Intent(subirArchivoCarpeta.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(subirArchivoCarpeta.this,"ARCHIVO SUBIDO A MI CARPETA EXITOSAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(subirArchivoCarpeta.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }





    public void subirArchivoACarpeta(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.subirArchivoACarpeta(
                MainActivity.usuareo,
                nombreCarpeta.getText().toString(),
                nombreArchivo.getText().toString(),


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
                        if (output.equals("ARCHIVO NO SUBIDO")){
                            Toast.makeText(subirArchivoCarpeta.this,"NO SE SUBIO EL ARCHIVO",Toast.LENGTH_LONG).show();
                        } else {
                            Intent perri = new Intent(subirArchivoCarpeta.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(subirArchivoCarpeta.this,"ARCHIVO SUBIDO A DICHA CARPETA EXITOSAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(subirArchivoCarpeta.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }










}
