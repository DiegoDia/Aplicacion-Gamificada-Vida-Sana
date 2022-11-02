package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FormularioReciclaje extends AppCompatActivity {

    EditText edtelefono, eddireccion, edreferencia, edmaterial;
    Button btnAgregar,btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_reciclaje);

        edtelefono=(EditText)findViewById(R.id.telefono);
        eddireccion=(EditText)findViewById(R.id.Direccion);
        edreferencia=(EditText)findViewById(R.id.Referencia);
        edmaterial=(EditText)findViewById(R.id.Material);
        btnAgregar=(Button) findViewById(R.id.btnAgregar);
        btnAtras=(Button) findViewById(R.id.btnAtras);

        final FormularioBD formularioBD=new FormularioBD(getApplicationContext());

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                formularioBD.agregarFomulario(edtelefono.getText().toString(),eddireccion.getText().toString(),edreferencia.getText().toString(),edmaterial.getText().toString());
                Toast.makeText(getApplicationContext(),"Se agrego correctamente",Toast.LENGTH_SHORT).show();
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });


    }
}