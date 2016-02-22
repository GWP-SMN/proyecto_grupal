package ar.jujuy.pov.dao.impl;

import ar.jujuy.pov.dao.ProveedorDAO;
import ar.jujuy.pov.hibernate.configuracion.HibernateUtil;
import ar.jujuy.pov.modelo.dominio.Proveedor;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;

public class ProveedorDAOImpl extends HibernateUtil implements ProveedorDAO,Serializable {

    @Override
    public List<Proveedor> getAll() {
        List<Proveedor> tablaProveedors = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            Criteria criteria = session.createCriteria(Proveedor.class);
            tablaProveedors = criteria.list();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
            session.getTransaction().rollback();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return tablaProveedors;
    }

    @Override
    public void alta() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void modificar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void eliminar() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
