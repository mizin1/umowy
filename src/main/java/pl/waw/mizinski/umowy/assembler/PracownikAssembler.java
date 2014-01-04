package pl.waw.mizinski.umowy.assembler;

import java.util.LinkedList;
import java.util.List;

import pl.waw.mizinski.umowy.dao.PanstwoDao;
import pl.waw.mizinski.umowy.dao.StatusPracownikaDao;
import pl.waw.mizinski.umowy.dao.UrzadSkarbowyDao;
import pl.waw.mizinski.umowy.intake.PracownikIntake;
import pl.waw.mizinski.umowy.model.AdresPracownika;
import pl.waw.mizinski.umowy.model.Panstwo;
import pl.waw.mizinski.umowy.model.Pracownik;
import pl.waw.mizinski.umowy.model.StatusPracownika;
import pl.waw.mizinski.umowy.model.UrzadSkarbowy;
import pl.waw.mizinski.umowy.model.enums.TypAdresu;

public class PracownikAssembler {

	private final PanstwoDao panstwoDao;
	private final UrzadSkarbowyDao urzadSkarbowyDao;
	private final StatusPracownikaDao statusPracownikaDao;

	public PracownikAssembler(final PanstwoDao panstwoDao, final UrzadSkarbowyDao urzadSkarbowyDao,
			final StatusPracownikaDao statusPracownikaDao) {
		super();
		this.panstwoDao = panstwoDao;
		this.urzadSkarbowyDao = urzadSkarbowyDao;
		this.statusPracownikaDao = statusPracownikaDao;
	}

	public PracownikIntake asPracownikIntake(final Pracownik pracownik) {
		final AdresPracownika adres = pracownik.getAdresWCelachPodatkowych();
		final AdresPracownika adresKorespondencyjny = pracownik.getAdresKorespondencyjny();
		PracownikIntake pracownikIntake = new PracownikIntake();
		pracownikIntake.setId(pracownik.getId());
		pracownikIntake.setNazwisko(pracownik.getNazwisko());
		pracownikIntake.setPierwszeImie(pracownik.getPierwszeImie());
		pracownikIntake.setImionaPozostale(pracownik.getImionaPozostale());
		pracownikIntake.setPlec(pracownik.getPlec());
		pracownikIntake.setDataUrodzenia(pracownik.getDataUrodzenia());
		pracownikIntake.setMiejsceUrodzenia(pracownik.getMiejsceUrodzenia());
		pracownikIntake.setObywatelstwo(pracownik.getObywatelstwo().getKod());
		if (pracownik.getUrzadSkarbowy() != null) {
			pracownikIntake.setUrzadSkarbowy(pracownik.getUrzadSkarbowy().getNazwa());
		}
		pracownikIntake.setPesel(pracownik.getPesel());
		pracownikIntake.setNip(pracownik.getNip());
		pracownikIntake.setNrDokumentuTozsamosci(pracownik.getNrDokumentuTozsamosci());
		pracownikIntake.setTypDokumentuTozsamosci(pracownik.getTypDokumentuTozsamosci());
		pracownikIntake.setNrKonta(pracownik.getNrKonta());
		pracownikIntake.setStatus(pracownik.getStatus().getNazwa());
		pracownikIntake.setDobrowolneUbezpieczenieChorobowe(pracownik.getDobrowolneUbezpieczenieChorobowe());
		if (adres != null) {
			pracownikIntake.setMiejscowosc(adres.getMiejscowosc());
			pracownikIntake.setUlica(adres.getUlica());
			pracownikIntake.setNrDomu(adres.getNrDomu());
			pracownikIntake.setNrMieszkania(adres.getNrMieszkania());
			pracownikIntake.setKodPocztowy(adres.getKodPocztowy());
			pracownikIntake.setPoczta(adres.getPoczta());
			pracownikIntake.setPanstwo(adres.getPanstwo().getKod());
		}
		if (adresKorespondencyjny != null) {
			pracownikIntake.setAdresKorespondencyjny(true);
			pracownikIntake.setAkMiejscowosc(adresKorespondencyjny.getMiejscowosc());
			pracownikIntake.setAkUlica(adresKorespondencyjny.getUlica());
			pracownikIntake.setAkNrDomu(adresKorespondencyjny.getNrDomu());
			pracownikIntake.setAkNrMieszkania(adresKorespondencyjny.getNrMieszkania());
			pracownikIntake.setAkKodPocztowy(adresKorespondencyjny.getKodPocztowy());
			pracownikIntake.setAkPoczta(adresKorespondencyjny.getPoczta());
			pracownikIntake.setAkPanstwo(adresKorespondencyjny.getPanstwo().getKod());
		} else {
			pracownikIntake.setAdresKorespondencyjny(false);
		}
		return pracownikIntake;
	}

