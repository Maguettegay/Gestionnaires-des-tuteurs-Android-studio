package uvs.ida.gestiontuteurs.chef_filiere;

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

public class AddChefFiliereActivity extends AppCompatActivity {

    EditText name, filiere ;
    Button btnInsert, update, delete, view;
    DBHelper DB;
    String libelles [];
    Spinner spinnerFiliere;
    String filiereSelected;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_chef_filiere);
        setTitle("Ajout Chef de filiére");

        name = findViewById(R.id.name);
        //filiere = findViewById(R.id.filiere);
        btnInsert = findViewById(R.id.btnInsert);
        DB = new DBHelper(this);

        spinnerFiliere = findViewById(R.id.spinner_filiere_chef);
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
                String nameTXT = name.getText().toString();
                Boolean checkinsertdata = DB.insertChef(nameTXT,filiereSelected);

                if(checkinsertdata==true){
                    Toast.makeText(AddChefFiliereActivity.this, "Chef enregistré", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(AddChefFiliereActivity.this, ChefFiliereActivity.class);
                    startActivity(intent);
                }
                else{
                    Toast.makeText(AddChefFiliereActivity.this, "Chef non enregistré", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}