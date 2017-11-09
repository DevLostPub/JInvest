package com.devlost.jinvest.dao;

import com.devlost.jinvest.model.Fund;
import com.devlost.jinvest.model.Risk;
import com.devlost.jinvest.util.HibernateUtil;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author lucas
 */
public class FundDAO {

    private static final Logger LOG = Logger.getLogger(FundDAO.class.getName());

    public ArrayList<Fund> getAll() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        ArrayList<Fund> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = (ArrayList<Fund>) session.createQuery("from Fund").list();
            //String risk = result.get(1).getRisk().getDescription();
            session.getTransaction().commit();
        }

        return result;
    }

    public Fund get(Long selectedId) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Fund fund;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            String sql = String.format("from Fund where id = %s", selectedId);
            LOG.info("ID: " + selectedId + " - sql: " + sql);
            fund = (Fund) session.createQuery(sql).getSingleResult();
            fund.getRisk();
        }
        return fund;
    }

    public void create(Fund selectedItem) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.save(selectedItem);
            session.getTransaction().commit();
        }
    }

    public void update(Fund item) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.update(item);
            session.getTransaction().commit();
        }
    }

    public void remove(Fund item) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            session.remove(item);
            session.getTransaction().commit();
        }
    }
}
