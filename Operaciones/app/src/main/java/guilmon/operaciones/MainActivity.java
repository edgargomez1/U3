package guilmon.operaciones;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {
    EditText et1, et2;
    TextView txtResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        txtResultados = (TextView)findViewById(R.id.txtResultados);
        et1 = (EditText)findViewById(R.id.et1);
        et2 = (EditText)findViewById(R.id.et2);

        cargar();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void suma(View v) {
        operacion(1);
        cargar();
    }

    public void resta(View v) {
        operacion(2);
        cargar();
    }

    public void multiplicacion(View v) {
        operacion(3);
        cargar();
    }

    public void division(View v) {
        operacion(4);
        cargar();
    }

    private void operacion(int operacion) {
        String num1 = et1.getText().toString();
        String num2 = et2.getText().toString();
        if (!num1.equals("") && !num2.equals("")) {
            int dato1 = Integer.parseInt(num1);
            int dato2 = Integer.parseInt(num2);
            String result;
            switch (operacion) {
                case 1:
                    result = suma(dato1, dato2);
                    Toast.makeText(this, "Suma resultado : " + result, Toast.LENGTH_SHORT).show();
                    save(dato1 + " + " + dato2 + " = " + result);
                    break;
                case 2:
                    result = resta(dato1, dato2);
                    Toast.makeText(this, "Resta resultado : " + result, Toast.LENGTH_SHORT).show();
                    save(dato1 + " - " + dato2 + " = " + result);
                    break;
                case 3:
                    result = multiplicacion(dato1, dato2);
                    Toast.makeText(this, "Multiplicacion resultado : " + result, Toast.LENGTH_SHORT).show();
                    save(dato1 + " * " + dato2 + " = " + result);
                    break;
                case 4:
                    result = division(dato1, dato2);
                    Toast.makeText(this, "Division resultado : " + result, Toast.LENGTH_SHORT).show();
                    save(dato1 + " / " + dato2 + " = " + result);
                    break;
                default:
                    break;
            }
        } else {
            Toast.makeText(this, "Si no pones nada no trbajo :| ", Toast.LENGTH_SHORT).show();
        }
    }

    private String suma(int num1, int num2) {
        int add = num1 + num2;
        String result = "" + add;
        return result;
    }

    private String resta(int num1, int num2) {
        int sub = num1 - num2;
        String result = "" + sub;
        return result;
    }

    private String multiplicacion(int num1, int num2) {
        int mul = num1 * num2;
        String result = "" + mul;
        return result;
    }

    private String division(float num1,float  num2) {
        if (num2 != 0) {
            float div = num1 / num2;
            String result = "" + div;
            return result;
        } else {
            return "Si ya sabes que no divido cero para que lo usas";
        }
    }

    private void cargar(){
        String nombre="datos.txt";
        nombre=nombre.replace('/','-');
        boolean enco = false;
        String[] archivos = fileList();
        for(int f = 0;f<archivos.length;f++)
            if(nombre.equals(archivos[f]))
                enco=true;
        if(enco==true){
            try {
                InputStreamReader archivo = new InputStreamReader(
                        openFileInput(nombre));
                BufferedReader br = new BufferedReader(archivo);
                String linea = br.readLine();
                String operaciones = "";
                while (linea != null) {
                    operaciones = operaciones + linea + "\n";
                    linea = br.readLine();
                }
                br.close();
                archivo.close();
                txtResultados.setText(operaciones);
            } catch (IOException e) {
            }
        }else  {

        }
    }

    private void save(String data_1){
        String nombre="datos.txt";
        try {
            OutputStreamWriter archivo = new OutputStreamWriter(openFileOutput(
                    nombre, Activity.MODE_PRIVATE));
            archivo.write(txtResultados.getText().toString()+"\n"+data_1);
            archivo.flush();
            archivo.close();
        } catch (IOException e) {
        }

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
