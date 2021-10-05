package uvs.ida.gestiontuteurs.tuteur;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import uvs.ida.gestiontuteurs.MainActivity;
import uvs.ida.gestiontuteurs.R;
import uvs.ida.gestiontuteurs.database.DBHelper;

public class TuteurActivity extends AppCompatActivity {

    FloatingActionButton add_tuteur;
    DBHelper DB;
    ListView lvTuteurs;
    Button view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuteur);
        setTitle("Liste des tuteurs");

        DB = new DBHelper(this);

        add_tuteur = findViewById(R.id.fab_add);
        lvTuteurs = findViewById(R.id.list_tuteur);
        final SimpleCursorAdapter simpleCursorAdapter = DB.getTuteurs();

        if (simpleCursorAdapter != null){
            lvTuteurs.setAdapter(simpleCursorAdapter);
        }

        lvTuteurs.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cursor cursor = (Cursor) simpleCursorAdapter.getItem(position);
                String name = cursor.getString(1);

                Cursor res = DB.getSingleTuteur(name);
                if(res.getCount()==0){
                    Toast.makeText(TuteurActivity.this, "Pas de cours", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("cours : "+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(TuteurActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Liste des cours");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });

        /*view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = DB.getSingleTuteur(1);
                if(res.getCount()==0){
                    Toast.makeText(TuteurActivity.this, "Pas d'utilisateur", Toast.LENGTH_SHORT).show();
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Email :"+res.getString(2)+"\n");
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(TuteurActivity.this);
                builder.setCancelable(true);
                builder.setTitle("User Entries");
                builder.setMessage(buffer.toString());
                builder.show();
            }
        });*/

        add_tuteur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TuteurActivity.this, AddTuteurActivity.class);
                startActivity(intent);
            }
        });

    }
}