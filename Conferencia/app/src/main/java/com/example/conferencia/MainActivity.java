package com.example.conferencia;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "datos_conferencia.txt";
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener referencias a los elementos de la interfaz
        EditText edtNombres = findViewById(R.id.edtNombres);
        EditText edtApellidos = findViewById(R.id.edtApellidos);
        EditText edtEmail = findViewById(R.id.edtEmail);
        EditText edtNumero = findViewById(R.id.edtNumero);
        EditText edtGrupoSanguineo = findViewById(R.id.edtGrupoSanguineo);
        EditText edtOcupacion = findViewById(R.id.edtOcupacion);
        EditText edtIE = findViewById(R.id.edtIE);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        Button btnLeer = findViewById(R.id.btnLeer);

        // Configurar el botón para registrar datos
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String datos = edtNombres.getText().toString() + "\n" +
                        edtApellidos.getText().toString() + "\n" +
                        edtEmail.getText().toString() + "\n" +
                        edtNumero.getText().toString() + "\n" +
                        edtGrupoSanguineo.getText().toString() + "\n" +
                        edtOcupacion.getText().toString() + "\n" +
                        edtIE.getText().toString();

                try (FileOutputStream fos = openFileOutput(FILE_NAME, MODE_PRIVATE)) {
                    fos.write(datos.getBytes());
                    Toast.makeText(MainActivity.this, "Datos registrados", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Error al guardar los datos", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error al guardar los datos", e);
                }
            }
        });

        // Configurar el botón para leer datos
        btnLeer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try (FileInputStream fis = openFileInput(FILE_NAME)) {
                    byte[] buffer = new byte[fis.available()];
                    fis.read(buffer);
                    String datos = new String(buffer);
                    Log.d(TAG, "Datos leídos: \n" + datos);
                } catch (IOException e) {
                    Toast.makeText(MainActivity.this, "Error al leer los datos", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, "Error al leer los datos", e);
                }
            }
        });
    }
}
