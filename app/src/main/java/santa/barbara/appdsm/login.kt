package santa.barbara.appdsm

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider

class login : AppCompatActivity() {

    private val InicioSesionGoogle = 100
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val txtCorreoLogin = findViewById<EditText>(R.id.txtCorreoLogin)
        val txtPasswordLogin = findViewById<EditText>(R.id.txtPasswordLogin)
        val btnIngresar = findViewById<Button>(R.id.btnIngresar)
        val btnInicioSesionGoogle = findViewById<Button>(R.id.btnInicioSesionGoogle)
        val btnRegistrar = findViewById<Button>(R.id.btnRegistrarme)
        val scrollView2 = findViewById<ScrollView>(R.id.scrollView2)

        btnIngresar.setOnClickListener {
            val correo = txtCorreoLogin.text.toString().trim()
            val password = txtPasswordLogin.text.toString()
            if (correo.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Debe llenar todos los campos", Toast.LENGTH_LONG).show()
            } else {
                FirebaseAuth.getInstance().signInWithEmailAndPassword(
                    correo,
                    password
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val activity_menu = Intent(this, MainActivity::class.java)
                        startActivity(activity_menu)
                    } else {
                        Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()

                    }
                }
            }
        }


        btnInicioSesionGoogle.setOnClickListener {
            val configuracionGoogle =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(getString(R.string.default_web_client_id)).requestEmail()
                    .build()

            val ClienteGoogle = GoogleSignIn.getClient(this, configuracionGoogle)

            startActivityForResult(ClienteGoogle.signInIntent, InicioSesionGoogle)
        }

        btnRegistrar.setOnClickListener {
            val activity_registrar = Intent(this, registrarse::class.java)
            startActivity(activity_registrar)
        }

        //Desplazamiento del scroll para que el teclado no oculte el cuadro de texto
        txtPasswordLogin.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollView2.post {
                    scrollView2.scrollTo(0, btnInicioSesionGoogle.bottom)
                }
            }
        }
        txtCorreoLogin.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                scrollView2.post {
                    scrollView2.scrollTo(0, btnInicioSesionGoogle.bottom)
                }
            }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == InicioSesionGoogle) {
            val tarea = GoogleSignIn.getSignedInAccountFromIntent(data)
            try {
                val cuenta = tarea.getResult(ApiException::class.java)
                if (cuenta != null) {
                    val credenciales = GoogleAuthProvider.getCredential(cuenta.idToken, null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales)
                        .addOnCompleteListener {
                            if (it.isSuccessful) {
                                val activity_menu = Intent(this, MainActivity::class.java)
                                startActivity(activity_menu)
                            } else {
                                Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG)
                                    .show()
                            }
                        }
                }
            } catch (e: ApiException) {
                Toast.makeText(this, "Error al iniciar sesion", Toast.LENGTH_LONG).show()

            }
        }
    }


}