package pl.waw.mizinski.umowy.model;

public class Panstwo {
	
	private String kod;
	private String nazwa;
	private String obywatelstwo;

	public String getKod() {
		return kod;
	}

	public void setKod(String kod) {
		this.kod = kod;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	public String getObywatelstwo() {
		return obywatelstwo;
	}

	public void setObywatelstwo(String obywatelstwo) {
		this.obywatelstwo = obywatelstwo;
	}

	@Override
	public String toString() {
		return nazwa;
	}
}
