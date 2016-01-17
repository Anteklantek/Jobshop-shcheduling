/**
 * Created by Antek on 2016-01-12.
 */
public class Przestoj extends ZawartoscMaszyny{
   private int nrMaszyny;
   private int nrPrzestoju;

    public Przestoj(int nrMaszyny, int nrPrzestoju, int czasTrwaniaPrzestoju, int czasStartuPrzestoju) {
        super(czasStartuPrzestoju,czasTrwaniaPrzestoju);
        this.nrMaszyny = nrMaszyny;
        this.nrPrzestoju = nrPrzestoju;
    }

    public int getNrMaszyny() {
        return nrMaszyny;
    }

    public void setNrMaszyny(int nrMaszyny) {
        this.nrMaszyny = nrMaszyny;
    }

    public int getNrPrzestoju() {
        return nrPrzestoju;
    }

    public void setNrPrzestoju(int nrPrzestoju) {
        this.nrPrzestoju = nrPrzestoju;
    }

}
