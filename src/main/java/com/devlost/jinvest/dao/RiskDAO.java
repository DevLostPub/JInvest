package com.devlost.jinvest.dao;

import com.devlost.jinvest.model.Risk;
import com.devlost.jinvest.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author lucas
 */
public class RiskDAO {

    public ArrayList<Risk> getAll(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        ArrayList<Risk> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = (ArrayList<Risk>) session.createQuery("from Risk").list();
        }
        
        return result;
    }
    
}
