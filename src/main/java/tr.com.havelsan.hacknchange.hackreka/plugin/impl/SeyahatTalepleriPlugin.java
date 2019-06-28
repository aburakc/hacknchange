package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.IAPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginConfiguration;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginParam;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeyahatTalepleriPlugin extends IAPlugin {

    private PluginParam[] parameters;

    public SeyahatTalepleriPlugin() {
        PluginParam paramServiceName = new PluginParam("serviceUrl","string");
        PluginParam paramUrlParams = new PluginParam("serviceUrlParams","string");
        this.parameters = new PluginParam[]{paramServiceName,paramUrlParams};
    }

    public void load(PluginConfiguration pluginConfiguration) {
        String user = (String)pluginConfiguration.getProperties().get("user");
        String password = (String)pluginConfiguration.getProperties().get("password");
        String url = (String)pluginConfiguration.getProperties().get("url");
        String port = (String)pluginConfiguration.getProperties().get("port");
        String tn = (String)pluginConfiguration.getProperties().get("tn");
        //connect, get session
    }

    public void unLoad(){
        //logoff, disconnect
    }

    public Map<String, Object> run(List<PluginParam> parameters,Map<String,Object> dataMap) {
        Map<String,Object> result = new HashMap<String, Object>();
        //get service result from session
        List<Task> taskList = new ArrayList<Task>();
        Task task = MockData.getTaskList("tdemir", 1,EnumIntegration.SEYAHAT).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Havva YILDIRIM) Seyahat Talebi");
        task.setDescription("01.07.2019 - 05.07.2019 tarihleri arasinda Kıbrıs seyahati");
        taskList.add(task);
        task = MockData.getTaskList("tdemir", 1,EnumIntegration.SEYAHAT).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Tahir DEMİR) Seyahat Talebi");
        task.setDescription("10.07.2019 - 15.07.2019 tarihleri arasinda Dubai seyahati");
        taskList.add(task);
        task = MockData.getTaskList("tdemir", 1,EnumIntegration.SEYAHAT).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Güven KÖKDAMAR) Seyahat Talebi");
        task.setDescription("01.07.2019 - 05.07.2019 tarihleri arasinda İstanbul seyahati");
        taskList.add(task);
        result.put("taskList", taskList);
        return result;
    }

    public PluginParam[] getParameters() {
        return parameters;
    }

}
