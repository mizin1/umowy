package pl.waw.mizinski.umowy.model;

public class TypJednostki {
	
	private String nazwa;
	private TypJednostki typNadrzedny;
	
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public TypJednostki getTypNadrzedny() {
		return typNadrzedny;
	}

	public void setTypNadrzedny(TypJednostki typNadrzedny) {
		this.typNadrzedny = typNadrzedny;
	}

	@Override
	public String toString() {
		return nazwa;
	}
}
