package mobilsoft.triki;

/**
 * Created by Mobilsoft on 30/03/2018.
 */

public class juegoDetail {
    public Integer modo;
    public String jugador1;
    public String jugador2;
    public Integer nivel;

    public void setData(Integer modoIn,String jugador1In,String jugador2In, Integer nivel){
        this.modo=modoIn;
        this.jugador1=jugador1In;
        this.jugador2=jugador2In;
        this.nivel=nivel;
    }

    public static void main(String[] main) {
    }
}
