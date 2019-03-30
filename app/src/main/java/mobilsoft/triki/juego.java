package mobilsoft.triki;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class juego extends AppCompatActivity {

    private TextView TextvModo;
    private TextView TextvNivel;
    private TextView TextvNameJugador1;
    private TextView TextvNameJugador2;
    private LinearLayout LinearNivelInfo;
    private Button BtnSelect;

    private Integer turno;
    private Integer ganados;
    private Integer perdidos;
    private Integer empatados;
    private Integer tkSelected;

    boolean gano;
    int jugadorGano;
    int posicionGano;

    CountDownTimer time;
    Boolean enJuego;

    Integer tkItems[] = {0,0,0,0,0,0,0,0,0};
    Integer tkSuccess [][] ={{1,4,7},{2,5,8},{3,6,9},{1,5,9},{3,5,7},{1,2,3},{4,5,6},{7,8,9}};

    private juegoDetail dataJuego = new juegoDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        Bundle datos = this.getIntent().getExtras();
        dataJuego.modo=datos.getInt("modo");
        dataJuego.nivel=datos.getInt("nivel");
        dataJuego.jugador1=datos.getString("jugador1");
        dataJuego.jugador2=datos.getString("jugador2");

        TextvModo=(TextView)findViewById(R.id.tv_modo_val);
        TextvNivel=(TextView)findViewById(R.id.tv_nivel_val);
        TextvNameJugador1=(TextView)findViewById(R.id.tv_ganados);
        TextvNameJugador2=(TextView)findViewById(R.id.tv_perdidos);
        LinearNivelInfo = (LinearLayout) findViewById(R.id.ln_nivel);

        TextvNameJugador1.setText(dataJuego.jugador1);
        TextvNameJugador2.setText(dataJuego.jugador2);
        enJuego=true;
        turno = 2;
        cambiar_turno();


        ganados = 0;
        perdidos = 0;;
        empatados = 0;
        tkSelected=0;
        this.tkItems = new Integer[]{0,0,0,0,0,0,0,0,0};

        gano=false;
        jugadorGano=0;
        posicionGano=0;


        if(dataJuego.modo==1){
            TextvModo.setText("MUTLIJUGADOR");
            LinearNivelInfo.setVisibility(View.INVISIBLE);

            TextView tv_tiempo_v = (TextView) findViewById(R.id.tv_tiempo_Val);
            tv_tiempo_v.setText(" No aplica");
        }else{
            LinearNivelInfo.setVisibility(View.VISIBLE);
            TextvModo.setText("VS MAQUINA");
            if(dataJuego.nivel==1){
                TextvNivel.setText("FACIL");
            }else if(dataJuego.nivel==2){
                TextvNivel.setText("MEDIO");
            }else{
                TextvNivel.setText("DIFICIL");
            }

            reiniciarTimer();

        }
    }

    public void presionar(View view){
        String Letra;
        if(turno==1) Letra="X"; else Letra="O";
        if(enJuego) {
            switch (view.getId()) {
                case R.id.btn_triki_1:
                    if (marcarCuadro(1)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_1);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_2:
                    if (marcarCuadro(2)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_2);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_3:
                    if (marcarCuadro(3)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_3);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_4:
                    if (marcarCuadro(4)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_4);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_5:
                    if (marcarCuadro(5)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_5);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_6:
                    if (marcarCuadro(6)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_6);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_7:
                    if (marcarCuadro(7)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_7);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_8:
                    if (marcarCuadro(8)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_8);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
                case R.id.btn_triki_9:
                    if (marcarCuadro(9)) {
                        BtnSelect = (Button) findViewById(R.id.btn_triki_9);
                        BtnSelect.setText(Letra);
                        cambiar_turno();
                    }
                    break;
            }
            if (tkSelected == 9 && !gano) {
                terminarJuego();
            }
        }
    }

    public boolean marcarCuadro(Integer posicion){
        boolean marcado = false;
        if(tkSelected<=9 && !gano) {
            if (tkItems[posicion - 1] != 0) {
                marcado = false;
            } else {
                marcado = true;
                tkItems[posicion - 1] = turno;
                tkSelected++;
            }
        }
        return marcado;
    }

    public void cambiar_turno(){
        TextView textViewGanados=(TextView)findViewById(R.id.tv_ganados);
        TextView textViewPerdidos=(TextView)findViewById(R.id.tv_perdidos);
        if(turno==2){
            turno=1;
            textViewGanados.setTextColor(Color.BLACK);
            textViewGanados.setText("Jugador 1: " + dataJuego.jugador1 + " <-- Su turno");
            textViewPerdidos.setTextColor(Color.GRAY);
            textViewPerdidos.setText("Jugador 2: " + dataJuego.jugador2);
        }else{
            turno=2;
            textViewGanados.setTextColor(Color.GRAY);
            textViewGanados.setText("Jugador 1: " +dataJuego.jugador1);
            textViewPerdidos.setTextColor(Color.BLACK);
            textViewPerdidos.setText("Jugador 2: " + dataJuego.jugador2 + " <-- Su turno");
            if(dataJuego.modo==2 && tkSelected<9 && !gano){
                View v;
                switch (colocarRespuestaCPU()) {
                    case 1:
                        v=findViewById(R.id.btn_triki_1);
                        presionar(v);
                        break;
                    case 2:
                        v=findViewById(R.id.btn_triki_2);
                        presionar(v);
                        break;
                    case 3:
                        v=findViewById(R.id.btn_triki_3);
                        presionar(v);
                        break;
                    case 4:
                        v=findViewById(R.id.btn_triki_4);
                        presionar(v);
                        break;
                    case 5:
                        v=findViewById(R.id.btn_triki_5);
                        presionar(v);
                        break;
                    case 6:
                        v=findViewById(R.id.btn_triki_6);
                        presionar(v);
                        break;
                    case 7:
                        v=findViewById(R.id.btn_triki_7);
                        presionar(v);
                        break;
                    case 8:
                        v=findViewById(R.id.btn_triki_8);
                        presionar(v);
                        break;
                    case 9:
                        v=findViewById(R.id.btn_triki_9);
                        presionar(v);
                        break;
                }

            }
        }
        if(!gano) {
            ganoJuego();
        }
    }
    public void ganoJuego(){
        List<Integer> nj1 = new ArrayList<Integer>();
        List<Integer> nj2 = new ArrayList<Integer>();
        int factor = 0;
        while ( factor <= 8 ) {
            if(tkItems[factor] != 0) {
                if(tkItems[factor] == 1) {
                    nj1.add(factor+1);
                }else{
                    nj2.add(factor+1);
                }
            }
            factor++;
        }

        int fc = 0;
        while (fc <= 7 && !gano) {
            int pos1 = tkSuccess[fc][0];
            int pos2 = tkSuccess[fc][1];
            int pos3 = tkSuccess[fc][2];

            if (nj1.size() >= 3) {
                int factor_j1 = 0;
                int coincidencias_j1 = 0;
                while (factor_j1 <= nj1.size() - 1) {
                    int numjug = nj1.get(factor_j1);
                    if (numjug == pos1 || numjug == pos2 || numjug == pos3) {
                        coincidencias_j1++;
                    }
                    factor_j1++;
                }

                if (coincidencias_j1 == 3) {
                    jugadorGano=1;
                    posicionGano = fc;
                    gano=true;
                }
            }

            if (nj2.size() >= 3) {
                int factor_j2 = 0;
                int coincidencias_j2 = 0;
                while (factor_j2 <= nj2.size() - 1) {
                    int numjug = nj2.get(factor_j2);
                    if (numjug == pos1 || numjug == pos2 || numjug == pos3) {
                        coincidencias_j2++;
                    }
                    factor_j2++;
                }

                if (coincidencias_j2 == 3) {
                    jugadorGano=2;
                    posicionGano = fc;
                    gano=true;
                }
            }

            fc++;
        }

        if(gano){
            if(posicionGano==0 || posicionGano==3 || posicionGano==5){
                Button btn1 = (Button) findViewById(R.id.btn_triki_1);
                btn1.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==1 || posicionGano==5){
                Button btn2 = (Button) findViewById(R.id.btn_triki_2);
                btn2.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==2 || posicionGano==4 || posicionGano==5){
                Button btn3 = (Button) findViewById(R.id.btn_triki_3);
                btn3.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==0 || posicionGano==6){
                Button btn4 = (Button) findViewById(R.id.btn_triki_4);
                btn4.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==1 || posicionGano==3 || posicionGano==4 || posicionGano==6){
                Button btn5 = (Button) findViewById(R.id.btn_triki_5);
                btn5.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==2 || posicionGano==6){
                Button btn6 = (Button) findViewById(R.id.btn_triki_6);
                btn6.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==0 || posicionGano==4 || posicionGano==7){
                Button btn7 = (Button) findViewById(R.id.btn_triki_7);
                btn7.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==1 || posicionGano==7){
                Button btn8 = (Button) findViewById(R.id.btn_triki_8);
                btn8.setBackgroundResource(R.color.colorSuccess);
            }
            if(posicionGano==2 || posicionGano==3 || posicionGano==7){
                Button btn9 = (Button) findViewById(R.id.btn_triki_9);
                btn9.setBackgroundResource(R.color.colorSuccess);
            }
            terminarJuego();
        }


    }

    public void terminarJuego(){
        String mensaje="";
        enJuego=false;
        if (gano) {
            if(jugadorGano==1) {
                mensaje="Ganó "+dataJuego.jugador1;
                ganados++;
            }else{
                mensaje="Ganó "+dataJuego.jugador2;
                perdidos++;
            }
        }else{
            mensaje="JUEGO TERMINADO EN EMPATE";
            empatados ++;
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(mensaje)
            .setPositiveButton("Entendido", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });
        builder.show();


        TextView ganadosVal =(TextView)findViewById(R.id.tv_ganados_val);
        TextView peridosVal=(TextView)findViewById(R.id.tv_perdidos_val);
        TextView empatadosVal=(TextView)findViewById(R.id.tv_empatados_val);
        TextView jugadosVal=(TextView)findViewById(R.id.tv_jugados_val);
        ganadosVal.setText(ganados.toString());
        peridosVal.setText(perdidos.toString());
        empatadosVal.setText(empatados.toString());
        jugadosVal.setText(""+(empatados+ganados+perdidos));

        if(dataJuego.modo==2) {
            time.cancel();
        }
    }
