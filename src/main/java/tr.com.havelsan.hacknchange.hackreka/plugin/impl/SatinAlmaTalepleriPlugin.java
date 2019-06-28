package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.IAPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginConfiguration;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginParam;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SatinAlmaTalepleriPlugin extends IAPlugin {

    private PluginParam[] parameters;

    public SatinAlmaTalepleriPlugin() {
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
        //taskList.addAll(MockData.getTaskList("user1", 20,EnumIntegration.SATINALMA));
        //taskList.addAll(MockData.getTaskList("user2", 20,EnumIntegration.SATINALMA));
        //taskList.addAll(MockData.getTaskList("user3", 20,EnumIntegration.SATINALMA));
        //taskList.addAll(MockData.getTaskList("user4", 20,EnumIntegration.SATINALMA));
        //taskList.addAll(MockData.getTaskList("user5", 20,EnumIntegration.SATINALMA));

        DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        Task task = MockData.getTaskList("tdemir", 1,EnumIntegration.SATINALMA).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Havva YILDIRIM) Sunucu Alımı");
        task.setDescription("Tarih : "+ df.format(task.getDate())+" - Sunucu alımı ile ilgili onay işlemi");
        taskList.add(task);
        task = MockData.getTaskList("tdemir", 1,EnumIntegration.SATINALMA).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Tahir DEMİR) A4 Kağıt Alımı");
        task.setDescription("Tarih : "+df.format(task.getDate())+" - A4 kağıt alımı ile ilgili onay işlemi");
        taskList.add(task);
        task = MockData.getTaskList("tdemir", 1,EnumIntegration.SATINALMA).get(0);
        task.setTitle("[Bilgi İletişim Teknolojileri] (Güven KÖKDAMAR) Tuvalet Kağıdı Alımı");
        task.setDescription("Tarih : "+df.format(task.getDate())+" - Tuvalet kağıdı alımı ile ilgili onay işlemi");
        taskList.add(task);
        result.put("taskList", taskList);
        return result;
    }

    public PluginParam[] getParameters() {
        return parameters;
    }

}
