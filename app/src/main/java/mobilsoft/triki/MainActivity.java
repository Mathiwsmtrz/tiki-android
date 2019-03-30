package mobilsoft.triki;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private TextView TextvJugador1;
    private TextView TextvJugador2;
    private Spinner spinner_modo_juego;
    private Spinner spinner_niveles_juego;
    private LinearLayout LinearDificultades;

    private juegoDetail dataJuego = new juegoDetail();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        cargarSpinnerModos();
        cargarSpinnerNieveles();
    }

    public void cargarSpinnerModos(){
        spinner_modo_juego  = (Spinner) findViewById(R.id.spin_modo_juego);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.modos_juego, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_modo_juego.setAdapter(adapter);
        spinner_modo_juego.setOnItemSelectedListener(this);
    }
    public void cargarSpinnerNieveles(){
        spinner_niveles_juego = (Spinner) findViewById(R.id.spin_nivel_dificultad);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.niveles_difultad, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_niveles_juego.setAdapter(adapter);
        spinner_niveles_juego.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view,int position, long id) {
                dataJuego.nivel=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        TextvJugador1 = (TextView) findViewById(R.id.textvJugador1);
        TextvJugador2 = (TextView) findViewById(R.id.textvJugador2);
        LinearDificultades = (LinearLayout) findViewById(R.id.linearDificultad);
        this.dataJuego.modo=pos;
        if(pos!=0){
            TextvJugador1.setText("");
            TextvJugador2.setText("");
            if(pos==1){
                LinearDificultades.setVisibility(View.INVISIBLE);
                TextvJugador2.setInputType(InputType.TYPE_CLASS_TEXT);
                TextvJugador2.setKeyListener(new EditText(getApplicationContext()).getKeyListener());
            }else{
                LinearDificultades.setVisibility(View.VISIBLE);
                TextvJugador2.setText("Maquina");
                TextvJugador2.setInputType(InputType.TYPE_NULL);
                TextvJugador2.setKeyListener(null);
            }
        }
    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void iniciarJuego(View view){
        TextvJugador1 = (TextView) findViewById(R.id.textvJugador1);
        TextvJugador2 = (TextView) findViewById(R.id.textvJugador2);
        String jugador1Text = (String) TextvJugador1.getText().toString();
        String jugador2Text = (String) TextvJugador2.getText().toString();

        this.dataJuego.jugador1=jugador1Text;
        this.dataJuego.jugador2=jugador2Text;

        if(this.dataJuego.modo==0) {
             Toast.makeText(getApplicationContext(), "Seleecione un modo de juego para continuar "+this.dataJuego.modo.toString(), Toast.LENGTH_SHORT).show();
        }else{
            if(this.dataJuego.modo==1){
                if(TextUtils.isEmpty(jugador1Text) || TextUtils.isEmpty(jugador2Text)){
                   Toast.makeText(getApplicationContext(), "Debe digitar los nombres de los dos jugadores", Toast.LENGTH_SHORT).show();
                }else{
                    Intent layout1_intent = new Intent(this,juego.class);
                    layout1_intent.putExtra("modo",dataJuego.modo);
                    layout1_intent.putExtra("jugador1",dataJuego.jugador1);
                    layout1_intent.putExtra("jugador2",dataJuego.jugador2);
                    layout1_intent.putExtra("nivel",dataJuego.nivel);
                    startActivity(layout1_intent);
                }
            }else{
                if(this.dataJuego.nivel==0) {
                   Toast.makeText(getApplicationContext(), "Seleccione un nivel para continuar", Toast.LENGTH_SHORT).show();
                }else{
                    if(TextUtils.isEmpty(jugador1Text)){
                        Toast.makeText(getApplicationContext(), "Debe digitar el nombre del jugador principal", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent layout1_intent = new Intent(this,juego.class);
                        layout1_intent.putExtra("modo",dataJuego.modo);
                        layout1_intent.putExtra("jugador1",dataJuego.jugador1);
                        layout1_intent.putExtra("jugador2",dataJuego.jugador2);
                        layout1_intent.putExtra("nivel",dataJuego.nivel);
                        startActivity(layout1_intent);
                    }
                }
            }
        }




    }

}
