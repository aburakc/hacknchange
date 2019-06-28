package tr.com.havelsan.hacknchange.hackreka.spring.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.ExportConf;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


/**
 * Configuration Export Service
 */
@Service
public class ExportConfDaoServiceImpl implements ExportConfDaoService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(ExportConf conf) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(conf);
        tx.commit();
        session.close();
    }

    @Transactional
    public void saveList(List<ExportConf> confList) {
        int i = 0;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        String hql = String.format("delete from ExportConf");
        Query query = session.createQuery(hql);
        query.executeUpdate();

        for (ExportConf conf : confList) {
            session.save(conf);
            if (++i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    @Transactional
    public void deleteList(List<ExportConf> confList) {
        int i = 0;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (ExportConf conf : confList) {
            session.delete(conf);
            if (++i % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    public List<ExportConf> list() {
        Session session = this.sessionFactory.openSession();

        Query query = session.createQuery("from ExportConf");

        List<ExportConf> confList = query.getResultList();
        session.close();
        return confList;
    }

    private String getUserCode() {
        return "tdemir";
    }

}