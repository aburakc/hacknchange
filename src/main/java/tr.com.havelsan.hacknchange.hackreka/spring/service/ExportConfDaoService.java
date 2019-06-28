package tr.com.havelsan.hacknchange.hackreka.spring.service;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.ExportConf;

import java.util.List;

public interface ExportConfDaoService {

    void save(ExportConf conf);

    void saveList(List<ExportConf> confList);

    void deleteList(List<ExportConf> confList);

    List<ExportConf> list();

}
