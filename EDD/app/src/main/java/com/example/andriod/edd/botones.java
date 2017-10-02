package com.example.andriod.edd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class botones extends AppCompatActivity {

    private Button crearCarpeta;
    private Button subirArchivoCarpeta;
    private Button descargarArchivo;
    private Button subirArchivoNube;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botones);

        crearCarpeta = (Button) findViewById(R.id.button4);
        subirArchivoCarpeta = (Button)findViewById(R.id.button5);
        descargarArchivo = (Button)findViewById(R.id.button6);
        subirArchivoNube = (Button)findViewById(R.id.button7);

        crearCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perri = new Intent(botones.this,crearCarpetas.class);
                startActivity(perri);
            }
        });

        subirArchivoCarpeta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perri = new Intent(botones.this,subirArchivoCarpeta.class);
                startActivity(perri);
            }
        });

        descargarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perri = new Intent(botones.this,descargarArchivo.class);
                startActivity(perri);
            }
        });

        subirArchivoNube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perri = new Intent(botones.this,subirArchivoNube.class);
                startActivity(perri);
            }
        });
    }
}
