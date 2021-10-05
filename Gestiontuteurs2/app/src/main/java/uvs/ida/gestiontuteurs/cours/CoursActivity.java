package uvs.ida.gestiontuteurs.cours;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;
import uvs.ida.gestiontuteurs.filiere.AddFiliereActivity;
import uvs.ida.gestiontuteurs.filiere.FiliereActivity;

public class CoursActivity extends AppCompatActivity {

    FloatingActionButton add_cours;
    DBHelper DB;
    ListView lvCours;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        setTitle("Liste des cours");

        DB = new DBHelper(this);

        add_cours = findViewById(R.id.fab_add);
        lvCours = findViewById(R.id.list_cours);
        final SimpleCursorAdapter simpleCursorAdapter = DB.getCours();

        if (simpleCursorAdapter != null){
            lvCours.setAdapter(simpleCursorAdapter);
        }

        lvCours.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String libelle = cursor.getString(1);
                Toast.makeText(CoursActivity.this,libelle,Toast.LENGTH_LONG).show();
            }
        });

        add_cours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CoursActivity.this, AddCoursActivity.class);
                startActivity(intent);
            }
        });



    }
}