package com.example.esp32;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class MainActivity extends AppCompatActivity{

    private DatabaseReference databaseReference;
    private FirebaseAuth rAuth;
    private StorageReference storageReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rAuth = FirebaseAuth.getInstance();
        storageReference = FirebaseStorage.getInstance().getReference();
        FirebaseUser user = rAuth.getCurrentUser();

        databaseReference = FirebaseDatabase.getInstance().getReference("pedidos").child("codigos").child("56135");

        CardView cardView1 = findViewById(R.id.cardView1);
        CardView cardView2 = findViewById(R.id.cardView2);
        CardView cardView3 = findViewById(R.id.cardView3);
        CardView cardView4 = findViewById(R.id.cardView4);


        cardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivarBombaTask activarBombaTask1 = new ActivarBombaTask();
                activarBombaTask1.execute("1"); // Activa la bomba 1
                String bebida = "Wiski";
                String fecha = obtenerFechaActual();
                guardarPedidoEnFirebase(bebida, fecha);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ActivarBombaTask activarBombaTask2 = new ActivarBombaTask();
                        activarBombaTask2.execute("2");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActivarBombaTask activarBombaTask3 = new ActivarBombaTask();
                                activarBombaTask3.execute("3"); // Activa la bomba 3

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ActivarBombaTask activarBombaTask4 = new ActivarBombaTask();
                                        activarBombaTask4.execute("4"); // Activa la bomba 4
                                    }
                                }, 3000);
                            }
                        }, 3000);
                    }
                }, 3000);
            }
        });

        cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivarBombaTask activarBombaTask3 = new ActivarBombaTask();
                activarBombaTask3.execute("3");
                String bebida = "Cuba";
                String fecha = obtenerFechaActual();
                guardarPedidoEnFirebase(bebida, fecha);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ActivarBombaTask activarBombaTask2 = new ActivarBombaTask();
                        activarBombaTask2.execute("2");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActivarBombaTask activarBombaTask4 = new ActivarBombaTask();
                                activarBombaTask4.execute("4");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ActivarBombaTask activarBombaTask1 = new ActivarBombaTask();
                                        activarBombaTask1.execute("1");
                                    }
                                }, 3000);
                            }
                        }, 3000); //
                    }
                }, 3000);
            }
        });

        cardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivarBombaTask activarBombaTask2 = new ActivarBombaTask();
                activarBombaTask2.execute("2");
                String bebida = "Pitufo";
                String fecha = obtenerFechaActual();
                guardarPedidoEnFirebase(bebida, fecha);

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ActivarBombaTask activarBombaTask4 = new ActivarBombaTask();
                        activarBombaTask4.execute("4");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActivarBombaTask activarBombaTask3 = new ActivarBombaTask();
                                activarBombaTask3.execute("3");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ActivarBombaTask activarBombaTask1 = new ActivarBombaTask();
                                        activarBombaTask1.execute("1");
                                    }
                                }, 3000);
                            }
                        }, 3000);
                    }
                }, 3000);
            }
        });

        cardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivarBombaTask activarBombaTask1 = new ActivarBombaTask();
                activarBombaTask1.execute("1"); // Activa la bomba 1
                String bebida = "Arizona";
                String fecha = obtenerFechaActual();
                guardarPedidoEnFirebase(bebida, fecha);


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ActivarBombaTask activarBombaTask3 = new ActivarBombaTask();
                        activarBombaTask3.execute("3");

                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                ActivarBombaTask activarBombaTask2 = new ActivarBombaTask();
                                activarBombaTask2.execute("2");

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        ActivarBombaTask activarBombaTask4 = new ActivarBombaTask();
                                        activarBombaTask4.execute("4");
                                    }
                                }, 3000);
                            }
                        }, 3000);
                    }
                }, 3000);
            }
        });

    }

    private String obtenerFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return sdf.format(new Date());
    }

    private void guardarPedidoEnFirebase(String bebida, String fecha) {
        String idPedido = databaseReference.push().getKey();
        Map<String, Object> pedidoMap = new HashMap<>();
        pedidoMap.put("bebida", bebida);
        pedidoMap.put("fecha", fecha);
        databaseReference.child(idPedido).setValue(pedidoMap)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(MainActivity.this, "Pedido registrado correctamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Error al registrar el pedido", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private static class ActivarBombaTask extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... params) {
            String resultado = "";
            String urlServidor = "http://192.168.1.76/activarBomba" + params[0]; // URL del servidor para activar la bomba especificada

            try {
                URL url = new URL(urlServidor);
                HttpURLConnection conexion = (HttpURLConnection) url.openConnection();

                conexion.setRequestMethod("GET"); // Método de la solicitud (en este caso, GET)

                int respuestaCodigo = conexion.getResponseCode();

                if (respuestaCodigo == HttpURLConnection.HTTP_OK) {
                    resultado = "Solicitud para activar bomba enviada correctamente";
                } else {
                    resultado = "Error al enviar la solicitud para activar bomba: " + respuestaCodigo;
                }

                conexion.disconnect();

            } catch (IOException e) {
                e.printStackTrace();
                resultado = "Excepción al enviar la solicitud para activar bomba: " + e.getMessage();
            }

            return resultado;
        }

        @Override
        protected void onPostExecute(String result) {
            // Manejar el resultado de la solicitud (puede ser una confirmación o un mensaje de error)
        }
    }
}