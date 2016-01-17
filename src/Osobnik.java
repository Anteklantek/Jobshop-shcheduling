import com.rits.cloning.Cloner;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Antek on 2016-01-13.
 */
public class Osobnik implements Comparable<Osobnik>{
    InstancjaProblemu instancjaProblemu;
    Maszyna maszyna1;
    Maszyna maszyna2;
    ArrayList<Zadanie> kolejnoscDodawaniaZadan;

    public Osobnik(InstancjaProblemu instancjaProblemu) {
        this.instancjaProblemu = instancjaProblemu;
    }

    public InstancjaProblemu getInstancjaProblemu() {
        return instancjaProblemu;
    }

    public void setInstancjaProblemu(InstancjaProblemu instancjaProblemu) {
        this.instancjaProblemu = instancjaProblemu;
    }

    public Maszyna getMaszyna1() {
        return maszyna1;
    }

    public void setMaszyna1(Maszyna maszyna1) {
        this.maszyna1 = maszyna1;
    }

    public Maszyna getMaszyna2() {
        return maszyna2;
    }

    public void setMaszyna2(Maszyna maszyna2) {
        this.maszyna2 = maszyna2;
    }

    public ArrayList<Zadanie> getKolejnoscDodawaniaZadan() {
        return kolejnoscDodawaniaZadan;
    }

    public void setKolejnoscDodawaniaZadan(ArrayList<Zadanie> kolejnoscDodawaniaZadan) {
        this.kolejnoscDodawaniaZadan = kolejnoscDodawaniaZadan;
    }

    public int getSumaCzasowZakonczenPracy() {

        return maszyna1.getSumaCzasowZakonczenOperacji() + maszyna2.getSumaCzasowZakonczenOperacji();
    }


    //0 - w lewo, 1 - prawo
    public void przesunZadaniewHistoriiDodawania(int indeksZadania, int wKtoraStrone, int oIlePozycji) {
        Zadanie zadanieTemp;
        if (wKtoraStrone == 1) {
            if (oIlePozycji + indeksZadania > kolejnoscDodawaniaZadan.size() - 1) {
                oIlePozycji = kolejnoscDodawaniaZadan.size() - 1 - indeksZadania;
            }
            zadanieTemp = kolejnoscDodawaniaZadan.get(indeksZadania);
            for (int i = indeksZadania; i < oIlePozycji + indeksZadania; i++) {
                kolejnoscDodawaniaZadan.set(i, kolejnoscDodawaniaZadan.get(i + 1));
            }
            kolejnoscDodawaniaZadan.set(indeksZadania + oIlePozycji, zadanieTemp);

        }
        if (wKtoraStrone == 0) {
            if (indeksZadania - oIlePozycji < 0) {
                oIlePozycji = indeksZadania;
            }
            zadanieTemp = kolejnoscDodawaniaZadan.get(indeksZadania);
            for (int i = indeksZadania; i > indeksZadania - oIlePozycji; i--) {
                kolejnoscDodawaniaZadan.set(i, kolejnoscDodawaniaZadan.get(i - 1));
            }
            kolejnoscDodawaniaZadan.set(indeksZadania - oIlePozycji, zadanieTemp);
        }
    }

    public Osobnik mutuj() {
        Cloner cloner = new Cloner();
        Random random = new Random();

        Osobnik osobnik = cloner.deepClone(instancjaProblemu.getOsobnikBezOperacj());
        ArrayList<Zadanie> kolejnoscDodawaniaZadanTemp = new ArrayList<>(kolejnoscDodawaniaZadan);
        osobnik.setKolejnoscDodawaniaZadan(kolejnoscDodawaniaZadanTemp);

        int wKtoraStrone = random.nextInt(2);
        int ktoreZadanie = random.nextInt(kolejnoscDodawaniaZadan.size());
        int oIlePrzesunac = random.nextInt(kolejnoscDodawaniaZadan.size() / 8) + 1;

        osobnik.przesunZadaniewHistoriiDodawania(ktoreZadanie, wKtoraStrone, oIlePrzesunac);

        ArrayList<Zadanie> kolejnoscDodawaniaZadanDeepCopy = cloner.deepClone(osobnik.getKolejnoscDodawaniaZadan());

        for (Zadanie zadanie : kolejnoscDodawaniaZadanDeepCopy) {

            if (zadanie.getOperacja1().getNrMaszyny() == 1)
                osobnik.getMaszyna1().dodajWPierwszeWolneMiejsce(zadanie.getOperacja1());
            if (zadanie.getOperacja1().getNrMaszyny() == 2)
                osobnik.getMaszyna2().dodajWPierwszeWolneMiejsce(zadanie.getOperacja1());

            if (zadanie.getOperacja2().getNrMaszyny() == 1)
                osobnik.getMaszyna1().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(zadanie.getOperacja2());
            if (zadanie.getOperacja2().getNrMaszyny() == 2)
                osobnik.getMaszyna2().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(zadanie.getOperacja2());

        }

        osobnik.setInstancjaProblemu(instancjaProblemu);
        return osobnik;
    }

    public Osobnik krzyzuj(Osobnik osobnik){
        Random random = new Random();
        Cloner cloner = new Cloner();
        Osobnik osobnikWynikowy = cloner.deepClone(instancjaProblemu.getOsobnikBezOperacj());

        ArrayList<Zadanie> kolejnoscDodawaniaZadanWynikowa = new ArrayList<>();


        int indeks = random.nextInt(kolejnoscDodawaniaZadan.size());

        for(int i = 0; i<=indeks;i++) {
        kolejnoscDodawaniaZadanWynikowa.add(kolejnoscDodawaniaZadan.get(i));
        }

        for(Zadanie zadanie : osobnik.getKolejnoscDodawaniaZadan()){
            if(!kolejnoscDodawaniaZadanWynikowa.contains(zadanie)){
             kolejnoscDodawaniaZadanWynikowa.add(zadanie);
            }
        }

        ArrayList<Zadanie> kolejnoscDodawaniaZadanDeepCopy = cloner.deepClone(kolejnoscDodawaniaZadanWynikowa);

        for(Zadanie zadanie : kolejnoscDodawaniaZadanDeepCopy){
            if (zadanie.getOperacja1().getNrMaszyny() == 1)
                osobnikWynikowy.getMaszyna1().dodajWPierwszeWolneMiejsce(zadanie.getOperacja1());
            if (zadanie.getOperacja1().getNrMaszyny() == 2)
                osobnikWynikowy.getMaszyna2().dodajWPierwszeWolneMiejsce(zadanie.getOperacja1());

            if (zadanie.getOperacja2().getNrMaszyny() == 1)
                osobnikWynikowy.getMaszyna1().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(zadanie.getOperacja2());
            if (zadanie.getOperacja2().getNrMaszyny() == 2)
                osobnikWynikowy.getMaszyna2().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(zadanie.getOperacja2());
        }


        osobnikWynikowy.setKolejnoscDodawaniaZadan(kolejnoscDodawaniaZadanWynikowa);
        osobnikWynikowy.setInstancjaProblemu(instancjaProblemu);
        return osobnikWynikowy;
    }

    @Override
    public int compareTo(Osobnik o) {
        return this.getSumaCzasowZakonczenPracy() - o.getSumaCzasowZakonczenPracy();
    }
}
