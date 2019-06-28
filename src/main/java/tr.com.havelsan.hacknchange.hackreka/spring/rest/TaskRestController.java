package tr.com.havelsan.hacknchange.hackreka.spring.rest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.ExportConf;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.spring.service.ExportConfDaoService;
import tr.com.havelsan.hacknchange.hackreka.spring.service.TaskDaoService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Rest controller
 */
@RestController
class TaskRestController {

    @Autowired
    TaskDaoService taskDaoService;

    @Autowired
    ExportConfDaoService exportConfDaoService;

    /**
     * @param userCode
     * @param integrationCode
     * @return tasklist as json
     */
    @RequestMapping(value = "/taskList", produces = "text/plain;charset=UTF-8")
    @CrossOrigin
    public String taskList(@RequestParam(value="userCode",required = false) String userCode, @RequestParam(value="integrationCode",required = false) String integrationCode ) {
        List<Task> taskList = taskDaoService.list(userCode, integrationCode);
        Gson gson = new Gson();
        return gson.toJson(taskList);
    }

    /**
     *
     * @return configuration as json
     */
    @CrossOrigin
    @RequestMapping(value = "/exportConfList",produces = "text/plain;charset=UTF-8")
    public String exportConfList() {
        List<ExportConf> confList = exportConfDaoService.list();
        Gson gson = new Gson();
        return gson.toJson(confList);
    }

    /**
     *
     * @param jsonData
     */
    @CrossOrigin
    @RequestMapping(value = "/updateExportConf",produces = "text/plain;charset=UTF-8",method = RequestMethod.POST)
    public void updateExportConf(@RequestParam(value="jsonData",required = false) String jsonData) {
        Gson gson = new Gson();
        //ExportConf[] list = gson.fromJson(jsonData,new ExportConf[]{}.getClass());
        ExportConf[] list = gson.fromJson(jsonData, ExportConf[].class);
        List<ExportConf> cList = Arrays.asList(list);
        exportConfDaoService.saveList(cList);
    }

    /**
     *
     * @param response
     * @throws IOException
     */
    @CrossOrigin
    @RequestMapping( value = "/downloadData", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public void downloadData(HttpServletResponse response) throws IOException {
        String jsonTaskList = "";
        List<ExportConf> confList = exportConfDaoService.list();
        if(!CollectionUtils.isEmpty(confList)) {
            List<Task> taskList = new ArrayList<Task>();
            for (ExportConf exportConf : confList) {
                String integrationCode = exportConf.getName();
                taskList.addAll(taskDaoService.list(null, integrationCode));
            }
            jsonTaskList = new Gson().toJson(taskList);
        }

        response.setContentType("text/plain");
        response.addHeader("Content-Disposition", "attachment; filename=DownloadData.json");

        response.getOutputStream().write(jsonTaskList.getBytes("UTF-8"));
        response.getOutputStream().flush();
    }

    @CrossOrigin
    @RequestMapping( value = "/uploadData", method = RequestMethod.POST)
    public ResponseEntity<String> uploadData(@RequestParam("file") MultipartFile file) throws Exception {
        if (file == null) {
            throw new RuntimeException("You must select the a file for uploading");
        }

        String jsonStr = new String(file.getBytes(), "UTF-8");
        List<Task> newTaskList = new Gson().fromJson(jsonStr, new TypeToken<List<Task>>(){}.getType());

        List<Task> oldTaskList = taskDaoService.list(null, null);
        taskDaoService.deleteList(oldTaskList);
        taskDaoService.saveList(newTaskList);

        return new ResponseEntity<String>(HttpStatus.OK);
    }
}
