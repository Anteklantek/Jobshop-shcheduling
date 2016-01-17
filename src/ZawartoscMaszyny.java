/**
 * Created by Antek on 2016-01-13.
 */
public class ZawartoscMaszyny {
    int czasStartu;
    int czasTrwania;
    int ostatniZajmowanyKwantCzasu;

    public ZawartoscMaszyny(int czasStartu, int czasTrwania) {
        this.czasStartu = czasStartu;
        this.czasTrwania = czasTrwania;
    }
    public ZawartoscMaszyny(int czasTrwania){
        this.czasTrwania = czasTrwania;
    }

    public int getCzasStartu() {
        return czasStartu;
    }

    public void setCzasStartu(int czasStartu) {
        this.czasStartu = czasStartu;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public int getOstatniZajmowanyKwantCzasu() {
        return ostatniZajmowanyKwantCzasu;
    }

    public void setOstatniZajmowanyKwantCzasu(int ostatniZajmowanyKwantCzasu) {
        this.ostatniZajmowanyKwantCzasu = ostatniZajmowanyKwantCzasu;
    }
}