/*
    public void colocarRespuestaCPU(){
        int r=(int) (Math.random()*9);
        while(botones.get(r).getText()!=""){
            r=(int) (Math.random()*9);
        }
        botones.get(r).setText("O");
    }
*/
    public Integer colocarRespuestaCPU(){
        int r=(int) (Math.random()*9);
        while(tkItems[r]!=0){
            r=(int) (Math.random()*9);
        }
        return (r+1);
    }
/*
    public String numeroRandom(){

        Random random = new Random();
        Boolean esta = false;
        int valorDado;
        valorDado=random.nextInt(8)+1;
        int factor = 0;
        while ( factor <= 8) {
            if(tkItems[factor] == valorDado) {
                esta = true;
            }
            if(factor==8){
                if(esta) {
                    factor = 0;
                    esta = false;
                    valorDado = random.nextInt(8) + 1;
                }
            }else{
                factor++;
            }
        }

        return " "+valorDado;
    }
*/

public void reiniciarTimer(){
    if(dataJuego.nivel==2 || dataJuego.nivel==3) {
        int tiempo = 0;
        if(dataJuego.nivel==2){
            tiempo = 20000;
        }else{
            tiempo = 10000;
        }
        TextView tv_tiempo_v = (TextView) findViewById(R.id.tv_tiempo_Val);
        time = new CountDownTimer(tiempo, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView tv_tiempo_v = (TextView) findViewById(R.id.tv_tiempo_Val);
                tv_tiempo_v.setText(" " + (millisUntilFinished)  / 1000);
            }

            public void onFinish() {
                terminarJuego();
                time.cancel();
                TextView tv_tiempo_v = (TextView) findViewById(R.id.tv_tiempo_Val);
                tv_tiempo_v.setText(" Acabo ");
            }
        };
        time.start();
    }else{
        TextView tv_tiempo_v = (TextView) findViewById(R.id.tv_tiempo_Val);
        tv_tiempo_v.setText(" No aplica");
    }
}

    public void nuevoJuego(View view){
        if(dataJuego.modo==2) {
            time.cancel();
            reiniciarTimer();
        }
        Button btn1 = (Button) findViewById(R.id.btn_triki_1);
        Button btn2 = (Button) findViewById(R.id.btn_triki_2);
        Button btn3 = (Button) findViewById(R.id.btn_triki_3);
        Button btn4 = (Button) findViewById(R.id.btn_triki_4);
        Button btn5 = (Button) findViewById(R.id.btn_triki_5);
        Button btn6 = (Button) findViewById(R.id.btn_triki_6);
        Button btn7 = (Button) findViewById(R.id.btn_triki_7);
        Button btn8 = (Button) findViewById(R.id.btn_triki_8);
        Button btn9 = (Button) findViewById(R.id.btn_triki_9);

        btn1.setBackgroundResource(R.color.colorGray);
        btn1.setText("");
        btn2.setBackgroundResource(R.color.colorGray);
        btn2.setText("");
        btn3.setBackgroundResource(R.color.colorGray);
        btn3.setText("");
        btn4.setBackgroundResource(R.color.colorGray);
        btn4.setText("");
        btn5.setBackgroundResource(R.color.colorGray);
        btn5.setText("");
        btn6.setBackgroundResource(R.color.colorGray);
        btn6.setText("");
        btn7.setBackgroundResource(R.color.colorGray);
        btn7.setText("");
        btn8.setBackgroundResource(R.color.colorGray);
        btn8.setText("");
        btn9.setBackgroundResource(R.color.colorGray);
        btn9.setText("");

        turno = 2;
        tkSelected=0;
        gano=false;
        enJuego=true;
        jugadorGano=0;
        posicionGano=0;
        this.tkItems = new Integer[]{0,0,0,0,0,0,0,0,0};
        cambiar_turno();
    }

}
