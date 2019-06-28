package tr.com.havelsan.hacknchange.hackreka.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginDefinition;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginManager;
import tr.com.havelsan.hacknchange.hackreka.spring.service.TaskDaoService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * Task Controller for web test
 */
@ManagedBean
@ViewScoped
public class TaskController extends SpringBeanAutowiringSupport {

    @Autowired
    private TaskDaoService taskDaoService;

    public void saveTaskList(List<Task> taskList) {
        taskDaoService.saveList(taskList);
    }

    public List<Task> listTask(){
        return taskDaoService.list(null, null);
    }

    public void clearTestData(){
        taskDaoService.deleteList(listTask());
    }

    public void createTestData(){
        clearTestData();
        List<PluginDefinition> pluginDefinitionList = PluginManager.getAuthorizedPluginDefinitions();
        for (PluginDefinition pluginDefinition : pluginDefinitionList) {
            List<Task> taskList = pluginDefinition.execute(null);
            saveTaskList(taskList);
        }

        /*creteTask("admin", 5, EnumIntegration.SAP);
        creteTask("admin", 15, EnumIntegration.EMAIL);
        creteTask("admin", 25, EnumIntegration.JIRA);
        creteTask("user1", 3, EnumIntegration.SAP);
        creteTask("user1", 13, EnumIntegration.EMAIL);
        creteTask("user1", 23, EnumIntegration.JIRA);
        creteTask("user2", 6, EnumIntegration.SAP);
        creteTask("user2", 16, EnumIntegration.EMAIL);
        creteTask("user2", 26, EnumIntegration.JIRA);*/
    }



    public void setTaskDaoService(TaskDaoService taskDaoService) {
        this.taskDaoService = taskDaoService;
    }

}
