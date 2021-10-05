package uvs.ida.gestiontuteurs.filiere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.HashMap;
import java.util.List;

import uvs.ida.gestiontuteurs.HomeActivity;
import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;
import uvs.ida.gestiontuteurs.tuteur.TuteurActivity;

public class FiliereActivity extends AppCompatActivity {

    FloatingActionButton add_filiere;
    DBHelper DB;
    ListView lvFIliere;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filiere);
        setTitle("Liste des filiére");

        DB = new DBHelper(this);

        add_filiere = findViewById(R.id.fab_add);
        lvFIliere = findViewById(R.id.list_filiere);
        final SimpleCursorAdapter simpleCursorAdapter = DB.getFilieres();

        if (simpleCursorAdapter != null){
            lvFIliere.setAdapter(simpleCursorAdapter);
        }

        lvFIliere.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String libelle = cursor.getString(1);
                Toast.makeText(FiliereActivity.this,libelle,Toast.LENGTH_LONG).show();
            }
        });

        add_filiere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FiliereActivity.this, AddFiliereActivity.class);
                startActivity(intent);
            }
        });



    }
}