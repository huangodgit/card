package com.sh.dao;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.annotation.Resource;
import java.util.List;

@Getter
@Setter
public class DaoHibernate<T> implements IBaseDao<T> {

    @Resource
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    private Session getSession() {
        return this.sessionFactory.getCurrentSession();
    }

    @Override
    public int insert(T o) {
        this.getSession().save(o);
        return 1;
    }

    @Override
    public int insertList(List<T> list) {
        for (T t : list) {
            insert(t);
        }
        return list.size();
    }

    @Override
    public int update(T o) {
        this.getSession().update(o);
        return 1;
    }

    @Override
    public int deleteList(Class c, int... ids) {
        for (int id : ids) {
            delete(c, id);
        }
        return ids.length;
    }

    @Override
    public int delete(T o) {
        this.getSession().delete(o);
        return 1;
    }

    @Override
    public int delete(Class c, int id) {
        getSession().delete(getSession().load(c, id));
        return 1;
    }

    @Override
    public T findById(Class c, int id) {
        return (T) getSession().get(c, id);
    }

    @Override
    public T findOne(String hql, String[] param) {
        Query query = getSession().createQuery(hql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        return (T) query.uniqueResult();
    }

    @Override
    public List<T> find(String hql, String[] param) {
        List<T> list = null;
        Query query = getSession().createQuery(hql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        return query.list();
    }

    @Override
    public List<T> findPage(String hql, String[] param, int page, int size) {
        List<T> list = null;
        Query query = getSession().createQuery(hql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        query.setFirstResult((page - 1) * size);
        query.setMaxResults(size);
        return query.list();
    }

    @Override
    public int getCount(String hql, String[] param) {
        int res = 0;
        Query query = getSession().createQuery(hql);
        if (param != null) {
            for (int i = 0; i < param.length; i++) {
                query.setParameter(i, param[i]);
            }
        }
        res = Integer.valueOf(query.iterate().next().toString());
        return res;
    }

    @Override
    public List<T> findByFields(String hql, String[] fields, String condition) {
        List<T> list = null;
        String findhql = hql;
        if (fields != null && condition != null && fields.length > 0 && !condition.equals("")) {
            findhql = findhql + " where 1=1 and (";
            for (int i = 0; i < fields.length - 1; ++i) {
                findhql += fields[i] + " like'%" + condition + "%' or ";
            }
            findhql += fields[fields.length - 1] + " like'%" + condition + "%')";
        }
        Query query = getSession().createQuery(findhql);
        list = query.list();
        return list;
    }
}
