package tr.com.havelsan.hacknchange.hackreka.jsf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import tr.com.havelsan.hacknchange.hackreka.hibernate.model.ExportConf;
import tr.com.havelsan.hacknchange.hackreka.spring.service.ExportConfDaoService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.util.List;


/**
 * Configuration controller
 */
@ManagedBean
@ViewScoped
public class ExportConfController extends SpringBeanAutowiringSupport {

    @Autowired
    private ExportConfDaoService exportConfDaoService;

    public List<ExportConf> getExportConf() {
        return exportConfDaoService.list();
    }

    public void saveUpdateData(List<ExportConf> exportConfList) {
        exportConfDaoService.deleteList(getExportConf());
        exportConfDaoService.saveList(exportConfList);
    }

    public void setExportConfDaoService(ExportConfDaoService exportConfDaoService) {
        this.exportConfDaoService = exportConfDaoService;
    }

}
