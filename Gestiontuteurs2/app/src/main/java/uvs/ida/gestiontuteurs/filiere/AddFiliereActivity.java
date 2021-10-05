package uvs.ida.gestiontuteurs.filiere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import uvs.ida.gestiontuteurs.HomeActivity;
import uvs.ida.gestiontuteurs.MainActivity;
import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;

public class AddFiliereActivity extends AppCompatActivity {

    EditText libelle, email, password;
    Button btnInsert, update, delete, view;
    DBHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_filiere);
        setTitle("Ajout de filiére");

        libelle = findViewById(R.id.libelle);
        btnInsert = findViewById(R.id.btnInsert);
        DB = new DBHelper(this);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String libelleTXT = libelle.getText().toString();

                Boolean checkinsertdata = DB.insertFiliere(libelleTXT);

                if(checkinsertdata==true){
                    Toast.makeText(AddFiliereActivity.this, "Filière enregistré", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddFiliereActivity.this, FiliereActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddFiliereActivity.this, "Filière non enregistré", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}