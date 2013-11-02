package pl.waw.mizinski.umowy.model;

import java.math.BigDecimal;
import java.util.Date;

import pl.waw.mizinski.umowy.model.enums.Plec;
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
	private BigDecimal pesel;
	private BigDecimal nip;
	private String nrDokumentuTozsamosci;
	private TypDokumentuTozsamosci typDokumentuTozsamosci;
	private String nrKonta;
	private StatusPracownika status;
	private Boolean dobrowolneUbezpieczenieChorobowe;

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

	public BigDecimal getPesel() {
		return pesel;
	}

	public void setPesel(BigDecimal pesel) {
		this.pesel = pesel;
	}

	public BigDecimal getNip() {
		return nip;
	}

	public void setNip(BigDecimal nip) {
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

	@Override
	public String toString() {
		return nazwisko + " " + pierwszeImie;
	}
}
