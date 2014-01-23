package pl.waw.mizinski.umowy.filter;

import java.util.List;

public interface Filter<E> {
	
	List<E> applyFilter (List<E> list);

}
