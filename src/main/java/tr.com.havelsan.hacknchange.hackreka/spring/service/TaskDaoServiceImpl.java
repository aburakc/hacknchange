package tr.com.havelsan.hacknchange.hackreka.spring.service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;

import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

/**
 * Task db operations
 */
@Service
public class TaskDaoServiceImpl implements TaskDaoService {

    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public void save(Task task) {
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        session.persist(task);
        tx.commit();
        session.close();
    }

    @Transactional
    public void saveList(List<Task> taskList) {
        int i = 0;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (Task task : taskList) {
            session.save(task);
            if ( ++i % 20 == 0 ) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    @Transactional
    public void deleteList(List<Task> taskList){
        if(CollectionUtils.isEmpty(taskList)){
            return;
        }

        int i = 0;
        Session session = this.sessionFactory.openSession();
        Transaction tx = session.beginTransaction();
        for (Task task : taskList) {
            session.delete(task);
            if ( ++i % 20 == 0 ) {
                session.flush();
                session.clear();
            }
        }
        tx.commit();
        session.close();
    }

    public List<Task> list(String userCode, String integrationCode) {
        String sql = "from Task where user_code = :userCode";
        Session session = this.sessionFactory.openSession();

        if(!StringUtils.isEmpty(integrationCode)){
            sql += " and integration_code = :integrationCode";
        }

        Query query = session.createQuery(sql);

        if(StringUtils.isEmpty(userCode)){
            userCode = getUserCode();
        }

        query.setParameter("userCode", userCode);

        if(!StringUtils.isEmpty(integrationCode)){
            query.setParameter("integrationCode", integrationCode);
        }

        List<Task> taskList = query.getResultList();
        session.close();
        return taskList;
    }

    private String getUserCode(){
        return "tdemir";
    }
}