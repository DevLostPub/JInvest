package com.devlost.jinvest.dao;

import com.devlost.jinvest.model.Category;
import com.devlost.jinvest.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author lucas
 */
public class CategoryDAO {

    public ArrayList<Category> getAll(){
        SessionFactory sf = HibernateUtil.getSessionFactory();
        ArrayList<Category> result;
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = (ArrayList<Category>) session.createQuery("from Category").list();
        }
        
        return result;
    }
    
}