	public Pracownik asPracownikEntity(PracownikIntake pracownikIntake) {
		return asPracownikEntity(pracownikIntake, null);
	}
	
	public Pracownik asPracownikEntity(PracownikIntake pracownikIntake, Pracownik pracownik) {
		if (pracownik == null) {
			pracownik = new Pracownik();
		}
		pracownik.setId(pracownikIntake.getId());
		pracownik.setNazwisko(pracownikIntake.getNazwisko());
		pracownik.setPierwszeImie(pracownikIntake.getPierwszeImie());
		pracownik.setImionaPozostale(pracownikIntake.getImionaPozostale());
		pracownik.setPlec(pracownikIntake.getPlec());// ?
		pracownik.setDataUrodzenia(pracownikIntake.getDataUrodzenia());
		pracownik.setMiejsceUrodzenia(pracownikIntake.getMiejsceUrodzenia());
		Panstwo obywatelstwo = panstwoDao.getById(pracownikIntake.getPanstwo());
		pracownik.setObywatelstwo(obywatelstwo);
		UrzadSkarbowy urzadSkarbowy = urzadSkarbowyDao.getById(pracownikIntake.getUrzadSkarbowy());
		pracownik.setUrzadSkarbowy(urzadSkarbowy);
		pracownik.setPesel(pracownikIntake.getPesel());
		pracownik.setNip(pracownikIntake.getNip());
		pracownik.setNrDokumentuTozsamosci(pracownikIntake.getNrDokumentuTozsamosci());
		pracownik.setTypDokumentuTozsamosci(pracownikIntake.getTypDokumentuTozsamosci());
		pracownik.setNrKonta(pracownikIntake.getNrKonta());
		StatusPracownika statusPracownika = statusPracownikaDao.getById(pracownikIntake.getStatus());
		pracownik.setStatus(statusPracownika);
		pracownik.setDobrowolneUbezpieczenieChorobowe(pracownikIntake.getDobrowolneUbezpieczenieChorobowe());
		List<AdresPracownika> adresy = new LinkedList<AdresPracownika>();
		AdresPracownika adres = createAdres(pracownikIntake, pracownik);
		adresy.add(adres);
		AdresPracownika adresKorespondencyjny = createAdresKorespondencyjny(pracownikIntake, pracownik);
		if (adresKorespondencyjny != null) {
			adresy.add(adresKorespondencyjny);
		}
		pracownik.setAdresy(adresy);
		return pracownik;
	}

	private AdresPracownika createAdres(PracownikIntake pracownikIntake, Pracownik pracownik) {
		AdresPracownika adres = new AdresPracownika();
		adres.setPracownik(pracownik);
		adres.setTypAdresu(TypAdresu.w_celach_podatkowych);
		adres.setMiejscowosc(pracownikIntake.getMiejscowosc());
		adres.setUlica(pracownikIntake.getUlica());
		adres.setNrDomu(pracownikIntake.getNrDomu());
		adres.setNrMieszkania(pracownikIntake.getNrMieszkania());
		adres.setKodPocztowy(pracownikIntake.getKodPocztowy());
		adres.setPoczta(pracownikIntake.getPoczta());
		Panstwo panstwo = panstwoDao.getById(pracownikIntake.getPanstwo());
		adres.setPanstwo(panstwo);
		return adres;
	}

	private AdresPracownika createAdresKorespondencyjny(PracownikIntake pracownikIntake, Pracownik pracownik) {
		if (pracownikIntake.isAdresKorespondencyjny()) {
			AdresPracownika adres = new AdresPracownika();
			adres.setPracownik(pracownik);
			adres.setTypAdresu(TypAdresu.korespondencyjny);
			adres.setMiejscowosc(pracownikIntake.getAkMiejscowosc());
			adres.setUlica(pracownikIntake.getAkUlica());
			adres.setNrDomu(pracownikIntake.getAkNrDomu());
			adres.setNrMieszkania(pracownikIntake.getAkNrMieszkania());
			adres.setKodPocztowy(pracownikIntake.getAkKodPocztowy());
			adres.setPoczta(pracownikIntake.getAkPoczta());
			Panstwo panstwo = panstwoDao.getById(pracownikIntake.getAkPanstwo());
			adres.setPanstwo(panstwo);
			return adres;
		} else {
			return null;
		}
	}

}
