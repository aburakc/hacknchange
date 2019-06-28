package tr.com.havelsan.hacknchange.hackreka.plugin;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.MockData;

import java.util.HashMap;
import java.util.List;

public class PluginManager {

    public static List<PluginDefinition> getAuthorizedPluginDefinitions(){
        return MockData.getPluginDefinitions();
    }

    public static List<Task> executePlugin(PluginDefinition plugin,HashMap<String,Object> externalData){
        //Dış servis parametrelerini dışardan(ekrandan, iç sistemden) alabilmek için externalData kullanılabilir
        return plugin.execute(externalData);
    }

}
