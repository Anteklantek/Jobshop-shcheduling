import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Antek on 2016-01-12.
 */
public class GeneratorInstancji {

    Random random = new Random();
    ArrayList<Integer> listaNumerowInstancji = new ArrayList<>();
    private final int PRZEDZIAL_CZASOW_GOTOWOSCI = 800;
    private final double DZIELNIK_N_DLA_USTALENIA_LICZBY_PRZESTOJOW = 10.0;

    public GeneratorInstancji() {
        for (int i = 1; i < 1000; i++) {
            listaNumerowInstancji.add(new Integer(i));
        }
    }

    public InstancjaProblemu utworzInstancjeProblemowCzasy20(int liczbaZadan) {

        ArrayList<Zadanie> listaZadan = new ArrayList<>();
        ArrayList<Przestoj> listaPrzestojow = new ArrayList<>();
        int sumaCzasowTrwaniaOperacjiMaszyna1 = 0;
        int sumaCzasowTrwaniaOperacjiMaszyna2 = 0;


        for (int i = 1; i <= liczbaZadan; i++) {
            Zadanie zadanie = generujZadanie20(i);

            zadanie.setCzasGotowosci(random.nextInt(PRZEDZIAL_CZASOW_GOTOWOSCI) + 1);

            int numerMaszynyDlaOperacji1 = random.nextInt(2) + 1;
            zadanie.getOperacja1().setNrMaszyny(numerMaszynyDlaOperacji1);
            zadanie.getOperacja2().setNrMaszyny(3 - numerMaszynyDlaOperacji1);

            if (zadanie.getOperacja1().getNrMaszyny() == 1) {
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja2().getCzasTrwania();
            } else {
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja2().getCzasTrwania();
            }

            listaZadan.add(zadanie);
        }

        int liczbaPrzestojow = (int) Math.round(liczbaZadan / DZIELNIK_N_DLA_USTALENIA_LICZBY_PRZESTOJOW);

        ArrayList<Integer> miejscaPrzestojowMaszyna1 = new ArrayList<>();
        ArrayList<Integer> miejscaPrzestojowMaszyna2 = new ArrayList<>();
        for (int o = 0; o < liczbaZadan * 1000; o++) {
            miejscaPrzestojowMaszyna1.add(0);
            miejscaPrzestojowMaszyna2.add(0);
        }

        Przestoj przestoj = null;

        for (int n = 1; n <= liczbaPrzestojow; n++) {

            int jakaMaszyna = random.nextInt(2) + 1;
            outerif1:
            if (jakaMaszyna == 1) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna1) + 1);
                for (int j = przestoj.getCzasStartu(); j <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; j++) {
                    if (miejscaPrzestojowMaszyna1.get(j) == 1) {
                        n--;
                        break outerif1;
                    }
                }
                for (int k = przestoj.getCzasStartu(); k <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; k++) {
                    miejscaPrzestojowMaszyna1.set(k, 1);
                }
                sumaCzasowTrwaniaOperacjiMaszyna1 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }

            outerif2:
            if (jakaMaszyna == 2) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna2) + 1);
                for (int l = przestoj.getCzasStartu(); l <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; l++) {
                    if (miejscaPrzestojowMaszyna2.get(l) == 1) {
                        n--;
                        break outerif2;
                    }
                }
                for (int m = przestoj.getCzasStartu(); m <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; m++) {
                    miejscaPrzestojowMaszyna2.set(m, 1);
                }

                sumaCzasowTrwaniaOperacjiMaszyna2 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }
        }

        InstancjaProblemu instancjaProblemu = new InstancjaProblemu(listaNumerowInstancji.get(0), listaZadan, listaPrzestojow);


        Osobnik osobnik = new Osobnik(instancjaProblemu);
        Maszyna maszyna1 = new Maszyna();
        Maszyna maszyna2 = new Maszyna();

        for (Przestoj przestojDoDodania : instancjaProblemu.getListaPrzestojow()) {
            if (przestojDoDodania.getNrMaszyny() == 1) {
                maszyna1.add(przestojDoDodania);
            }
            if (przestojDoDodania.getNrMaszyny() == 2) {
                maszyna2.add(przestojDoDodania);
            }
        }

        osobnik.setMaszyna1(maszyna1);
        osobnik.setMaszyna2(maszyna2);

        instancjaProblemu.setOsobnikBezOperacj(osobnik);

        listaNumerowInstancji.remove(0);
        return instancjaProblemu;
    }

    public InstancjaProblemu utworzInstancjeProblemowCzasy200(int liczbaZadan) {

        ArrayList<Zadanie> listaZadan = new ArrayList<>();
        ArrayList<Przestoj> listaPrzestojow = new ArrayList<>();
        int sumaCzasowTrwaniaOperacjiMaszyna1 = 0;
        int sumaCzasowTrwaniaOperacjiMaszyna2 = 0;


        for (int i = 1; i <= liczbaZadan; i++) {
            Zadanie zadanie = generujZadanie200(i);

            zadanie.setCzasGotowosci(random.nextInt(PRZEDZIAL_CZASOW_GOTOWOSCI) + 1);

            int numerMaszynyDlaOperacji1 = random.nextInt(2) + 1;
            zadanie.getOperacja1().setNrMaszyny(numerMaszynyDlaOperacji1);
            zadanie.getOperacja2().setNrMaszyny(3 - numerMaszynyDlaOperacji1);

            if (zadanie.getOperacja1().getNrMaszyny() == 1) {
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja2().getCzasTrwania();
            } else {
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja2().getCzasTrwania();
            }

            listaZadan.add(zadanie);
        }

        int liczbaPrzestojow = (int) Math.round(liczbaZadan / DZIELNIK_N_DLA_USTALENIA_LICZBY_PRZESTOJOW);

        ArrayList<Integer> miejscaPrzestojowMaszyna1 = new ArrayList<>();
        ArrayList<Integer> miejscaPrzestojowMaszyna2 = new ArrayList<>();
        for (int o = 0; o < liczbaZadan * 1000; o++) {
            miejscaPrzestojowMaszyna1.add(0);
            miejscaPrzestojowMaszyna2.add(0);
        }

        Przestoj przestoj = null;

        for (int n = 1; n <= liczbaPrzestojow; n++) {

            int jakaMaszyna = random.nextInt(2) + 1;
            outerif1:
            if (jakaMaszyna == 1) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna1) + 1);
                for (int j = przestoj.getCzasStartu(); j <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; j++) {
                    if (miejscaPrzestojowMaszyna1.get(j) == 1) {
                        n--;
                        break outerif1;
                    }
                }
                for (int k = przestoj.getCzasStartu(); k <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; k++) {
                    miejscaPrzestojowMaszyna1.set(k, 1);
                }
                sumaCzasowTrwaniaOperacjiMaszyna1 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }

            outerif2:
            if (jakaMaszyna == 2) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna2) + 1);
                for (int l = przestoj.getCzasStartu(); l <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; l++) {
                    if (miejscaPrzestojowMaszyna2.get(l) == 1) {
                        n--;
                        break outerif2;
                    }
                }
                for (int m = przestoj.getCzasStartu(); m <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; m++) {
                    miejscaPrzestojowMaszyna2.set(m, 1);
                }

                sumaCzasowTrwaniaOperacjiMaszyna2 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }
        }

        InstancjaProblemu instancjaProblemu = new InstancjaProblemu(listaNumerowInstancji.get(0), listaZadan, listaPrzestojow);
        listaNumerowInstancji.remove(0);

        Osobnik osobnik = new Osobnik(instancjaProblemu);
        Maszyna maszyna1 = new Maszyna();
        Maszyna maszyna2 = new Maszyna();

        for (Przestoj przestojDoDodania : instancjaProblemu.getListaPrzestojow()) {
            if (przestojDoDodania.getNrMaszyny() == 1) {
                maszyna1.add(przestojDoDodania);
            }
            if (przestojDoDodania.getNrMaszyny() == 2) {
                maszyna2.add(przestojDoDodania);
            }
        }

        osobnik.setMaszyna1(maszyna1);
        osobnik.setMaszyna2(maszyna2);

        instancjaProblemu.setOsobnikBezOperacj(osobnik);
        return instancjaProblemu;
    }

    public InstancjaProblemu utworzInstancjeProblemowCzasyMieszane(int liczbaZadan) {

        ArrayList<Zadanie> listaZadan = new ArrayList<>();
        ArrayList<Przestoj> listaPrzestojow = new ArrayList<>();
        int sumaCzasowTrwaniaOperacjiMaszyna1 = 0;
        int sumaCzasowTrwaniaOperacjiMaszyna2 = 0;


        for (int i = 1; i <= liczbaZadan; i++) {
            Zadanie zadanie = generujZadanieMieszane(i);

            zadanie.setCzasGotowosci(random.nextInt(PRZEDZIAL_CZASOW_GOTOWOSCI) + 1);

            int numerMaszynyDlaOperacji1 = random.nextInt(2) + 1;
            zadanie.getOperacja1().setNrMaszyny(numerMaszynyDlaOperacji1);
            zadanie.getOperacja2().setNrMaszyny(3 - numerMaszynyDlaOperacji1);

            if (zadanie.getOperacja1().getNrMaszyny() == 1) {
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja2().getCzasTrwania();
            } else {
                sumaCzasowTrwaniaOperacjiMaszyna2 += zadanie.getOperacja1().getCzasTrwania();
                sumaCzasowTrwaniaOperacjiMaszyna1 += zadanie.getOperacja2().getCzasTrwania();
            }

            listaZadan.add(zadanie);
        }

        int liczbaPrzestojow = (int) Math.round(liczbaZadan / DZIELNIK_N_DLA_USTALENIA_LICZBY_PRZESTOJOW);

        ArrayList<Integer> miejscaPrzestojowMaszyna1 = new ArrayList<>();
        ArrayList<Integer> miejscaPrzestojowMaszyna2 = new ArrayList<>();
        for (int o = 0; o < liczbaZadan * 300; o++) {
            miejscaPrzestojowMaszyna1.add(0);
            miejscaPrzestojowMaszyna2.add(0);
        }

        Przestoj przestoj = null;

        for (int n = 1; n <= liczbaPrzestojow; n++) {

            int jakaMaszyna = random.nextInt(2) + 1;
            outerif1:
            if (jakaMaszyna == 1) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna1) + 1);
                for (int j = przestoj.getCzasStartu(); j <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; j++) {
                    if (miejscaPrzestojowMaszyna1.get(j) == 1) {
                        n--;
                        break outerif1;
                    }
                }
                for (int k = przestoj.getCzasStartu(); k <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; k++) {
                    miejscaPrzestojowMaszyna1.set(k, 1);
                }
                sumaCzasowTrwaniaOperacjiMaszyna1 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }

            outerif2:
            if (jakaMaszyna == 2) {
                przestoj = new Przestoj(jakaMaszyna, n, random.nextInt(20) + 1, random.nextInt(sumaCzasowTrwaniaOperacjiMaszyna2) + 1);
                for (int l = przestoj.getCzasStartu(); l <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; l++) {
                    if (miejscaPrzestojowMaszyna2.get(l) == 1) {
                        n--;
                        break outerif2;
                    }
                }
                for (int m = przestoj.getCzasStartu(); m <= przestoj.getCzasTrwania() + przestoj.getCzasStartu() - 1; m++) {
                    miejscaPrzestojowMaszyna2.set(m, 1);
                }

                sumaCzasowTrwaniaOperacjiMaszyna2 += przestoj.getCzasTrwania();
                listaPrzestojow.add(przestoj);
            }
        }

        InstancjaProblemu instancjaProblemu = new InstancjaProblemu(listaNumerowInstancji.get(0), listaZadan, listaPrzestojow);
        listaNumerowInstancji.remove(0);

        Osobnik osobnik = new Osobnik(instancjaProblemu);
        Maszyna maszyna1 = new Maszyna();
        Maszyna maszyna2 = new Maszyna();

        for (Przestoj przestojDoDodania : instancjaProblemu.getListaPrzestojow()) {
            if (przestojDoDodania.getNrMaszyny() == 1) {
                maszyna1.add(przestojDoDodania);
            }
            if (przestojDoDodania.getNrMaszyny() == 2) {
                maszyna2.add(przestojDoDodania);
            }
        }

        osobnik.setMaszyna1(maszyna1);
        osobnik.setMaszyna2(maszyna2);

        instancjaProblemu.setOsobnikBezOperacj(osobnik);

        return instancjaProblemu;
    }


    public Zadanie generujZadanieMieszane(int numerZadania) {
        Operacja operacja1 = generujOperacjeMieszane();
        Operacja operacja2 = generujOperacjeMieszane();
        Zadanie zadanie = new Zadanie(numerZadania, operacja1, operacja2);
        operacja1.setZadanie(zadanie);
        operacja2.setZadanie(zadanie);

        return zadanie;
    }

    public Zadanie generujZadanie20(int numerZadania) {
        Operacja operacja1 = generujOperacje20();
        Operacja operacja2 = generujOperacje20();
        Zadanie zadanie = new Zadanie(numerZadania, operacja1, operacja2);
        operacja1.setZadanie(zadanie);
        operacja2.setZadanie(zadanie);

        return zadanie;
    }

    public Zadanie generujZadanie100(int numerZadania) {
        Operacja operacja1 = generujOperacje100();
        Operacja operacja2 = generujOperacje100();
        Zadanie zadanie = new Zadanie(numerZadania, operacja1, operacja2);
        operacja1.setZadanie(zadanie);
        operacja2.setZadanie(zadanie);

        return zadanie;
    }

    public Zadanie generujZadanie200(int numerZadania) {
        Operacja operacja1 = generujOperacje200();
        Operacja operacja2 = generujOperacje200();
        Zadanie zadanie = new Zadanie(numerZadania, operacja1, operacja2);
        operacja1.setZadanie(zadanie);
        operacja2.setZadanie(zadanie);

        return zadanie;
    }

    public Operacja generujOperacjeMieszane() {
        Operacja operacja = null;
        int liczbalosowa1lub2 = random.nextInt(2) + 1;
        if (liczbalosowa1lub2 == 1) operacja = new Operacja(random.nextInt(20) + 1);
        if (liczbalosowa1lub2 == 2) operacja = new Operacja(random.nextInt(200) + 1);

        return operacja;
    }

    public Operacja generujOperacje20() {
        Operacja operacja = new Operacja(random.nextInt(20) + 1);
        return operacja;
    }

    public Operacja generujOperacje200() {
        Operacja operacja = new Operacja(random.nextInt(200) + 1);
        return operacja;
    }

    public Operacja generujOperacje100() {
        Operacja operacja = new Operacja(random.nextInt(100)+1);
        return operacja;
    }

}



