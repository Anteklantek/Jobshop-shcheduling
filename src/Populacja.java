import java.util.*;

/**
 * Created by Antek on 2016-01-15.
 */
public class Populacja {
    ArrayList<Osobnik> listaOsobnikow;
    Osobnik osobnikZNajlepszymCzasem;

    public Populacja(ArrayList<Osobnik> listaOsobnikow) {
        this.listaOsobnikow = listaOsobnikow;
        ustawOsobnikaZNajlepszymCzasem();
    }

    public ArrayList<Osobnik> getListaOsobnikow() {
        return listaOsobnikow;
    }

    public void setListaOsobnikow(ArrayList<Osobnik> listaOsobnikow) {
        this.listaOsobnikow = listaOsobnikow;
    }

    public Osobnik getOsobnikZNajlepszymCzasem() {
        return osobnikZNajlepszymCzasem;
    }

    public void setOsobnikZNajlepszymCzasem(Osobnik osobnikZNajlepszymCzasem) {
        this.osobnikZNajlepszymCzasem = osobnikZNajlepszymCzasem;
    }


    public void selekcjaRankingowa() {
        Collections.sort(listaOsobnikow);
        ArrayList<Osobnik> osobnicyTemp = new ArrayList<>();

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 10 - i; j++)
                osobnicyTemp.add(listaOsobnikow.get(i));

        this.setListaOsobnikow(osobnicyTemp);

        ustawOsobnikaZNajlepszymCzasem();
    }

    public void selekcjaElitarna() {
        Collections.sort(listaOsobnikow);
        ArrayList<Osobnik> osobnicyTemp = new ArrayList<>();

        for (int i = 0; i < 50; i++)
            osobnicyTemp.add(listaOsobnikow.get(i));

        this.setListaOsobnikow(osobnicyTemp);

        ustawOsobnikaZNajlepszymCzasem();
    }


    public void selekcjaTurniejowa() {
        Random random = new Random();
        ArrayList<Osobnik> listaLosowanych = new ArrayList<>(listaOsobnikow);
        ArrayList<Osobnik> listaWynikowa = new ArrayList<>();
        ArrayList<Osobnik> uczestnicyTurnieju = new ArrayList<>();


        while (listaLosowanych.size() >= 2) {
            for (int i = 0; i < 2; i++) {
                int ktory = random.nextInt(listaLosowanych.size());
                uczestnicyTurnieju.add(listaLosowanych.get(ktory));
                listaLosowanych.remove(ktory);
            }
            Collections.sort(uczestnicyTurnieju);
            listaWynikowa.add(uczestnicyTurnieju.get(0));
            uczestnicyTurnieju.clear();
        }

        setListaOsobnikow(listaWynikowa);
        ustawOsobnikaZNajlepszymCzasem();

    }


    public void ustawOsobnikaZNajlepszymCzasem() {
        Collections.sort(listaOsobnikow);
        osobnikZNajlepszymCzasem = listaOsobnikow.get(0);
    }

    public int size() {
        return listaOsobnikow.size();
    }

    public Osobnik getRandom() {
        Random random = new Random();
        return listaOsobnikow.get(random.nextInt(listaOsobnikow.size()));
    }
}
