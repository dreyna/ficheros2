package com.example.ficheros;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Fiechero1 extends AppCompatActivity {
    private Button btnEscribirFichero = null;
    private Button btnLeerFichero = null;
    private Button btnLeerRaw = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiechero1);
        btnEscribirFichero = (Button)findViewById(R.id.BtnEscribirFichero);
        btnLeerFichero = (Button)findViewById(R.id.BtnLeerFichero);
        btnLeerRaw = (Button)findViewById(R.id.BtnLeerRaw);


        btnEscribirFichero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {   showAddItemDialog(Fiechero1.this);  }
        });

        btnLeerFichero.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                try
                {
                    BufferedReader fin = new BufferedReader(new InputStreamReader(openFileInput("ejemplo2.txt")));
                    String texto = fin.readLine();
                    fin.close();
                    Toast.makeText(getApplicationContext(),"Archvo Leido...!: "+texto,Toast.LENGTH_LONG).show();
                    //Log.i("Ficheros", "Texto: " + texto);
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde memoria interna");
                }
            }
        });

        btnLeerRaw.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0)
            {
                String linea = "";

                try
                {
                    InputStream fraw = getResources().openRawResource(R.raw.ejemplo);
                    BufferedReader brin = new BufferedReader(new InputStreamReader(fraw));
                    linea = brin.readLine();
                    fraw.close();
                    Toast.makeText(getApplicationContext(),"Archvo Leido...!: "+linea,Toast.LENGTH_LONG).show();
                }
                catch (Exception ex)
                {
                    Log.e("Ficheros", "Error al leer fichero desde recurso raw");;
                }
            }
        });
    }
    private void showAddItemDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Agregar Texto")
                .setMessage("Ingresar Texto...")
                .setView(taskEditText)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String texto= String.valueOf(taskEditText.getText());
                        //Toast.makeText(getApplicationContext(),""+texto,Toast.LENGTH_LONG).show();
                        try
                        {
                            OutputStreamWriter fout = new OutputStreamWriter(openFileOutput("ejemplo2.txt", Context.MODE_PRIVATE));
                            Log.v("dsd",texto);
                            fout.write("Texto de prueba."+texto);
                            fout.close();

                            Log.i("Ficheros", "Fichero creado!");
                        }
                        catch (Exception ex)
                        {
                            Log.e("Ficheros", "Error al escribir fichero a memoria interna");
                        }

                    }
                })
                .setNegativeButton("Cancelar", null)
                .create();
        dialog.show();
    }
}
