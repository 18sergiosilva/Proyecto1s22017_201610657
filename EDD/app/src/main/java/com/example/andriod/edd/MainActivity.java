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

public class MainActivity extends AppCompatActivity {

    private EditText user;
    private EditText password;
    private Button login;
    private Button createAccount;
    public static String usuareo="";

    public static final String ROOT_URL = "http://192.168.1.7:5000/";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = (EditText)findViewById(R.id.editText3);
        password = (EditText)findViewById(R.id.editText);
        login = (Button) findViewById(R.id.button);
        createAccount = (Button) findViewById(R.id.button2);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //validar(user.getText().toString(),password.getText().toString());
                validarUser();
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCuenta();
            }
        });

    }

    public void validar(String user, String pass){
        if ((user.equals("perrito")) && (pass.equals("gatito"))){
            Intent perri = new Intent(MainActivity.this,drive.class);
            startActivity(perri);
        } else {

            Toast.makeText(getApplicationContext(),"USER/PASSWORD INCORRECT", Toast.LENGTH_LONG).show();
        }
    }

    public void crearCuenta(){
        Intent gati = new Intent(MainActivity.this, nuevo.class);
        startActivity(gati);
    }

    public void validarUser(){
        usuareo = user.getText().toString();
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.validarUser(
            user.getText().toString(),
            password.getText().toString(),

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
                    if (output.equals("invalido")){
                        Toast.makeText(MainActivity.this,"KGADA",Toast.LENGTH_LONG).show();
                    } else {
                        //crearCarpetaAndroid();
                        Intent perri = new Intent(MainActivity.this,botones.class);
                        startActivity(perri);
                        Toast.makeText(MainActivity.this,"BIENVENIDO",Toast.LENGTH_LONG).show();
                    }

                }

                @Override
                public void failure(RetrofitError error){
                    Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
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
                        if (output.equals("CARPETA NO CREADA")){
                            Toast.makeText(MainActivity.this,"NO SE CREO LA CARPETA",Toast.LENGTH_LONG).show();
                        } else {
                            Intent perri = new Intent(MainActivity.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(MainActivity.this,"CARPETA CREADA EXITOSAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(MainActivity.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }



}
