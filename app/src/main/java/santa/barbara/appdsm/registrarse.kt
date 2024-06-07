package santa.barbara.appdsm

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.InputType
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.auth.FirebaseAuth

class registrarse : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_registrarse)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val txtCorreo = findViewById<EditText>(R.id.txtCorreo)
        val txtPassword = findViewById<EditText>(R.id.txtPassword)
        val btnCrearCuenta = findViewById<Button>(R.id.btnCrearCuenta)
        val btnRegresarLogin = findViewById<Button>(R.id.btnRegresarLogin)
        val imgAtrasregistrarse = findViewById<ImageView>(R.id.imgAtrasregistrarse)
        val txtConfirmarPassword = findViewById<EditText>(R.id.txtConfirmarPassword)
        val imgVerPassword = findViewById<ImageView>(R.id.imgVerPassword)
        val imgVerConfirmacionPassword = findViewById<ImageView>(R.id.imgVerConfirmacionPassword)

        btnCrearCuenta.setOnClickListener {
            val correo = txtCorreo.text.toString()
            val password = txtPassword.text.toString()
            val confirmarPassword = txtConfirmarPassword.text.toString()

            if (txtCorreo.text.isEmpty() || txtPassword.text.isEmpty()|| txtConfirmarPassword.text.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show()

            }  else if (password != confirmarPassword) {
                Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show()
            }else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo, password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this, "Usuario registrado", Toast.LENGTH_LONG).show()
                            txtCorreo.setText("")
                            txtPassword.setText("")
                        } else {
                            Toast.makeText(this, "Error al registrar", Toast.LENGTH_LONG).show()
                        }
                    }
            }
        }

        imgVerPassword.setOnClickListener {
            if (txtPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
            }

        imgVerConfirmacionPassword.setOnClickListener {
            if (txtConfirmarPassword.inputType == InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD) {
                txtConfirmarPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD
            } else {
                txtConfirmarPassword.inputType = InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD
            }
        }

        btnRegresarLogin.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        imgAtrasregistrarse.setOnClickListener {
            val intent = Intent(this, login::class.java)
            startActivity(intent)
        }

        //Para cambiar color a la barra de estado
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = ContextCompat.getColor(this, R.color.statusBarColor)
        }
    }
}