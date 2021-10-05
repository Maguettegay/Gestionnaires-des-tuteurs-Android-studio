package uvs.ida.gestiontuteurs.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.SimpleCursorAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import uvs.ida.gestiontuteurs.R;

public class DBHelper extends SQLiteOpenHelper {
    Context context;
    public DBHelper(Context context) {

        super(context, "gestuteurs.db", null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE user(id_user INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, email TEXT NOT NULL UNIQUE, password TEXT NOT NULL)");
        DB.execSQL("CREATE TABLE tuteur(id_tuteur INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, id_filiere INTEGER, id_chef_filiere INTEGER, id_cours INTEGER)");
        DB.execSQL("CREATE TABLE filiere(id_filiere INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT NOT NULL)");
        DB.execSQL("CREATE TABLE cours(id_cours INTEGER PRIMARY KEY AUTOINCREMENT, libelle TEXT NOT NULL, id_filiere INTEGER)");
        DB.execSQL("CREATE TABLE chef_filiere(id_chef_filiere INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, id_filiere INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists user");
        DB.execSQL("drop Table if exists tuteur");
        DB.execSQL("drop Table if exists filiere");
        DB.execSQL("drop Table if exists cours");
        DB.execSQL("drop Table if exists chef_filiere");
        onCreate(DB);
    }

    public Boolean teste(String email, String password){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user where email = ? and password = ?", new String[]{email,password});
        if (cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    public Boolean insertUser(String name, String email, String password)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        long result=DB.insert("user", null, contentValues);
        DB.close();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }


    public Boolean updateUser(String name, String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.update("user", contentValues, "email=?", new String[]{email});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }


    public Boolean deleteUser (String email)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user where email = ?", new String[]{email});
        if (cursor.getCount() > 0) {
            long result = DB.delete("user", "email=?", new String[]{email});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Cursor getAllUser ()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user", null);
        return cursor;

    }

    public ArrayList<HashMap<String,String>> getUsers(){
        SQLiteDatabase DB = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM user";
        Cursor cursor = DB.rawQuery(query,null);

        while(cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex("name")));
            user.put("email", cursor.getString(cursor.getColumnIndex("email")));
            userList.add(user);
        }
        return userList;
    }

    public ArrayList<HashMap<String,String>> getUserById(int userId){

        SQLiteDatabase DB = this.getWritableDatabase();
        ArrayList<HashMap<String,String>> userList = new ArrayList<>();
        String query = "SELECT name, email FROM user";
        Cursor cursor = DB.query("user",new String[]{"name","email"},"id_user = ?", new String[]{String.valueOf(userId)},null,null,null,null);

        while(cursor.moveToNext()){
            HashMap<String,String> user = new HashMap<>();
            user.put("name", cursor.getString(cursor.getColumnIndex("name")));
            user.put("email", cursor.getString(cursor.getColumnIndex("email")));
            userList.add(user);
        }
        return userList;

    }

