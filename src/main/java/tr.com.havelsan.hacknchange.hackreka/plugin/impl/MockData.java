package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginDefinition;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginManager;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginParam;

import java.util.*;

public class MockData {

    public static List<Task> getTaskList(String userCode, int count, EnumIntegration enumIntegration) {
        List<Task> tasks = new ArrayList<Task>();
        for(int i = 0; i<count; i++) {
            Task task = new Task();
            task.setDate(new Date());
            task.setDescription("Task Desc "+ i);
            task.setTitle("Task Title "+ i);
            task.setIntegrationCode(enumIntegration.getCode());
            task.setIntegrationName(enumIntegration.getName());
            task.setLink(enumIntegration.getLink());
            task.setUserCode(userCode);
            tasks.add(task);
        }
        return tasks;
    }

    public static List<PluginDefinition> getPluginDefinitions(){
        List<PluginDefinition> list = new ArrayList<PluginDefinition>();
        //TODO:Plugin definition will query from database
        PluginDefinition pJira = new PluginDefinition();
        pJira.setPluginType(EnumIntegration.JIRA.getCode());
        List<PluginParam> params2 = new ArrayList<PluginParam>();
        params2.add(new PluginParam("query","status not in (Closed, Resolved) ORDER BY assignee, resolutiondate"));
        params2.add(new PluginParam("project","hacknchange"));
        pJira.setParameters(params2);
        Map<String,Object> prmsMap2 = new HashMap<String, Object>();
        prmsMap2.put("user","tdemir");
        byte[] pass = new byte[]{102,105,114,100,101,118,115,50,54,33};
        prmsMap2.put("password",new String(pass));
        prmsMap2.put("url","http://195.214.160.101:8080");
        prmsMap2.put("integrationCode","jira");
        pJira.setLoadParameters(prmsMap2);
        list.add(pJira);

        PluginDefinition satinAlma = new PluginDefinition();
        satinAlma.setPluginType(EnumIntegration.SATINALMA.getCode());
        List<PluginParam> params = new ArrayList<PluginParam>();
        params.add(new PluginParam("serviceUrl","test"));
        params.add(new PluginParam("serviceUrlParams","test"));
        satinAlma.setParameters(params);
        Map<String,Object> prmsMap = new HashMap<String, Object>();
        prmsMap.put("user","test");
        prmsMap.put("password","test");
        prmsMap.put("url","test");
        prmsMap.put("port","test");
        prmsMap.put("tn","test");
        satinAlma.setLoadParameters(prmsMap);
        list.add(satinAlma);

        PluginDefinition izinTalepleri = new PluginDefinition();
        izinTalepleri.setPluginType(EnumIntegration.IZIN.getCode());
        List<PluginParam> izinparams = new ArrayList<PluginParam>();
        izinparams.add(new PluginParam("serviceUrl","test"));
        izinparams.add(new PluginParam("serviceUrlParams","test"));
        izinTalepleri.setParameters(izinparams);
        Map<String,Object> izinprmsMap = new HashMap<String, Object>();
        izinprmsMap.put("user","test");
        izinprmsMap.put("password","test");
        izinprmsMap.put("url","test");
        izinprmsMap.put("port","test");
        izinprmsMap.put("tn","test");
        izinTalepleri.setLoadParameters(izinprmsMap);
        list.add(izinTalepleri);

        PluginDefinition seyahatTalepleri = new PluginDefinition();
        seyahatTalepleri.setPluginType(EnumIntegration.SEYAHAT.getCode());
        List<PluginParam> seyahatparams = new ArrayList<PluginParam>();
        seyahatparams.add(new PluginParam("serviceUrl","test"));
        seyahatparams.add(new PluginParam("serviceUrlParams","test"));
        seyahatTalepleri.setParameters(seyahatparams);
        Map<String,Object> seyahatprmsMap = new HashMap<String, Object>();
        seyahatprmsMap.put("user","test");
        seyahatprmsMap.put("password","test");
        seyahatprmsMap.put("url","test");
        seyahatprmsMap.put("port","test");
        seyahatprmsMap.put("tn","test");
        seyahatTalepleri.setLoadParameters(seyahatprmsMap);
        list.add(seyahatTalepleri);

        PluginDefinition kaliteTalepleri = new PluginDefinition();
        kaliteTalepleri.setPluginType(EnumIntegration.KALITE.getCode());
        List<PluginParam> kaliteparams = new ArrayList<PluginParam>();
        kaliteparams.add(new PluginParam("serviceUrl","test"));
        kaliteparams.add(new PluginParam("serviceUrlParams","test"));
        kaliteTalepleri.setParameters(kaliteparams);
        Map<String,Object> kaliteprmsMap = new HashMap<String, Object>();
        kaliteprmsMap.put("user","test");
        kaliteprmsMap.put("password","test");
        kaliteprmsMap.put("url","test");
        kaliteprmsMap.put("port","test");
        kaliteprmsMap.put("tn","test");
        kaliteTalepleri.setLoadParameters(kaliteprmsMap);
        list.add(kaliteTalepleri);

        PluginDefinition evrakaTalepleri = new PluginDefinition();
        evrakaTalepleri.setPluginType(EnumIntegration.EVRAKA.getCode());
        List<PluginParam> evrakaparams = new ArrayList<PluginParam>();
        evrakaparams.add(new PluginParam("serviceUrl","test"));
        evrakaparams.add(new PluginParam("serviceUrlParams","test"));
        evrakaTalepleri.setParameters(evrakaparams);
        Map<String,Object> evrakaprmsMap = new HashMap<String, Object>();
        evrakaprmsMap.put("user","test");
        evrakaprmsMap.put("password","test");
        evrakaprmsMap.put("url","test");
        evrakaprmsMap.put("port","test");
        evrakaprmsMap.put("tn","test");
        evrakaTalepleri.setLoadParameters(evrakaprmsMap);
        list.add(evrakaTalepleri);

        PluginDefinition eonayTalepleri = new PluginDefinition();
        eonayTalepleri.setPluginType(EnumIntegration.EONAY.getCode());
        List<PluginParam> eonayparams = new ArrayList<PluginParam>();
        eonayparams.add(new PluginParam("serviceUrl","test"));
        eonayparams.add(new PluginParam("serviceUrlParams","test"));
        eonayTalepleri.setParameters(eonayparams);
        Map<String,Object> eonayprmsMap = new HashMap<String, Object>();
        eonayprmsMap.put("user","test");
        eonayprmsMap.put("password","test");
        eonayprmsMap.put("url","test");
        eonayprmsMap.put("port","test");
        eonayprmsMap.put("tn","test");
        eonayTalepleri.setLoadParameters(eonayprmsMap);
        list.add(eonayTalepleri);

        PluginDefinition tfsTalepleri = new PluginDefinition();
        tfsTalepleri.setPluginType(EnumIntegration.TFS.getCode());
        List<PluginParam> tfsparams = new ArrayList<PluginParam>();
        tfsparams.add(new PluginParam("serviceUrl","test"));
        tfsparams.add(new PluginParam("serviceUrlParams","test"));
        tfsTalepleri.setParameters(tfsparams);
        Map<String,Object> tfsprmsMap = new HashMap<String, Object>();
        tfsprmsMap.put("user","test");
        tfsprmsMap.put("password","test");
        tfsprmsMap.put("url","test");
        tfsprmsMap.put("port","test");
        tfsprmsMap.put("tn","test");
        tfsTalepleri.setLoadParameters(tfsprmsMap);
        list.add(tfsTalepleri);

        PluginDefinition takvim = new PluginDefinition();
        takvim.setPluginType(EnumIntegration.TAKVIM.getCode());
        List<PluginParam> takvimparams = new ArrayList<PluginParam>();
        takvimparams.add(new PluginParam("url","https://calendar.google.com/calendar/ical/ua50ts2p1deojhbm3bdiehbn2k%40group.calendar.google.com/public/basic.ics"));
        takvimparams.add(new PluginParam("user","test"));
        takvim.setParameters(takvimparams);
        Map<String,Object> tt = new HashMap<String, Object>();
        tt.put("user","tdemir");
        tt.put("url","https://calendar.google.com/calendar/ical/ua50ts2p1deojhbm3bdiehbn2k%40group.calendar.google.com/public/basic.ics");
        takvim.setLoadParameters(tt);
        list.add(takvim);

        return list;
    }

    public static void main(String[] args) {
        System.out.println("111");
        List<PluginDefinition> ls = PluginManager.getAuthorizedPluginDefinitions();
        for (PluginDefinition d:ls){
            System.out.println(d.getClass().getName());
            List<Task> t = d.execute(null);
            System.out.println(t.size());
        }
    }
}
