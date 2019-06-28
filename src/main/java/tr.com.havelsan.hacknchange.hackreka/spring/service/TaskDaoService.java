package tr.com.havelsan.hacknchange.hackreka.spring.service;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;

import java.util.List;

public interface TaskDaoService {

    /**
     * Save tasl
     * @param task
     */
    void save(Task task);

    /**
     * Save list of tasks
     * @param taskList
     */
    void saveList(List<Task> taskList);

    /**
     * delete list of tasks
     * @param taskList
     */
    void deleteList(List<Task> taskList);

    /**
     * return tasks list as List with given usercode and integration code
     * @param userCode
     * @param integrationCode
     * @return
     */
    List<Task> list(String userCode, String integrationCode);

}
