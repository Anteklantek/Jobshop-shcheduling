import java.io.*;
import java.util.ArrayList;

/**
 * Created by Antek on 2016-01-12.
 */
public class PlikiTekstowe {


    public static void zapiszInstancjeDoPliku(String nazwaPliku, InstancjaProblemu instancjaProblemu) {
        File file = new File(nazwaPliku);

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);

            printWriter.println("****" + instancjaProblemu.getNumerInstancji() + "****");
            printWriter.println(instancjaProblemu.getListaZadan().size());

            for (Zadanie zadanie : instancjaProblemu.getListaZadan()) {
                printWriter.println(zadanie.getOperacja1().getCzasTrwania() + ";" + zadanie.getOperacja2().getCzasTrwania() + ";" +
                        zadanie.getOperacja1().getNrMaszyny() + ";" + zadanie.getOperacja2().getNrMaszyny() + ";" +
                        zadanie.getCzasGotowosci());
            }

            for (Przestoj przestoj : instancjaProblemu.getListaPrzestojow()) {
                printWriter.println(przestoj.getNrPrzestoju() + ";" + przestoj.getNrMaszyny() + ";" +
                        przestoj.getCzasTrwania() + ";" + przestoj.getCzasStartu());
            }

            printWriter.print("****EOF****");
            printWriter.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static InstancjaProblemu odczytajInstancjeZPliku(String nazwaPliku) throws IOException {
        FileReader fileReader = new FileReader(nazwaPliku);
        BufferedReader bufferedReader = new BufferedReader(fileReader);

        String textLine1 = bufferedReader.readLine();
        textLine1 = textLine1.substring(4, textLine1.length() - 4);
        int numerInstancji = Integer.parseInt(textLine1);

        String textline2 = bufferedReader.readLine();
        int ileZadan = Integer.parseInt(textline2);
        ArrayList<Zadanie> listaZadan = new ArrayList<>();

        for (int i = 0; i < ileZadan; i++) {
            String textLine = bufferedReader.readLine();
            String[] parts = textLine.split(";");
            int czasOp1 = Integer.parseInt(parts[0]);
            int czasOp2 = Integer.parseInt(parts[1]);
            int nrMaszynyOp1 = Integer.parseInt(parts[2]);
            int nrMaszynyOp2 = Integer.parseInt(parts[3]);
            int czasGotowosci = Integer.parseInt(parts[4]);
            Operacja op1 = new Operacja(czasOp1, nrMaszynyOp1);
            Operacja op2 = new Operacja(czasOp2, nrMaszynyOp2);
            Zadanie zadanie = new Zadanie(i + 1, op1, op2);
            op1.setZadanie(zadanie);
            op2.setZadanie(zadanie);
            zadanie.setCzasGotowosci(czasGotowosci);
            listaZadan.add(zadanie);
        }

        ArrayList<Przestoj> listaPrzestojow = new ArrayList<>();
        String nextLine = bufferedReader.readLine();

        while (!nextLine.equals("****EOF****")) {
            String[] parts = nextLine.split(";");
            int nrPrzestoju = Integer.parseInt(parts[0]);
            int nrMaszyny = Integer.parseInt(parts[1]);
            int czasTrwania = Integer.parseInt(parts[2]);
            int czasStartu = Integer.parseInt(parts[3]);

            Przestoj przestoj = new Przestoj(nrMaszyny, nrPrzestoju, czasTrwania, czasStartu);
            listaPrzestojow.add(przestoj);

            nextLine = bufferedReader.readLine();
        }

        InstancjaProblemu instancjaProblemu = new InstancjaProblemu(numerInstancji, listaZadan, listaPrzestojow);

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
        bufferedReader.close();

        return instancjaProblemu;
    }

