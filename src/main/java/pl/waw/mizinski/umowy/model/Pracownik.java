package pl.waw.mizinski.umowy.model;

import java.util.Date;
import java.util.List;

import pl.waw.mizinski.umowy.model.enums.Plec;
import pl.waw.mizinski.umowy.model.enums.TypAdresu;
import pl.waw.mizinski.umowy.model.enums.TypDokumentuTozsamosci;

public class Pracownik {

	private Long id;
	private String nazwisko;
	private String pierwszeImie;
	private String imionaPozostale;
	private Plec plec;
	private Date dataUrodzenia;
	private String miejsceUrodzenia;
	private Panstwo obywatelstwo;
	private UrzadSkarbowy urzadSkarbowy;
	private String pesel;
	private String nip;
	private String nrDokumentuTozsamosci;
	private TypDokumentuTozsamosci typDokumentuTozsamosci;
	private String nrKonta;
	private StatusPracownika status;
	private Boolean dobrowolneUbezpieczenieChorobowe;
	private List<AdresPracownika> adresy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNazwisko() {
		return nazwisko;
	}

	public void setNazwisko(String nazwisko) {
		this.nazwisko = nazwisko;
	}

	public String getPierwszeImie() {
		return pierwszeImie;
	}

	public void setPierwszeImie(String pierwszeImie) {
		this.pierwszeImie = pierwszeImie;
	}

	public String getImionaPozostale() {
		return imionaPozostale;
	}

	public void setImionaPozostale(String imionaPozostale) {
		this.imionaPozostale = imionaPozostale;
	}

	public Plec getPlec() {
		return plec;
	}
	
	public void setPlec(Plec plec) {
		this.plec = plec;
	}
	
	public Date getDataUrodzenia() {
		return dataUrodzenia;
	}

	public void setDataUrodzenia(Date dataUrodzenia) {
		this.dataUrodzenia = dataUrodzenia;
	}

	public String getMiejsceUrodzenia() {
		return miejsceUrodzenia;
	}

	public void setMiejsceUrodzenia(String miejsceUrodzenia) {
		this.miejsceUrodzenia = miejsceUrodzenia;
	}

	public Panstwo getObywatelstwo() {
		return obywatelstwo;
	}

	public void setObywatelstwo(Panstwo obywatelstwo) {
		this.obywatelstwo = obywatelstwo;
	}

	public UrzadSkarbowy getUrzadSkarbowy() {
		return urzadSkarbowy;
	}

	public void setUrzadSkarbowy(UrzadSkarbowy urzadSkarbowy) {
		this.urzadSkarbowy = urzadSkarbowy;
	}

	public String getPesel() {
		return pesel;
	}

	public void setPesel(String pesel) {
		this.pesel = pesel;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public String getNrDokumentuTozsamosci() {
		return nrDokumentuTozsamosci;
	}

	public void setNrDokumentuTozsamosci(String nrDokumentuTozsamosci) {
		this.nrDokumentuTozsamosci = nrDokumentuTozsamosci;
	}

	public TypDokumentuTozsamosci getTypDokumentuTozsamosci() {
		return typDokumentuTozsamosci;
	}

	public void setTypDokumentuTozsamosci(TypDokumentuTozsamosci typDokumentuTozsamosci) {
		this.typDokumentuTozsamosci = typDokumentuTozsamosci;
	}

	public String getNrKonta() {
		return nrKonta;
	}

	public void setNrKonta(String nrKonta) {
		this.nrKonta = nrKonta;
	}

	public StatusPracownika getStatus() {
		return status;
	}

	public void setStatus(StatusPracownika status) {
		this.status = status;
	}

	public Boolean getDobrowolneUbezpieczenieChorobowe() {
		return dobrowolneUbezpieczenieChorobowe;
	}

	public void setDobrowolneUbezpieczenieChorobowe(Boolean dobrowolneUbezpieczenieChorobowe) {
		this.dobrowolneUbezpieczenieChorobowe = dobrowolneUbezpieczenieChorobowe;
	}

	public List<AdresPracownika> getAdresy() {
		return adresy;
	}
	
	public void setAdresy(List<AdresPracownika> adresy) {
		this.adresy = adresy;
	}
	
	public AdresPracownika getAdresKorespondencyjny(){
		return findAdresByTyp(TypAdresu.korespondencyjny);
	}
	
	public AdresPracownika getAdresWCelachPodatkowych(){
		return findAdresByTyp(TypAdresu.w_celach_podatkowych);
	}
	
	public boolean hasAdresKorespondencyjny() {
		return getAdresKorespondencyjny() != null;
	}
	
	private AdresPracownika findAdresByTyp(TypAdresu typAdresu) {
		for (AdresPracownika adres : adresy) {
			if (adres.getTypAdresu() == typAdresu) {
				return adres;
			}
		}
		return null;
	}

	@Override
	public String toString() {
		return nazwisko + " " + pierwszeImie;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pracownik other = (Pracownik) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
