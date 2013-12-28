package pl.waw.mizinski.umowy.intake;

import java.util.Date;

import pl.waw.mizinski.umowy.model.enums.Plec;
import pl.waw.mizinski.umowy.model.enums.TypDokumentuTozsamosci;

public class PracownikIntake {

	//Pracownik
	private Long id;
	private String nazwisko;
	private String pierwszeImie;
	private String imionaPozostale;
	private Plec plec;
	private Date dataUrodzenia;
	private String miejsceUrodzenia;
	private String obywatelstwo;
	private String urzadSkarbowy;
	private String pesel;
	private String nip;
	private String nrDokumentuTozsamosci;
	private TypDokumentuTozsamosci typDokumentuTozsamosci;
	private String nrKonta;
	private String status;
	private Boolean dobrowolneUbezpieczenieChorobowe;
	
	//Adres
	private String miejscowosc;
	private String ulica;
	private String nrDomu;
	private String nrMieszkania;
	private String kodPocztowy;
	private String poczta;
	private String panstwo; 
	
	private boolean adresKorespondencyjny;
	
	//Adres korespondencyjny
	private String akMiejscowosc;
	private String akUlica;
	private String akNrDomu;
	private String akNrMieszkania;
	private String akKodPocztowy;
	private String akPoczta;
	private String akPanstwo;

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

	public String getObywatelstwo() {
		return obywatelstwo;
	}

	public void setObywatelstwo(String obywatelstwo) {
		this.obywatelstwo = obywatelstwo;
	}

	public String getUrzadSkarbowy() {
		return urzadSkarbowy;
	}

	public void setUrzadSkarbowy(String urzadSkarbowy) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Boolean getDobrowolneUbezpieczenieChorobowe() {
		return dobrowolneUbezpieczenieChorobowe;
	}

	public void setDobrowolneUbezpieczenieChorobowe(Boolean dobrowolneUbezpieczenieChorobowe) {
		this.dobrowolneUbezpieczenieChorobowe = dobrowolneUbezpieczenieChorobowe;
	}

	public String getMiejscowosc() {
		return miejscowosc;
	}

	public void setMiejscowosc(String miejscowosc) {
		this.miejscowosc = miejscowosc;
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getNrDomu() {
		return nrDomu;
	}

	public void setNrDomu(String nrDomu) {
		this.nrDomu = nrDomu;
	}

	public String getNrMieszkania() {
		return nrMieszkania;
	}

	public void setNrMieszkania(String nrMieszkania) {
		this.nrMieszkania = nrMieszkania;
	}

	public String getKodPocztowy() {
		return kodPocztowy;
	}

	public void setKodPocztowy(String kodPocztowy) {
		this.kodPocztowy = kodPocztowy;
	}

	public String getPoczta() {
		return poczta;
	}

	public void setPoczta(String poczta) {
		this.poczta = poczta;
	}

	public String getPanstwo() {
		return panstwo;
	}

	public void setPanstwo(String panstwo) {
		this.panstwo = panstwo;
	}

	public boolean isAdresKorespondencyjny() {
		return adresKorespondencyjny;
	}

	public void setAdresKorespondencyjny(boolean adresKorespondencyjny) {
		this.adresKorespondencyjny = adresKorespondencyjny;
	}

	public String getAkMiejscowosc() {
		return akMiejscowosc;
	}

	public void setAkMiejscowosc(String akMiejscowosc) {
		this.akMiejscowosc = akMiejscowosc;
	}

	public String getAkUlica() {
		return akUlica;
	}

	public void setAkUlica(String akUlica) {
		this.akUlica = akUlica;
	}

	public String getAkNrDomu() {
		return akNrDomu;
	}

	public void setAkNrDomu(String akNrDomu) {
		this.akNrDomu = akNrDomu;
	}

	public String getAkNrMieszkania() {
		return akNrMieszkania;
	}

	public void setAkNrMieszkania(String akNrMieszkania) {
		this.akNrMieszkania = akNrMieszkania;
	}

	public String getAkKodPocztowy() {
		return akKodPocztowy;
	}

	public void setAkKodPocztowy(String akKodPocztowy) {
		this.akKodPocztowy = akKodPocztowy;
	}

	public String getAkPoczta() {
		return akPoczta;
	}

	public void setAkPoczta(String akPoczta) {
		this.akPoczta = akPoczta;
	}

	public String getAkPanstwo() {
		return akPanstwo;
	}

	public void setAkPanstwo(String akPanstwo) {
		this.akPanstwo = akPanstwo;
	} 
}
