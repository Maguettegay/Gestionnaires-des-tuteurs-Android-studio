package uvs.ida.gestiontuteurs.chef_filiere;

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
import uvs.ida.gestiontuteurs.cours.AddCoursActivity;
import uvs.ida.gestiontuteurs.cours.CoursActivity;
import uvs.ida.gestiontuteurs.database.DBHelper;

public class ChefFiliereActivity extends AppCompatActivity {

    FloatingActionButton add_chef;
    DBHelper DB;
    ListView lvChef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours);
        setTitle("Liste Chef de filiére");
        DB = new DBHelper(this);

        add_chef = findViewById(R.id.fab_add);
        lvChef = findViewById(R.id.list_cours);
        final SimpleCursorAdapter simpleCursorAdapter = DB.getChefs();

        if (simpleCursorAdapter != null){
            lvChef.setAdapter(simpleCursorAdapter);
        }

        lvChef.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String libelle = cursor.getString(1);
                Toast.makeText(ChefFiliereActivity.this,libelle,Toast.LENGTH_LONG).show();
            }
        });

        add_chef.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChefFiliereActivity.this, AddChefFiliereActivity.class);
                startActivity(intent);
            }
        });



    }
}