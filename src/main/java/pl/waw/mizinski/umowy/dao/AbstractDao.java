package pl.waw.mizinski.umowy.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.objectledge.context.Context;
import org.objectledge.hibernate.HibernateSessionContext;

public class AbstractDao<K extends Serializable, E> {

	protected final Context context;
	private final Class<E> entityClass;

	public AbstractDao(Context context) {
		this.context = context;
		ParameterizedType genericSuperclass = (ParameterizedType) getClass().getGenericSuperclass();
		this.entityClass = (Class<E>) genericSuperclass.getActualTypeArguments()[1];
	}

	protected Session session() {
		return HibernateSessionContext.getHibernateSessionContext(context).getSession();
	}

	public Class<E> getEntityClass() {
		return entityClass;
	}

	public E getById(K id) {
		return (E) session().get(getEntityClass(), id);
	}

	public List<E> getAll() {
		Query query = session().createQuery("from " + getEntityClass().getName());
		return query.list();
	}

	public void add(E entity) {
		session().persist(entity);
	}

	public E merge(E entity) {
		return (E) session().merge(entity);
	}

	public void remove(E entity) {
		session().delete(entity);
	}

	public void remove(K id) {
		E entity = getById(id);
		remove(entity);
	}

	public void remove(Collection<E> entities) {
		for (E entity : entities) {
			remove(entity);
		}
	}

}
