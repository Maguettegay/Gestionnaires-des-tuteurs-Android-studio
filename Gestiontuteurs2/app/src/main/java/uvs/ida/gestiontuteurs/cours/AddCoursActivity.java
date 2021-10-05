package uvs.ida.gestiontuteurs.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;
import uvs.ida.gestiontuteurs.filiere.AddFiliereActivity;
import uvs.ida.gestiontuteurs.filiere.FiliereActivity;

public class AddCoursActivity extends AppCompatActivity {

    EditText libelle, filiere ;
    Button btnInsert, update, delete, view;
    DBHelper DB;
    String libelles [];
    Spinner spinnerFiliere;
    String filiereSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_cours);
        setTitle("Ajout de cours");

        libelle = findViewById(R.id.libelle);
        btnInsert = findViewById(R.id.btnInsert);
        DB = new DBHelper(this);

        spinnerFiliere = findViewById(R.id.spinner_filiere);
        Cursor data = DB.getFilieresForSpinner();
        libelles = new String[data.getCount()];
        data.moveToFirst();

        for (int i=0; i<libelles.length;i++){
            libelles[i]=data.getString(1);
            data.moveToNext();
        }
        ArrayAdapter<String> adp = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,libelles);
        spinnerFiliere.setAdapter(adp);

        spinnerFiliere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filiereSelected = spinnerFiliere.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String libelleTXT = libelle.getText().toString();
                Boolean checkinsertdata = DB.insertCours(libelleTXT,filiereSelected);

                if(checkinsertdata==true){
                    Toast.makeText(AddCoursActivity.this, "Cours enregistré", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddCoursActivity.this, CoursActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddCoursActivity.this, "Cours non enregistré", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}