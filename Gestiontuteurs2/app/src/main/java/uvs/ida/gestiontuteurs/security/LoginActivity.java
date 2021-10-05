package uvs.ida.gestiontuteurs.security;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import uvs.ida.gestiontuteurs.HomeActivity;
import uvs.ida.gestiontuteurs.MainActivity;
import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;

public class LoginActivity extends AppCompatActivity {
    EditText email, password;
    TextView inscription;
    Button btnlogin;
    Boolean checkuserpass1;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Connexion");

        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        inscription = (TextView) findViewById(R.id.inscription);
        DB = new DBHelper(this);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = email.getText().toString();
                String pass = password.getText().toString();
                checkuserpass1 = DB.teste(user,pass);
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Tous les champs sont requis",Toast.LENGTH_SHORT).show();
                else {
                    Boolean check = DB.login(user,pass);
                    if(check==true){
                        Toast.makeText(LoginActivity.this, "Connexion réussie",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Toast.makeText(LoginActivity.this,"Donnée connexion invalid",Toast.LENGTH_SHORT).show();
                    }
                }
                }


        });

        inscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }
}