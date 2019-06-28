package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

import com.atlassian.jira.rest.client.api.JiraRestClient;
import com.atlassian.jira.rest.client.api.JiraRestClientFactory;
import com.atlassian.jira.rest.client.api.domain.Issue;
import com.atlassian.jira.rest.client.api.domain.SearchResult;
import com.atlassian.jira.rest.client.internal.async.AsynchronousJiraRestClientFactory;
import com.atlassian.util.concurrent.Promise;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.IAPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginConfiguration;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginParam;

import java.net.URI;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * JIRA plugin for users issue
 */
public class JiraPlugin extends IAPlugin {

    private PluginParam[] parameters;
    private String JIRA_URL;
    private String JIRA_USERNAME;
    private String JIRA_PASSWORD;
    private String INT_CODE;
    private JiraRestClient client;


    public JiraPlugin() {
        //required fields for definition
        PluginParam paramServiceName = new PluginParam("project", "string");
        PluginParam paramUrlParams = new PluginParam("query", "string");
        this.parameters = new PluginParam[]{paramServiceName, paramUrlParams};
    }

    public void load(PluginConfiguration pluginConfiguration) {
        //connect, get session, initialize etc...
        JIRA_USERNAME = (String) pluginConfiguration.getProperties().get("user");
        JIRA_PASSWORD = (String) pluginConfiguration.getProperties().get("password");
        JIRA_URL = (String) pluginConfiguration.getProperties().get("url");
        INT_CODE = (String) pluginConfiguration.getProperties().get("integrationCode");
    }

    public void unLoad() {
        try {
            client.close();
        }catch (Exception e){
            //handle for client problems
            throw new RuntimeException(e);
        }
    }

    public Map<String, Object> run(List<PluginParam> parameters, Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<String, Object>();
        try {
            JiraRestClientFactory factory = new AsynchronousJiraRestClientFactory();
            System.out.println("Jira Plugin connecting...");
            client = factory.createWithBasicHttpAuthentication(new URI(JIRA_URL), JIRA_USERNAME, JIRA_PASSWORD);
            System.out.println("Jira Plugin connected");
            String project = (String) getParameter("project",parameters).getValue();
            String query = (String) getParameter("query",parameters).getValue();
            if (project!=null){
                query = ("project = " + project + " AND ") + query;
            }
            Promise<SearchResult> searchJqlPromise = client.getSearchClient().searchJql(query);

            List<Task> taskList = new ArrayList<Task>();
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            for (Issue issue : searchJqlPromise.claim().getIssues()) {
                Task task = new Task();
                task.setDate(issue.getCreationDate() == null ? null : issue.getCreationDate().toDate());
                task.setDescription((task.getDate()!=null?(df.format(task.getDate())+" - "):"")+ issue.getSummary());
                task.setId(issue.getId());
                task.setIntegrationCode(INT_CODE);
                task.setLink(JIRA_URL + "/browse/" + issue.getKey());
                task.setTitle((issue.getProject()!=null?("["+issue.getProject().getName()+"] "+(issue.getAssignee()!=null?(" ("+issue.getAssignee().getName()+") "):"")):"")+(issue.getDescription()!=null?issue.getDescription():""));
                task.setUserCode(issue.getAssignee() != null ? issue.getAssignee().getName() : null);
                taskList.add(task);
            }
            System.out.println(JIRA_USERNAME+" task count: "+taskList.size());
            result.put("taskList",taskList);
            unLoad();
        } catch (Exception e) {
            //handle for connector problems
            throw new RuntimeException(e);
        }
        return result;
    }

    public PluginParam[] getParameters() {
        return parameters;
    }

}
