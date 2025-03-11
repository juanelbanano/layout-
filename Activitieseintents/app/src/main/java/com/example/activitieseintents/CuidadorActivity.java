package com.example.activitieseintents;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class CuidadorActivity extends AppCompatActivity {
    private TextView historialMensajes;
    private static final String EXTRA_MENSAJE = "mensaje";
    private static final String EXTRA_HISTORIAL = "historial";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuidador);
        historialMensajes = findViewById(R.id.historialMensajes);

        // Recuperar mensajes del propietario y el historial
        Intent intent = getIntent();
        if (intent != null) {
            // Si hay un mensaje del propietario, agregarlo al historial
            if (intent.hasExtra(EXTRA_MENSAJE)) {
                String mensajePropietario = intent.getStringExtra(EXTRA_MENSAJE);
                historialMensajes.append("\nPropietario: " + mensajePropietario);
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

        // Actualizar el historial de mensajes con el mensaje del cuidador
        historialMensajes.append("\nCuidador: " + mensajeTexto);

        // Crear un Intent para enviar el mensaje y el historial al propietario
        Intent intent = new Intent(this, PropietarioActivity.class);
        intent.putExtra(EXTRA_MENSAJE, mensajeTexto);
        intent.putExtra(EXTRA_HISTORIAL, historialMensajes.getText().toString());

        // Iniciar la actividad del propietario
        startActivity(intent);
    }
}