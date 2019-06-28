package tr.com.havelsan.hacknchange.hackreka.plugin.impl;

import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Summary;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.IAPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginConfiguration;
import tr.com.havelsan.hacknchange.hackreka.plugin.PluginParam;

import java.io.*;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Calendar plugin reads ical calendar for calendar events
 */
public class CalendarPlugin extends IAPlugin {
    private PluginParam[] parameters;
    private String url;
    private String user;

    public void load(PluginConfiguration pluginConfiguration) {
        user = (String) pluginConfiguration.getProperties().get("user");
        url = (String) pluginConfiguration.getProperties().get("url");
    }

    public void unLoad() {
        //no-need
    }

    public List<Task> getTasks(String url, String user) throws IOException, ParserException {
        File tempFile = File.createTempFile("cal", "cal");
        List<Task> taskList = new ArrayList<>();
        try (BufferedInputStream in = new BufferedInputStream(new URL(url).openStream());
             FileOutputStream fileOutputStream = new FileOutputStream(tempFile)) {
            byte dataBuffer[] = new byte[1024];
            int bytesRead;
            while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
                fileOutputStream.write(dataBuffer, 0, bytesRead);
            }
        } catch (IOException e) {
            // handle exception
        }

        FileInputStream fin = new FileInputStream(tempFile);

        CalendarBuilder builder = new CalendarBuilder();

        Calendar calendar = builder.build(fin);
        for(Component com : calendar.getComponents()){
            Task t = new Task();
            Summary summary = (Summary)com.getProperties().getProperty("SUMMARY");
            t.setTitle(summary.getValue());
            DtStart dtstart = (DtStart) com.getProperties().getProperty("DTSTART");
            t.setDate(dtstart.getDate());
            t.setUserCode(user);
            t.setIntegrationName(EnumIntegration.TAKVIM.name);
            t.setIntegrationCode(EnumIntegration.TAKVIM.code);
            taskList.add(t);
        }
        return taskList;
    }

    public Map<String, Object> run(List<PluginParam> parameters, Map<String, Object> dataMap) {
        Map<String, Object> result = new HashMap<>();
        try {
            result.put("taskList",
                    getTasks(getParameter("url", parameters).getValue().toString(),
                            getParameter("user", parameters).getValue().toString()));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }

        return result;
    }

    public PluginParam[] getParameters() {
        return new PluginParam[0];
    }
}
