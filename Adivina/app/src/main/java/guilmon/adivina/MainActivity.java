package guilmon.adivina;

import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int numero;
    EditText etNumero;
    TextView txtIntento, txtMensaje;

    public void Numero(View v){
        int valor = Integer.parseInt(etNumero.getText().toString());
        if (numero==valor){
            int puntosactual = Integer.parseInt(txtIntento.getText().toString());
            puntosactual++;
            txtIntento.setText(String.valueOf(puntosactual));
            txtMensaje.setText("GG, Bien Jugado");
            etNumero.setText("");
            SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferencias.edit();
            editor.putInt("puntos", puntosactual);
            editor.commit();
        }else {
            if (valor>numero){
                txtMensaje.setText("Te pasaste");
            }else {
                txtMensaje.setText("Muy lejos");
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etNumero = (EditText)findViewById(R.id.etNumero);
        txtIntento = (TextView)findViewById(R.id.txtIntento);
        txtMensaje=(TextView)findViewById(R.id.txtMensaje);

        SharedPreferences preferencias = getSharedPreferences("datos", Context.MODE_PRIVATE);
        String v = String.valueOf(preferencias.getInt("puntos", 0));
        txtIntento.setText(v);
        numero = 1 + (int) (Math.random()*50);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
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
