package pl.waw.mizinski.umowy.filter;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractFilter<E> implements Filter<E>{

	private List<String> expressions = new LinkedList<String>();
	
	protected AbstractFilter (final String filterString){
		for (String expression : filterString.trim().split("\\s") ){
			if (!expression.isEmpty()) {
				expressions.add(expression);
			}
		}
	}
	
	protected abstract boolean matches(E e, String expression);
	
	public final List<E> applyFilter (List<E> list) {
		for(String expression : expressions) {
			list = applyExpression(list, expression);
		}
		return (List<E>) list;
	}
	
	private List<E> applyExpression(List<E> list, String expression) {
		if (expression.isEmpty()) {
			return list;
		} 
		List<E> ret = new LinkedList<E>();
		for (E e : list ){
			if(matches(e, expression)){
				ret.add(e);
			}
		}
		return ret;
	}
	
	protected static boolean matches(String expression, String string){
		if (string == null) {
			return false;
		}
		string = string.toLowerCase();
		expression = expression.toLowerCase().trim();
		return string.contains(expression);
	}
	
	protected static boolean matches(String expression, String ...strings) {
		for (String string : strings){
			if(matches(expression, string)) {
				return true;
			}
		}
		return false;
	}
}
