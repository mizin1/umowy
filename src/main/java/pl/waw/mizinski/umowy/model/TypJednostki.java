package pl.waw.mizinski.umowy.model;

import java.util.List;

public class TypJednostki {
	
	private String nazwa;
	private TypJednostki typNadrzedny;
	private List<JednostkaOrganizacyjna> jednostki;
	
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

	public List<JednostkaOrganizacyjna> getJednostki() {
		return jednostki;
	}
	
	public void setJednostki(List<JednostkaOrganizacyjna> jednostki) {
		this.jednostki = jednostki;
	}
	
//	public Iterable<TypJednostki> getTypyNadrzedne(){ 
//		
//		return new Iterable<TypJednostki>() {
//	
//			@Override
//			public Iterator<TypJednostki> iterator() {
//				return new Iterator<TypJednostki>() {
//					
//					private TypJednostki next = getTypNadrzedny();
//					
//					@Override
//					public void remove() {
//						throw new UnsupportedOperationException();
//					}
//					
//					@Override
//					public TypJednostki next() {
//						TypJednostki ret = next;
//						next = next.getTypNadrzedny();
//						return ret;
//					}
//					
//					@Override
//					public boolean hasNext() {
//						return next != null;
//					}
//				};
//			}
//		};
//	}
	
	@Override
	public String toString() {
		return nazwa;
	}
}
