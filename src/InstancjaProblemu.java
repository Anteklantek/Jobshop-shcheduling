import java.util.ArrayList;

/**
 * Created by Antek on 2016-01-12.
 */
public class InstancjaProblemu {
    int numerInstancji;
    ArrayList<Zadanie> listaZadan;
    ArrayList<Przestoj> listaPrzestojow;
    Osobnik osobnikBezOperacj;

    public InstancjaProblemu(ArrayList<Zadanie> listaZadan, ArrayList<Przestoj> listaPrzestojow) {
        this.listaZadan = listaZadan;
        this.listaPrzestojow = listaPrzestojow;
    }

    public InstancjaProblemu(int numerInstancji, ArrayList<Zadanie> listaZadan, ArrayList<Przestoj> listaPrzestojow) {
        this.numerInstancji = numerInstancji;
        this.listaZadan = listaZadan;
        this.listaPrzestojow = listaPrzestojow;
    }

    public Osobnik getOsobnikBezOperacj() {
        return osobnikBezOperacj;
    }

    public void setOsobnikBezOperacj(Osobnik osobnikBezOperacj) {
        this.osobnikBezOperacj = osobnikBezOperacj;
    }

    public int getNumerInstancji() {
        return numerInstancji;
    }

    public void setNumerInstancji(int numerInstancji) {
        this.numerInstancji = numerInstancji;
    }

    public ArrayList<Zadanie> getListaZadan() {
        return listaZadan;
    }

    public void setListaZadan(ArrayList<Zadanie> listaZadan) {
        this.listaZadan = listaZadan;
    }

    public ArrayList<Przestoj> getListaPrzestojow() {
        return listaPrzestojow;
    }

    public void setListaPrzestojow(ArrayList<Przestoj> listaPrzestojow) {
        this.listaPrzestojow = listaPrzestojow;
    }

    public int getSumaCzasowZawartosicDlaMaszyny1(){
        int sumaCzasu=0;
        for(Zadanie zadanie : listaZadan){
            if(zadanie.getOperacja1().getNrMaszyny()==1){
                sumaCzasu +=zadanie.getOperacja1().getCzasTrwania();
            }
            if(zadanie.getOperacja2().getNrMaszyny()==1){
                sumaCzasu +=zadanie.getOperacja2().getCzasTrwania();
            }
        }
        for(Przestoj przestoj : listaPrzestojow){
            if(przestoj.getNrMaszyny()==1)
            sumaCzasu += przestoj.getCzasTrwania();
        }
        return sumaCzasu;
    }
    public int getSumaCzasowZawartosicDlaMaszyny2(){
        int sumaCzasu=0;
        for(Zadanie zadanie : listaZadan){
            if(zadanie.getOperacja1().getNrMaszyny()==2){
                sumaCzasu +=zadanie.getOperacja1().getCzasTrwania();
            }
            if(zadanie.getOperacja2().getNrMaszyny()==2){
                sumaCzasu +=zadanie.getOperacja2().getCzasTrwania();
            }
        }
        for(Przestoj przestoj : listaPrzestojow){
            if(przestoj.getNrMaszyny()==2)
                sumaCzasu += przestoj.getCzasTrwania();
        }
        return sumaCzasu;
    }
}
