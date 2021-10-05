package uvs.ida.gestiontuteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AlertDialog;
import android.database.Cursor;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uvs.ida.gestiontuteurs.database.DBHelper;
import uvs.ida.gestiontuteurs.filiere.AddFiliereActivity;
import uvs.ida.gestiontuteurs.filiere.FiliereActivity;
import uvs.ida.gestiontuteurs.security.LoginActivity;

public class MainActivity extends AppCompatActivity {
    EditText name, email, password;
    Button insert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Utilisateurs");

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        insert = findViewById(R.id.btnInsert);
        /*update = findViewById(R.id.btnUpdate);
        delete = findViewById(R.id.btnDelete);
        view = findViewById(R.id.btnView);*/
        DB = new DBHelper(this);
        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkinsertdata = DB.insertUser(nameTXT, emailTXT, passwordTXT);
                Boolean check = DB.teste(emailTXT,passwordTXT);
                if(checkinsertdata==true){
                    Toast.makeText(MainActivity.this, "Utilisateur enregistré", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(MainActivity.this, "Utilisateur non enregistré", Toast.LENGTH_SHORT).show();
                }
            }
        });
        /*update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                String emailTXT = email.getText().toString();
                String passwordTXT = password.getText().toString();

                Boolean checkupdatedata = DB.updateUser(nameTXT, emailTXT, passwordTXT);
                if(checkupdatedata==true){
                    Toast.makeText(getBaseContext(), "Données mise à jour", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Données non mise à jour", Toast.LENGTH_SHORT).show();
                }
            }
        });
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String emailTXT = email.getText().toString();
                Boolean checkudeletedata = DB.deleteUser(emailTXT);
                if(checkudeletedata==true){
                    Toast.makeText(MainActivity.this, "Utilisateur supprimé", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(MainActivity.this, "Utilisateur non supprimé", Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getAllUser();
                if(res.getCount()==0){
                    Toast.makeText(MainActivity.this, "Pas d'utilisateur", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });*/
    }
}