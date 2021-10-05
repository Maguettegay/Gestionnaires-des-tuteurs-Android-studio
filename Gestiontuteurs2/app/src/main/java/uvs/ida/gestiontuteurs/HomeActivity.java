package uvs.ida.gestiontuteurs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import uvs.ida.gestiontuteurs.chef_filiere.ChefFiliereActivity;
import uvs.ida.gestiontuteurs.cours.CoursActivity;
import uvs.ida.gestiontuteurs.database.DBHelper;
import uvs.ida.gestiontuteurs.filiere.FiliereActivity;
import uvs.ida.gestiontuteurs.tuteur.TuteurActivity;

public class HomeActivity extends AppCompatActivity {
    EditText name, email, password;
    Button tuteur, filiere, chef_filiere, cours, user;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        tuteur = findViewById(R.id.tuteur);
        filiere = findViewById(R.id.filiere);
        cours = findViewById(R.id.cours);
        chef_filiere = findViewById(R.id.chef_filiere);
        DB = new DBHelper(this);
        tuteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, TuteurActivity.class);
                startActivity(intent);
            }
        });
        filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, FiliereActivity.class);
                startActivity(intent);

            }
        });
        cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, CoursActivity.class);
                startActivity(intent);
            }
        });

        chef_filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ChefFiliereActivity.class);
                startActivity(intent);
            }
        });
    }
}