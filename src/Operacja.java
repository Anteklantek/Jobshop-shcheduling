/**
 * Created by Antek on 2016-01-12.
 */
public class Operacja extends ZawartoscMaszyny{

    Zadanie zadanie;
    int nrMaszyny;

    public Operacja(int czasTrwania) {
        super(czasTrwania);
    }

    public Operacja(int czasTrwania, int nrMaszyny) {
        super(czasTrwania);
        this.nrMaszyny = nrMaszyny;
    }

    public void setZadanie(Zadanie zadanie) {
        this.zadanie = zadanie;
    }

    public Zadanie getZadanie() {
        return zadanie;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public int getNrMaszyny() {
        return nrMaszyny;
    }

    public void setNrMaszyny(int nrMaszyny) {
        this.nrMaszyny = nrMaszyny;
    }
}
