/**
 * Created by Antek on 2016-01-12.
 */
public class Zadanie {

    private int numer;
    private Operacja operacja1;
    private Operacja operacja2;
    private int czasGotowosci;

    public Zadanie(int numer, Operacja operacja1, Operacja operacja2) {
        this.numer = numer;
        this.operacja1 = operacja1;
        this.operacja2 = operacja2;
    }

    public int getNumer() {
        return numer;
    }

    public void setNumer(int numer) {
        this.numer = numer;
    }

    public Operacja getOperacja1() {
        return operacja1;
    }

    public void setOperacja1(Operacja operacja1) {
        this.operacja1 = operacja1;
    }

    public Operacja getOperacja2() {
        return operacja2;
    }

    public void setOperacja2(Operacja operacja2) {
        this.operacja2 = operacja2;
    }

    public int getCzasGotowosci() {
        return czasGotowosci;
    }

    public void setCzasGotowosci(int czasGotowosci) {
        this.czasGotowosci = czasGotowosci;
    }
}
