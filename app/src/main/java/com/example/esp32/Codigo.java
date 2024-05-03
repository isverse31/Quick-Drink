package com.example.esp32;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Codigo extends AppCompatActivity {

    private EditText editTextCodigo;
    private Button btnVerificarCodigo;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_codigo);

        editTextCodigo = findViewById(R.id.editTextCodigo);
        btnVerificarCodigo = findViewById(R.id.btnVerificarCodigo);
        databaseReference = FirebaseDatabase.getInstance().getReference("pedidos").child("codigos");

        btnVerificarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String codigo = editTextCodigo.getText().toString().trim();

                if (!codigo.isEmpty()) {
                    databaseReference.child(codigo).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // El código existe en Firebase
                                // Guardar el código en las preferencias compartidas
                                guardarCodigoInicioSesion(codigo);

                                // Imprimir el valor de las preferencias compartidas para verificar que se ha guardado correctamente
                                SharedPreferences sharedPref = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
                                String codigoGuardado = sharedPref.getString("codigoInicio", "");
                                Log.d("CodigoGuardado", "Código guardado en preferencias compartidas: " + codigoGuardado);

                                // Pasar a la siguiente interfaz
                                Intent intent = new Intent(Codigo.this, MainActivity.class);
                                startActivity(intent);
                            } else {
                                // El código no existe en Firebase
                                editTextCodigo.setError("El código no es válido");
                                editTextCodigo.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Manejar errores de Firebase
                        }
                    });
                } else {
                    editTextCodigo.setError("Por favor, ingrese un código");
                    editTextCodigo.requestFocus();
                }
            }
        });
    }

    // Método para guardar el código de inicio de sesión en las preferencias compartidas
    private void guardarCodigoInicioSesion(String codigoInicio) {
        SharedPreferences sharedPref = getSharedPreferences("APP_PREFS", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("codigoInicio", codigoInicio);
        editor.apply();
    }

}
