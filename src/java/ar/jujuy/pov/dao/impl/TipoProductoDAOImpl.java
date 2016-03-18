/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.TipoProductoDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.TipoProducto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author FAMILIA
 */
public class TipoProductoDAOImpl implements TipoProductoDAO,Serializable {

    @Override
    public List<TipoProducto> getAll() {
        List<TipoProducto> listaTiposProductos = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(TipoProducto.class);
            criteria.setMaxResults(100);
            listaTiposProductos = criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaTiposProductos;
    }

    @Override
    public TipoProducto getId(Integer idTipoProducto) {
        Session session=null;
        TipoProducto tp = null;
        try {
            session=HibernateUtil.getSessionFactory().openSession();
            tp=(TipoProducto)session.get(TipoProducto.class, idTipoProducto);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        }finally{
            if (session != null) {
                session.close();
            }
        }
        return tp;
    }

    @Override
    public void alta(TipoProducto tp) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(tp);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void modificar(TipoProducto tp) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(tp);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void eliminar(TipoProducto tp) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(tp);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }
    
    @Override
    public List<TipoProducto> filtrar(String descripcion,Boolean estado){
        List<TipoProducto> listaTiposProductos = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(TipoProducto.class);
            if (!descripcion.equals("")) {
                criteria.add(Restrictions.ilike("descripcion", "%"+descripcion+"%"));
            }
            if (estado!=null) {
                criteria.add(Restrictions.eq("estado", estado));
            }
            
            listaTiposProductos = criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return listaTiposProductos;
    }
}
