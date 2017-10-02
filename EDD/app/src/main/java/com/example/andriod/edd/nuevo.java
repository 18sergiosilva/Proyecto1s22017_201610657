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

public class nuevo extends AppCompatActivity {

    private EditText nuevoUsuario;
    private EditText nuevoPass;
    private Button crear;
    public String nuser;
    public String npass;


    public static final String ROOT_URL = "http://192.168.1.7:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);

        nuevoUsuario = (EditText)findViewById(R.id.editText2);
        nuevoPass = (EditText)findViewById(R.id.editText4);
        crear = (Button) findViewById(R.id.button3);

        crear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //verifDatos(nuevoUsuario.getText().toString(),nuevoPass.getText().toString());
                insertarUser();
            }
        });


    }

    public void verifDatos(String us, String pas){
        nuser = us;
        npass = pas;
        Toast.makeText(getApplicationContext(),"" + nuser + "/" + npass , Toast.LENGTH_LONG).show();
        Intent perri = new Intent(nuevo.this,MainActivity.class);
        startActivity(perri);
    }




    public void insertarUser(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.insertarUser(
                nuevoUsuario.getText().toString(),
                nuevoPass.getText().toString(),

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
                        if (output.equals("FalseUser")){
                            Toast.makeText(nuevo.this,"USUARIO YA EXISTE",Toast.LENGTH_LONG).show();
                        } else if (output.equals("FalsePass")) {
                            Toast.makeText(nuevo.this,"PASSWORD NO VALIDO",Toast.LENGTH_LONG).show();
                        } else {
                            crearCarpetaAndroidInicial();
                            Intent perri = new Intent(nuevo.this,MainActivity.class);
                            startActivity(perri);
                            Toast.makeText(nuevo.this,"CUENTA CREADA CON EXITO",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(nuevo.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }




    public void crearCarpetaAndroidInicial(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.crearCarpetaAndroidInicial(
                nuevoUsuario.getText().toString(),

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
                        if (output.equals("CARPETA NO CREADA")){
                            Toast.makeText(nuevo.this,"NO SE CREO LA CARPETA",Toast.LENGTH_LONG).show();
                        } else {
                            //Intent perri = new Intent(nuevo.this,botones.class);
                            //startActivity(perri);
                            Toast.makeText(nuevo.this,"CARPETA CREADA EXITOSAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(nuevo.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }




}
