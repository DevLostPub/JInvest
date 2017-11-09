package com.devlost.jinvest.dao;

import com.devlost.jinvest.model.UserProfile;
import com.devlost.jinvest.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

/**
 *
 * @author lucas
 */
public class CustomerDAO {

    public ArrayList<UserProfile> getAll() {
        ArrayList<UserProfile> result;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            result = (ArrayList<UserProfile>) session.createQuery("from UserProfile").list();
            session.getTransaction().commit();
        }
        return result;
    }

    /**
     *
     * @param username
     * @return the user profile attached to the username
     */
    public UserProfile get(String username){
        UserProfile result;
        SessionFactory sf = HibernateUtil.getSessionFactory();
        try (Session session = sf.openSession()) {
            session.beginTransaction();
            String sql = String.format("from UserProfile where users_id = \'%s\'",username);
            result = (UserProfile) session.createQuery(sql).getSingleResult();
        }
        
        return result;
    }

}
