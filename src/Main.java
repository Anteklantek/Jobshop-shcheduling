import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Main {

    public static void main(String[] args) {
        final int MUTACJI_NA_100 = 50;
        InstancjaProblemu instancjaProblemu = null;
        GeneratorInstancji generatorInstancji = new GeneratorInstancji();
        instancjaProblemu = generatorInstancji.utworzInstancjeProblemowCzasy20(40);
        PlikiTekstowe.zapiszInstancjeDoPliku("InstancjaProblemuNr5",instancjaProblemu);
//        try {
//            instancjaProblemu = PlikiTekstowe.odczytajInstancjeZPliku("InstancjaProblemuNr3");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        ArrayList<Osobnik> osobnicyPowstaliZeSkrzyzowania = new ArrayList<>();
        ArrayList<Osobnik> osobnicyPoEtapieMutacji = new ArrayList<>();
        ArrayList<Osobnik> poczatkowiOsobnicy = new ArrayList<>();
        for (int i = 0; i < 600; i++) {
            Osobnik osobnik = GeneratorLosowychOsobnikow.generujLosowegoOsobnika(instancjaProblemu);
            poczatkowiOsobnicy.add(osobnik);
        }

        Populacja populacja = new Populacja(poczatkowiOsobnicy);

        int czasLosowegoUszeregowania = populacja.getRandom().getSumaCzasowZakonczenPracy();


        for (int k = 0; k < 100; k++) {
            populacja.selekcjaTurniejowa();
            for (int i = 0; i < populacja.size(); i++) {
                Osobnik osobnik = populacja.getListaOsobnikow().get(i);
                for (int j = 0; j < 1; j++) {
                    Osobnik osobnikWylosowany = populacja.getRandom();
                    Osobnik osobnikWynikowy = osobnik.krzyzuj(osobnikWylosowany);
                    osobnicyPowstaliZeSkrzyzowania.add(osobnikWynikowy);
                }
            }
            for (Osobnik osobnik : osobnicyPowstaliZeSkrzyzowania) {
                populacja.getListaOsobnikow().add(osobnik);
            }
            osobnicyPowstaliZeSkrzyzowania.clear();

            for (Osobnik osobnik : populacja.getListaOsobnikow()) {
                Random random = new Random();
                int czyZachodziMutacja = random.nextInt(100) + 1;
                if (czyZachodziMutacja <= MUTACJI_NA_100) {
                    osobnicyPoEtapieMutacji.add(osobnik.mutuj());
                } else {
                    osobnicyPoEtapieMutacji.add(osobnik);
                }
            }

            populacja.getListaOsobnikow().clear();

            for (Osobnik osobnik : osobnicyPoEtapieMutacji) {
                populacja.getListaOsobnikow().add(osobnik);
            }

            osobnicyPoEtapieMutacji.clear();

            System.out.println(populacja.getOsobnikZNajlepszymCzasem().getSumaCzasowZakonczenPracy());
        }

        PlikiTekstowe.zapiszRozwiazanieDoPlike("Rozawiązanie_SelekcjaTurniejowa_turnieje2osobowe_pMutacji50%",populacja.getOsobnikZNajlepszymCzasem(),czasLosowegoUszeregowania);
    }
}
