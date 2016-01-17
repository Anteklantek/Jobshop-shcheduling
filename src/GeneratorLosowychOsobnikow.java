import com.rits.cloning.Cloner;

import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Antek on 2016-01-13.
 */
public class GeneratorLosowychOsobnikow {


    public static Osobnik generujLosowegoOsobnika(InstancjaProblemu instancjaProblemu) {
        Random random = new Random();
        Cloner cloner = new Cloner();
        ArrayList<Zadanie> listaZadanTemp = cloner.deepClone(instancjaProblemu.getListaZadan());
        ArrayList<Zadanie> kolejnoscDodawaniaZadan = new ArrayList<>();

        ArrayList<Zadanie> listaZadanInstancjiDoUsuawania = new ArrayList<>(instancjaProblemu.getListaZadan());


        Osobnik osobnik = cloner.deepClone(instancjaProblemu.getOsobnikBezOperacj());


        int iloscZadan = listaZadanTemp.size();

        for (int i = 0; i < iloscZadan; i++) {
            int indeks = random.nextInt(listaZadanTemp.size());
            if (listaZadanTemp.get(indeks).getOperacja1().getNrMaszyny() == 1) {
                osobnik.getMaszyna1().dodajWPierwszeWolneMiejsce(listaZadanTemp.get(indeks).getOperacja1());
            } else {
                osobnik.getMaszyna2().dodajWPierwszeWolneMiejsce(listaZadanTemp.get(indeks).getOperacja1());
            }
            if (listaZadanTemp.get(indeks).getOperacja2().getNrMaszyny() == 1) {
                osobnik.getMaszyna1().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(listaZadanTemp.get(indeks).getOperacja2());
            } else {
                osobnik.getMaszyna2().dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(listaZadanTemp.get(indeks).getOperacja2());
            }
            kolejnoscDodawaniaZadan.add(listaZadanInstancjiDoUsuawania.get(indeks));
            listaZadanInstancjiDoUsuawania.remove(indeks);
            listaZadanTemp.remove(indeks);
        }
        osobnik.setKolejnoscDodawaniaZadan(kolejnoscDodawaniaZadan);
        osobnik.setInstancjaProblemu(instancjaProblemu);
        return osobnik;
    }

}
