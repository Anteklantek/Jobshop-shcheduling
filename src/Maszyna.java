import java.util.ArrayList;

/**
 * Created by Antek on 2016-01-13.
 */
public class Maszyna {
    ArrayList<ZawartoscMaszyny> maszynyArrayList = new ArrayList();


    public void add(Przestoj przestoj) {
        int indeks = 0;
        for (int i = 0; i < maszynyArrayList.size(); i++) {
            if (maszynyArrayList.get(i) != null && przestoj.getCzasStartu() > maszynyArrayList.get(i).getCzasStartu()) {
                indeks = i + 1;
            } else {
                break;
            }
        }
        przestoj.setOstatniZajmowanyKwantCzasu(przestoj.getCzasStartu() + przestoj.getCzasTrwania() - 1);
        maszynyArrayList.add(indeks, przestoj);
    }

    public void dodajWPierwszeWolneMiejsce(Operacja operacja) {
        int miejsce = znajdzPierwszeWolneMiejsce(operacja.getZadanie().getCzasGotowosci(), operacja.getCzasTrwania());
        if (miejsce == 0) {
            operacja.setCzasStartu(operacja.getZadanie().getCzasGotowosci());
        } else {
            if (operacja.getZadanie().getCzasGotowosci() <= maszynyArrayList.get(miejsce - 1).getOstatniZajmowanyKwantCzasu() + 1) {
                operacja.setCzasStartu(maszynyArrayList.get(miejsce - 1).getOstatniZajmowanyKwantCzasu() + 1);
            } else {
                operacja.setCzasStartu(operacja.getZadanie().getCzasGotowosci());
            }
        }

        operacja.setOstatniZajmowanyKwantCzasu(operacja.getCzasStartu() + operacja.getCzasTrwania() - 1);


        if (miejsce == maszynyArrayList.size()) {
            maszynyArrayList.add(operacja);
        } else {
            maszynyArrayList.add(miejsce, operacja);
        }
    }

    public int znajdzPierwszeWolneMiejsce(int odJakiegoMomentu, int jakDlugie) {
        int i;
        if (maszynyArrayList.isEmpty()) {
            return 0;
        }
        for (i = 0; i < maszynyArrayList.size(); i++) {
            if (odJakiegoMomentu < maszynyArrayList.get(i).getCzasStartu() && dlugoscPrzerwyMiedzyItymAPoprzednimElementemAleOdCzasu(odJakiegoMomentu, i) >= jakDlugie) {
                return i;
            }
            if (i == maszynyArrayList.size() - 1) {
                return maszynyArrayList.size();
            }
        }
        return i;
    }


    public int dlugoscPrzerwyMiedzyItymAPoprzednimElementemAleOdCzasu(int odJakiegoCzasuWlacznieZTymCzasem, int indeks) {
        if (indeks == 0) {
            if (odJakiegoCzasuWlacznieZTymCzasem >= maszynyArrayList.get(0).getCzasStartu()) {
                return 0;
            } else {
                return maszynyArrayList.get(0).getCzasStartu() - odJakiegoCzasuWlacznieZTymCzasem;
            }
        }

        ZawartoscMaszyny zw = maszynyArrayList.get(indeks);
        ZawartoscMaszyny zwPoprzednie = maszynyArrayList.get(indeks - 1);

        int poczatekOresuWlacznie = zwPoprzednie.getOstatniZajmowanyKwantCzasu() + 1;
        int koniecOkresuWlacznie = zw.getCzasStartu() - 1;


        if (odJakiegoCzasuWlacznieZTymCzasem <= poczatekOresuWlacznie)
            return koniecOkresuWlacznie - poczatekOresuWlacznie + 1;
        if (odJakiegoCzasuWlacznieZTymCzasem > poczatekOresuWlacznie &&
                odJakiegoCzasuWlacznieZTymCzasem <= koniecOkresuWlacznie)
            return koniecOkresuWlacznie - odJakiegoCzasuWlacznieZTymCzasem + 1;
        if (odJakiegoCzasuWlacznieZTymCzasem > koniecOkresuWlacznie) return 0;

        return -1;
    }


    public void dodajOperacae2WPierwszeWolneMiejsceAlePoOperacj1(Operacja operacja) {
        if (operacja.equals(operacja.getZadanie().getOperacja2())) {
            int koniecOperacji1 = operacja.getZadanie().getOperacja1().getOstatniZajmowanyKwantCzasu();
            int miejsce = znajdzPierwszeWolneMiejsce(koniecOperacji1 + 1, operacja.getCzasTrwania());

            if (miejsce == 0) {
                operacja.setCzasStartu(koniecOperacji1 + 1);
            } else {
                if (koniecOperacji1 <= maszynyArrayList.get(miejsce - 1).getOstatniZajmowanyKwantCzasu())
                    operacja.setCzasStartu(maszynyArrayList.get(miejsce - 1).getOstatniZajmowanyKwantCzasu() + 1);
                if (koniecOperacji1 > maszynyArrayList.get(miejsce - 1).getOstatniZajmowanyKwantCzasu())
                    operacja.setCzasStartu(koniecOperacji1 + 1);
            }

            operacja.setOstatniZajmowanyKwantCzasu(operacja.getCzasStartu() + operacja.getCzasTrwania() - 1);


            if (miejsce == maszynyArrayList.size()) {
                maszynyArrayList.add(operacja);
            } else {
                maszynyArrayList.add(miejsce, operacja);
            }
        }
    }





    public int size() {
        return maszynyArrayList.size();
    }

    public ZawartoscMaszyny get(int indeks) {
        return maszynyArrayList.get(indeks);
    }

    public ArrayList<ZawartoscMaszyny> getMaszynyArrayList() {
        return maszynyArrayList;
    }

    public void setMaszynyArrayList(ArrayList<ZawartoscMaszyny> maszynyArrayList) {
        this.maszynyArrayList = maszynyArrayList;
    }

    public int getSumaCzasowZakonczenOperacji(){
        int sumaCzasowZakonczen=0;

        for(ZawartoscMaszyny zawartoscMaszyny : maszynyArrayList){
            if(zawartoscMaszyny instanceof Operacja)
                sumaCzasowZakonczen += ((Operacja)zawartoscMaszyny).getOstatniZajmowanyKwantCzasu();
        }



        return sumaCzasowZakonczen;
    }
}
