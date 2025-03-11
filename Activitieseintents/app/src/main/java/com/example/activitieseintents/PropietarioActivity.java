package com.example.activitieseintents;

import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class PropietarioActivity extends AppCompatActivity {
    private TextView historialMensajes;
    private static final String EXTRA_MENSAJE = "mensaje";
    private static final String EXTRA_HISTORIAL = "historial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_propietario);
        historialMensajes = findViewById(R.id.historialMensajes);

        // Recuperar mensajes del cuidador y el historial
        Intent intent = getIntent();
        if (intent != null) {
            // Si hay un mensaje del cuidador, agregarlo al historial
            if (intent.hasExtra(EXTRA_MENSAJE)) {
                String mensajeCuidador = intent.getStringExtra(EXTRA_MENSAJE);
                historialMensajes.append("\nCuidador: " + mensajeCuidador);
            }
            // Si hay un historial, establecerlo en el TextView
            if (intent.hasExtra(EXTRA_HISTORIAL)) {
                String historial = intent.getStringExtra(EXTRA_HISTORIAL);
                historialMensajes.setText(historial);
            }
        }
    }

    public void enviarMensaje(View view) {
        EditText inputMensaje = findViewById(R.id.inputMensaje);
        String mensajeTexto = inputMensaje.getText().toString();

        // Actualizar el historial de mensajes con el mensaje del propietario
        historialMensajes.append("\nPropietario: " + mensajeTexto);

        // Crear un Intent para enviar el mensaje y el historial al cuidador
        Intent intent = new Intent(this, CuidadorActivity.class);
        intent.putExtra(EXTRA_MENSAJE, mensajeTexto);
        intent.putExtra(EXTRA_HISTORIAL, historialMensajes.getText().toString());

        // Iniciar la actividad del cuidador
        startActivity(intent);
    }
}
