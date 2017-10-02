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

public class crearCarpetas extends AppCompatActivity {

    private EditText directorio;
    private Button crearCarpeta;

    public static final String ROOT_URL = "http://192.168.1.7:5000/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear_carpetas);

        directorio = (EditText)findViewById(R.id.editText5);
        crearCarpeta = (Button)findViewById(R.id.button8);

        crearCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                crearCarpetaAndroid();
            }
        });

    }


    public void crearCarpetaAndroid(){
        RestAdapter adapter  = new RestAdapter.Builder()
                .setEndpoint(ROOT_URL)
                .build();

        RegisterAPI api = adapter.create(RegisterAPI.class);

        api.crearCarpetaAndroid(
                directorio.getText().toString(),
                MainActivity.usuareo,
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
                            Toast.makeText(crearCarpetas.this,"NO SE CREO LA CARPETA",Toast.LENGTH_LONG).show();
                        } else {
                            Intent perri = new Intent(crearCarpetas.this,botones.class);
                            startActivity(perri);
                            Toast.makeText(crearCarpetas.this,"CARPETA CREADA EXITOSAMENTE",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void failure(RetrofitError error){
                        Toast.makeText(crearCarpetas.this,error.toString(),Toast.LENGTH_LONG).show();
                    }

                }

        );

    }
}
