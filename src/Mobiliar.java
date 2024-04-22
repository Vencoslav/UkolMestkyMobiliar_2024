import java.time.LocalDate;
import java.util.Locale;

public class Mobiliar {
    private String popis;
    private Boolean urcenKVymene;
    private LocalDate datumInstalace;
    private Double porizovaciCena;
    private String typ;

    public Mobiliar(String popis, Boolean urcenKVymene, LocalDate datumInstalace, Double porizovaciCena, String typ) {
        this.popis = popis;
        this.urcenKVymene = urcenKVymene;
        this.datumInstalace = datumInstalace;
        this.porizovaciCena = porizovaciCena;
        this.typ = typ;
    }

    public String getPopis() {
        return popis;
    }

    public Boolean getUrcenKVymene() {
        return urcenKVymene;
    }

    public LocalDate getDatumInstalace() {
        return datumInstalace;
    }

    public Double getPorizovaciCena() {
        return porizovaciCena;
    }

    public String getTyp() {
        return typ;
    }

    public void setPopis(String popis) {
        this.popis = popis;
    }

    public void setUrcenKVymene(Boolean urcenKVymene) {
        this.urcenKVymene = urcenKVymene;
    }

    public void setDatumInstalace(LocalDate datumInstalace) {
        this.datumInstalace = datumInstalace;
    }

    public void setPorizovaciCena(Double porizovaciCena) {
        this.porizovaciCena = porizovaciCena;
    }

    public void setTyp(String typ) {
        this.typ = typ;
    }
}
