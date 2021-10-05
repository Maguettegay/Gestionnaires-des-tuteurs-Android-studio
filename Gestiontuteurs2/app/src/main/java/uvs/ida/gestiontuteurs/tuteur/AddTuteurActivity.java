package uvs.ida.gestiontuteurs.tuteur;

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

import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.cours.AddCoursActivity;
import uvs.ida.gestiontuteurs.cours.CoursActivity;
import uvs.ida.gestiontuteurs.database.DBHelper;

public class AddTuteurActivity extends AppCompatActivity {

    EditText name;
    Button btnInsert, update, delete, view;
    DBHelper DB;
    String libellesFilieres [];
    String libellesCours [];
    String namesChefs [];
    Spinner spinnerFiliere;
    Spinner spinnerChef;
    Spinner spinnerCours;
    String filiereSelected;
    String chefSelected;
    String coursSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tuteur);
        setTitle("Ajout tuteur");

        name = findViewById(R.id.name);
        btnInsert = findViewById(R.id.btnInsert);
        DB = new DBHelper(this);

        //Liste déroulant des Filiéres
        spinnerFiliere = findViewById(R.id.spinner_filiere);
        Cursor filiere = DB.getFilieresForSpinner();
        libellesFilieres = new String[filiere.getCount()];
        filiere.moveToFirst();

        for (int i=0; i<libellesFilieres.length;i++){
            libellesFilieres[i]=filiere.getString(1);
            filiere.moveToNext();
        }
        ArrayAdapter<String> listFilieres = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,libellesFilieres);
        spinnerFiliere.setAdapter(listFilieres);


        //Liste déroulant des Cours
        spinnerCours = findViewById(R.id.spinner_cours);
        Cursor cours = DB.getCoursForSpinner();
        libellesCours = new String[cours.getCount()];
        cours.moveToFirst();

        for (int i=0; i<libellesCours.length;i++){
            libellesCours[i]=cours.getString(1);
            cours.moveToNext();
        }
        ArrayAdapter<String> listCours = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,libellesCours);
        spinnerCours.setAdapter(listCours);

        //Liste déroulant des chefs de filières
        spinnerChef = findViewById(R.id.spinner_chef_filiere);
        Cursor chefs = DB.getChefForSpinner();
        namesChefs = new String[chefs.getCount()];
        chefs.moveToFirst();

        for (int i=0; i<namesChefs.length;i++){
            namesChefs[i]=chefs.getString(1);
            chefs.moveToNext();
        }
        ArrayAdapter<String> listChefs = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,namesChefs);
        spinnerChef.setAdapter(listChefs);

        spinnerFiliere.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                filiereSelected = spinnerFiliere.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCours.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                coursSelected = spinnerCours.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerChef.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                chefSelected = spinnerChef.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameTXT = name.getText().toString();
                Boolean checkinsertdata = DB.insertTuteur(nameTXT,filiereSelected,chefSelected,coursSelected);

                if(checkinsertdata==true){
                    Toast.makeText(AddTuteurActivity.this, "Tuteur enregistré", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddTuteurActivity.this, TuteurActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddTuteurActivity.this, "Tuteur non enregistré", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}