    public Boolean deleteUserById (int userId)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user where id_user = ?", new String[]{String.valueOf(userId)});
        if (cursor.getCount() > 0) {
            long result = DB.delete("user", "id_user=?", new String[]{String.valueOf(userId)});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }

    }

    public Boolean updateUserById(int userId, String name, String email, String password) {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("email", email);
        contentValues.put("password", password);
        Cursor cursor = DB.rawQuery("Select * from user where id_user = ?", new String[]{String.valueOf(userId)});
        if (cursor.getCount() > 0) {
            long result = DB.update("user", contentValues, "id_user=?", new String[]{String.valueOf(userId)});
            DB.close();
            if (result == -1) {
                return false;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }

    public Boolean login(String email, String password){
        //SQLiteDatabase MyDB = this.getWritableDatabase();
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from user where email = ? and password = ?", new String[] {email,password});

        if (cursor.getCount() > 0){
            return  true;
        }
        else{
            return  false;
        }
    }

    //---------------------------Traitement données filiéres------------------------------//

    public Boolean insertFiliere(String libelle)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("libelle", libelle);
        long result=DB.insert("filiere", null, contentValues);
        DB.close();
        if(result==-1){
            return false;
        }else{
            return true;
        }
    }

    public SimpleCursorAdapter getFilieres(){
        SQLiteDatabase DB = this.getWritableDatabase();
        SimpleCursorAdapter libelleAdapter;
        Cursor cursor = DB.rawQuery("Select id_filiere as _id, libelle from filiere", null);

        int count = cursor.getCount();
        String[] fromFieldNames = new String[]{"_id","libelle"};

        if (count > 0){
            int[] viewIds = new int[]{R.id.id_filiere, R.id.libelle};
            libelleAdapter = new SimpleCursorAdapter(
                    context,
                    R.layout.single_item_filiere,
                    cursor,
                    fromFieldNames,
                    viewIds
            );
        }
        else{
            libelleAdapter = null;
        }

        return libelleAdapter;
    }


    //---------------------------Traitement données cours------------------------------//

    public Boolean insertCours(String libelle, String libFiliere)
    {
        if (libelle.equals("") || libFiliere.equals("")){
            return false;
        }
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id_filiere from filiere where libelle = ? LIMIT 1", new String[]{libFiliere});

        cursor.moveToNext();
        int idLibelle = Integer.parseInt(cursor.getString(0));
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("libelle", libelle);
            contentValues.put("id_filiere", idLibelle);
            long result=DB.insert("cours", null, contentValues);
            DB.close();
            if(result==-1){
                return false;
            }else{
                return true;
            }
        } else {
            return false;
        }
    }

    public SimpleCursorAdapter getCours(){
        SQLiteDatabase DB = this.getWritableDatabase();
        SimpleCursorAdapter coursAdapter;
        Cursor cursor = DB.rawQuery("Select id_cours as _id, libelle from cours", null);

        int count = cursor.getCount();
        String[] fromFieldNames = new String[]{"_id","libelle"};

        if (count > 0){
            int[] viewIds = new int[]{R.id.id_cours, R.id.libelle};
            coursAdapter = new SimpleCursorAdapter(
                    context,
                    R.layout.single_item_cours,
                    cursor,
                    fromFieldNames,
                    viewIds
            );
        }
        else{
            coursAdapter = null;
        }

        return coursAdapter;
    }

    public Cursor getFilieresForSpinner(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id_filiere, libelle from filiere", null);
        return cursor;
    }


    //---------------------------Traitement données cours------------------------------//

    public Boolean insertChef(String name, String libFiliere)
    {
        if (name.equals("") || libFiliere.equals("")){
            return false;
        }
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id_filiere from filiere where libelle = ? LIMIT 1", new String[]{libFiliere});

        cursor.moveToNext();
        int idLibelle = Integer.parseInt(cursor.getString(0));
        if (cursor.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("id_filiere", idLibelle);
            long result=DB.insert("chef_filiere", null, contentValues);
            DB.close();
            if(result==-1){
                return false;
            }else{
                return true;
            }
        } else {
            return false;
        }
    }

    public SimpleCursorAdapter getChefs(){
        SQLiteDatabase DB = this.getWritableDatabase();
        SimpleCursorAdapter chefAdapter;
        Cursor cursor = DB.rawQuery("Select id_chef_filiere as _id, name from chef_filiere", null);

        int count = cursor.getCount();
        String[] fromFieldNames = new String[]{"_id","name"};

        if (count > 0){
            int[] viewIds = new int[]{R.id.id_chef_filiere, R.id.name};
            chefAdapter = new SimpleCursorAdapter(
                    context,
                    R.layout.single_item_chef_filiere,
                    cursor,
                    fromFieldNames,
                    viewIds
            );
        }
        else{
            chefAdapter = null;
        }

        return chefAdapter;
    }



    //---------------------------Traitement données tuteurs------------------------------//

    public Boolean insertTuteur(String name, String libFiliere, String nameChef, String libCours)
    {
        if (name.equals("") || libFiliere.equals("") || nameChef.equals("") || libCours.equals("")){
            return false;
        }
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor filiere = DB.rawQuery("Select id_filiere from filiere where libelle = ? LIMIT 1", new String[]{libFiliere});
        filiere.moveToNext();
        int idLibelle = Integer.parseInt(filiere.getString(0));

        Cursor chefFiliere = DB.rawQuery("Select id_chef_filiere from chef_filiere where name = ? LIMIT 1", new String[]{nameChef});
        chefFiliere.moveToNext();
        int idchef = Integer.parseInt(chefFiliere.getString(0));

        Cursor cours = DB.rawQuery("Select id_cours from cours where libelle = ? LIMIT 1", new String[]{libCours});
        cours.moveToNext();
        int idCours = Integer.parseInt(cours.getString(0));

        if (filiere.getCount() > 0 && chefFiliere.getCount() > 0 && cours.getCount() > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("name", name);
            contentValues.put("id_filiere", idLibelle);
            contentValues.put("id_chef_filiere", idchef);
            contentValues.put("id_cours", idCours);
            long result=DB.insert("tuteur", null, contentValues);
            DB.close();
            if(result==-1){
                return false;
            }else{
                return true;
            }
        } else {
            return false;
        }
    }

    public SimpleCursorAdapter getTuteurs(){
        SQLiteDatabase DB = this.getWritableDatabase();
        SimpleCursorAdapter chefAdapter;
        Cursor cursor = DB.rawQuery("Select id_tuteur as _id, name from tuteur", null);

        int count = cursor.getCount();
        String[] fromFieldNames = new String[]{"_id","name"};

        if (count > 0){
            int[] viewIds = new int[]{R.id.id_tuteur, R.id.name};
            chefAdapter = new SimpleCursorAdapter(
                    context,
                    R.layout.single_item_tuteur,
                    cursor,
                    fromFieldNames,
                    viewIds
            );
        }
        else{
            chefAdapter = null;
        }

        return chefAdapter;
    }

    public Cursor getSingleTuteur(String name){

        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select tuteur.id_tuteur as _id, tuteur.name as name, cours.libelle as libelle from tuteur join cours on tuteur.id_cours = cours.id_cours where tuteur.name = ?", new String[]{name});

        return cursor;
    }

    public Cursor getChefForSpinner(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id_chef_filiere, name from chef_filiere", null);
        return cursor;
    }

    public Cursor getCoursForSpinner(){
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select id_cours, libelle from cours", null);
        return cursor;
    }
}