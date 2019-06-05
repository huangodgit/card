package com.sh.dao;

import java.util.List;

public interface IBaseDao<T> {
    public int insert(T o);
    public int insertList(List<T> list);
    public int update(T o);
    public int deleteList(Class c, int... ids);
    public int delete(T o);
    public int delete(Class c, int id);
    public T findById(Class c, int id);
    public T findOne(String hql, String[] param);
    public List<T> find(String hql, String[] param);
    public List<T> findPage(String hql, String[] param, int page, int size);
    public int getCount(String hql, String[] param);
    public List<T> findByFields(String hql, String[] fields, String condition);
}
