package guilmon.ife;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private EditText etNombre, etDireccion, etNumero;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNombre=(EditText)findViewById(R.id.etNombre);
        etDireccion=(EditText)findViewById(R.id.etDireccion);
        etNumero=(EditText)findViewById(R.id.etNumero);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }



    public void alta (View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = etNombre.getText().toString();
        String direc = etDireccion.getText().toString();
        String num = etNumero.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nom);
        registro.put("direccion", direc);
        registro.put("numero", num);
        bd.insert("ife", null, registro);
        bd.close();
        etNombre.setText("");
        etDireccion.setText("");
        etNumero.setText("");
        Toast.makeText(this, "Se cargaron los datos del artículo",
                Toast.LENGTH_SHORT).show();
    }

    public void consultapornombre(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = etNombre.getText().toString();
        Cursor fila = bd.rawQuery(
                "select descripcion,precio from ife where nombre=" + nom, null);
        if (fila.moveToFirst()) {
            etDireccion.setText(fila.getString(0));
            etNumero.setText(fila.getString(1));
        } else
            Toast.makeText(this, "Ese nombre no existe",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void consultapordireccion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String direc = etDireccion.getText().toString();
        Cursor fila = bd.rawQuery(
                "select nombre,precio from ife where descripcion='" + direc +"'", null);
        if (fila.moveToFirst()) {
            etNombre.setText(fila.getString(0));
            etNumero.setText(fila.getString(1));
        } else
            Toast.makeText(this, "No existe esa direccion",
                    Toast.LENGTH_SHORT).show();
        bd.close();
    }

    public void bajapornombre(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom= etNombre.getText().toString();
        int cant = bd.delete("ife", "nombre=" + nom, null);
        bd.close();
        etNombre.setText("");
        etDireccion.setText("");
        etNumero.setText("");
        if (cant == 1)
            Toast.makeText(this, "Se borró usuario con ese nombre",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe usuario llamado asi",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String nom = etNombre.getText().toString();
        String direc = etDireccion.getText().toString();
        String num = etNumero.getText().toString();
        ContentValues registro = new ContentValues();
        registro.put("nombre", nom);
        registro.put("direccion", direc);
        registro.put("numero", num);
        int cant = bd.update("ife", registro, "nombre=" + nom, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe un usuario con ese nombre",
                    Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