    public static void zapiszRozwiazanieDoPlike(String nazwaPliku, Osobnik osobnik, int losowyCzasUszeregowania) {
        File file = new File(nazwaPliku);
        ArrayList<ZawartoscMaszyny> przestojeIdleM1 = new ArrayList<>();
        ArrayList<ZawartoscMaszyny> przestojeIdleM2 = new ArrayList<>();
        ArrayList<ZawartoscMaszyny> maszyna1;
        ArrayList<ZawartoscMaszyny> maszyna2;


        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            PrintWriter printWriter = new PrintWriter(file);

            printWriter.println("****" + osobnik.getInstancjaProblemu().getNumerInstancji() + "****");
            printWriter.println(osobnik.getSumaCzasowZakonczenPracy() + "," + losowyCzasUszeregowania);

            maszyna1 = osobnik.getMaszyna1().getMaszynyArrayList();
            maszyna2 = osobnik.getMaszyna2().getMaszynyArrayList();

            printWriter.print("M1: ");

            for (int i = 0; i < maszyna1.size(); i++) {
                if (maszyna1.get(i) instanceof Operacja) {
                    Operacja operacja = (Operacja) maszyna1.get(i);
                    if (operacja == operacja.getZadanie().getOperacja1()) {
                        printWriter.print("op1_" + operacja.getZadanie().getNumer() + ", " + operacja.getCzasStartu() + ", " + operacja.getCzasTrwania() + ";");
                    } else {
                        printWriter.print("op2_" + operacja.getZadanie().getNumer() + ", " + operacja.getCzasStartu() + ", " + operacja.getCzasTrwania() + ";");
                    }
                }
                if (maszyna1.get(i) instanceof Przestoj) {
                    Przestoj przestoj = (Przestoj) maszyna1.get(i);
                    printWriter.print("maint" + przestoj.getNrPrzestoju() + "_M" + przestoj.getNrMaszyny() + ", " + przestoj.getCzasStartu() + ", " + przestoj.getCzasTrwania() + ";");
                }

                if (i + 1 < maszyna1.size()) {
                    if (maszyna1.get(i).getOstatniZajmowanyKwantCzasu() + 1 != maszyna1.get(i + 1).getCzasStartu()) {
                        ZawartoscMaszyny zawartoscMaszyny = new ZawartoscMaszyny(maszyna1.get(i).getOstatniZajmowanyKwantCzasu() + 1,
                                maszyna1.get(i + 1).getCzasStartu() - maszyna1.get(i).getOstatniZajmowanyKwantCzasu() - 1);
                        przestojeIdleM1.add(zawartoscMaszyny);
                        printWriter.print("idle" + przestojeIdleM1.size() + "_M1" + ", " + zawartoscMaszyny.getCzasStartu() + ", " + zawartoscMaszyny.getCzasTrwania() + ";");
                    }
                }
            }
            printWriter.println();

            printWriter.print("M2: ");

            for (int i = 0; i < maszyna2.size(); i++) {
                if (maszyna2.get(i) instanceof Operacja) {
                    Operacja operacja = (Operacja) maszyna2.get(i);
                    if (operacja == operacja.getZadanie().getOperacja1()) {
                        printWriter.print("op1_" + operacja.getZadanie().getNumer() + ", " + operacja.getCzasStartu() + ", " + operacja.getCzasTrwania() + ";");
                    } else {
                        printWriter.print("op2_" + operacja.getZadanie().getNumer() + ", " + operacja.getCzasStartu() + ", " + operacja.getCzasTrwania() + ";");
                    }
                }
                if (maszyna2.get(i) instanceof Przestoj) {
                    Przestoj przestoj = (Przestoj) maszyna2.get(i);
                    printWriter.print("maint" + przestoj.getNrPrzestoju() + "_M" + przestoj.getNrMaszyny() + ", " + przestoj.getCzasStartu() + ", " + przestoj.getCzasTrwania() + ";");
                }

                if (i + 1 < maszyna2.size()) {
                    if ((maszyna2.get(i).getOstatniZajmowanyKwantCzasu() + 1) != maszyna2.get(i + 1).getCzasStartu()) {
                        ZawartoscMaszyny zawartoscMaszyny = new ZawartoscMaszyny(maszyna2.get(i).getOstatniZajmowanyKwantCzasu() + 1,
                                maszyna2.get(i + 1).getCzasStartu() - maszyna2.get(i).getOstatniZajmowanyKwantCzasu() - 1);
                        przestojeIdleM2.add(zawartoscMaszyny);
                        printWriter.print("idle" + przestojeIdleM2.size() + "_M2" + ", " + zawartoscMaszyny.getCzasStartu() + ", " + zawartoscMaszyny.getCzasTrwania() + ";");
                    }
                }
            }
            printWriter.println();

            int liczbaMaintM1 = 0;
            int czasMaintM1 = 0;
            int liczbaMaintM2 = 0;
            int czasMaintM2 = 0;

            for (Przestoj przestoj : osobnik.getInstancjaProblemu().getListaPrzestojow()) {
                if (przestoj.getNrMaszyny() == 1) {
                    liczbaMaintM1++;
                    czasMaintM1 += przestoj.getCzasTrwania();
                } else {
                    liczbaMaintM2++;
                    czasMaintM2 += przestoj.getCzasTrwania();
                }
            }
            printWriter.println(liczbaMaintM1 + "," + czasMaintM1);
            printWriter.println(liczbaMaintM2 + "," + czasMaintM2);


            int liczbaIdleM1 = 0;
            int czasIdleM1 = 0;
            for (ZawartoscMaszyny zawartoscMaszyny : przestojeIdleM1) {
                liczbaIdleM1++;
                czasIdleM1 += zawartoscMaszyny.getCzasTrwania();
            }
            printWriter.println(liczbaIdleM1 + "," + czasIdleM1);

            int liczbaIdleM2 = 0;
            int czasIdleM2 = 0;
            for (ZawartoscMaszyny zawartoscMaszyny : przestojeIdleM2) {
                liczbaIdleM2++;
                czasIdleM2 += zawartoscMaszyny.getCzasTrwania();
            }
            printWriter.println(liczbaIdleM2 + "," + czasIdleM2);
            printWriter.println("****EOF****");
            printWriter.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
