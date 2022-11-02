package com.example.login;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.scottyab.aescrypt.AESCrypt;

import java.security.GeneralSecurityException;

public class LoginActivity extends AppCompatActivity {

    EditText username, password;
    Button btnlogin, btnregistro;
    DBHelper DB;


    EditText et_key;
    EditText et_value;
    TextView message;


    private static final String AES="AES";
    private String mensajeAEncriptar= "ESTO ES UNA PRUEBA DE ENCRIPTACION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_key = findViewById(R.id.password1);
        et_value = findViewById(R.id.et_value);
        message = findViewById(R.id.message);


        username = (EditText) findViewById(R.id.username1);
        password = (EditText) findViewById(R.id.password1);
        btnlogin = (Button) findViewById(R.id.btnsignin1);
        btnregistro = (Button) findViewById(R.id.btnregistro);
        DB = new DBHelper(this);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();



                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Por favor ingrese todos los campos", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);

                        if(checkuserpass==true){
                        Toast.makeText(LoginActivity.this, "Inicio de sesión con éxito", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginActivity.this, "Credenciales no válidas", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            public void encrypt(View view) throws GeneralSecurityException {

                String encrpyted = AESCrypt.encrypt(et_key.getText().toString(), et_value.getText().toString());
                ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("lable", encrpyted);
                clipboardManager.setPrimaryClip(clipData);
                et_value.setText("");
                et_key.setText("");
                message.setText(String.format("Tu clave cifrada es (copiada al portapapeles) : %s", encrpyted));

            }
        });

        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });


        /*

        public void decrypt (View view) throws GeneralSecurityException {
            String encrpyted = AESCrypt.decrypt(et_key.getText().toString(), et_value.getText().toString());
            ClipboardManager clipboardManager = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("lable", encrpyted);
            clipboardManager.setPrimaryClip(clipData);
            Toast.makeText(this, "Su mensaje fue copiado en el portapapeles", Toast.LENGTH_SHORT).show();
            et_value.setText("");
            et_key.setText("");
            message.setText(String.format("Tu clave descifrada es (copiada al portapapeles) : %s", encrpyted));

        }



        /*

        try{
            encriptar(mensajeAEncriptar);
        }catch (Exception e){

        }
    }
    /*

    public static String encriptar(String password) throw Exception{
        //Estamdar de escriptacion
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //iniciaizamos el generador de llaves especificando el tamaño
        keyGenerator.init(1250);
        //Instancia de la llave secreta
        SecretKey secretKey = keyGenerator.generateKey();
        byte[] bytesSecreKey = secretKey.getEncoded();
        //Construcion de una llave secreta de tipo AES
        SecretKeySpec secretKeySpec= new SecretKeySpec(bytesSecreKey, AES);
        //Instancia de un tipo AES
        Cipher cipher = Cipher.getInstance(AES);
        //Encriptacion ; inicializacion, con la clave que se genero
        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);
        byte[] password=cipher.doFinal(password.getBytes());

        /*
        //metodo para desencriptar
        //Inicializacion de la clave para desencriptar
        cipher.init(Cipher.DECRYPT_MODE,secretKeySpec);
        byte[] mensajeDesEncriptado = cipher.doFinal(mensajeEncriptado);
        Log.d("TAG", new String(mensajeDesEncriptado));
        return new String(mensajeDesEncriptado); */

    }
}