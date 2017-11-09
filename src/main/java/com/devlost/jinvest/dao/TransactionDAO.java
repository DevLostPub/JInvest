package com.devlost.jinvest.dao;

import com.devlost.jinvest.model.Transaction;
import com.devlost.jinvest.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Calendar;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

/**
 *
 * @author lucas
 */
public class TransactionDAO {

    public void insert(Transaction transaction) {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        
        Calendar c = Calendar.getInstance();
        c.setTime(transaction.getSubmitDate());
        Integer settlePeriod = transaction.getFund().getSettlePeriod();
        c.add(Calendar.DATE, settlePeriod);
        transaction.setSettlementDate(c.getTime());
        
        session.beginTransaction();
        session.save(transaction);
        session.getTransaction().commit();
        session.close();
    }

    public ArrayList<Transaction> getAll() {
        SessionFactory sf = HibernateUtil.getSessionFactory();
        Session session = sf.openSession();
        session.beginTransaction();
        ArrayList<Transaction> result = (ArrayList<Transaction>) session.createQuery("from Transaction").list();
        session.getTransaction().commit();
        session.close();
        return  result;
    }
}
