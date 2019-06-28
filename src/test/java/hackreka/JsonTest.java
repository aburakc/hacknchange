package hackreka;

import com.google.gson.Gson;
import net.fortuna.ical4j.data.ParserException;
import org.junit.Test;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.CalendarPlugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JsonTest {

    @Test
    public void jsonTest() {
        Task task = new Task();
        task.setDate(new Date());
        task.setDescription("Description");
        task.setId(100L);
        task.setIntegrationCode("Int. Code");
        task.setIntegrationName("Int. Name");
        task.setLink("link");
        task.setTitle("Title");
        task.setUserCode("User Code");
        List taskList = new ArrayList();
        taskList.add(task);
        Gson gson = new Gson();
        String json = gson.toJson(taskList);
        System.out.println(json);
    }

    @Test
    public void calendarTest() {
        CalendarPlugin plugin = new CalendarPlugin();
        try {
            plugin.getTasks("https://calendar.google.com/calendar/ical/ua50ts2p1deojhbm3bdiehbn2k%40group.calendar.google.com/public/basic.ics", "user1");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserException e) {
            e.printStackTrace();
        }

    }
}
