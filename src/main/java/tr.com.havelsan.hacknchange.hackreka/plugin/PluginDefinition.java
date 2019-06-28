package tr.com.havelsan.hacknchange.hackreka.plugin;

import tr.com.havelsan.hacknchange.hackreka.hibernate.model.Task;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.IzinTalepleriPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.JiraPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.SatinAlmaTalepleriPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.SeyahatTalepleriPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.KaliteTalepleriPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.EvrakaPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.EOnayPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.EnumIntegration;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.TFSPlugin;
import tr.com.havelsan.hacknchange.hackreka.plugin.impl.CalendarPlugin;

import java.util.List;
import java.util.Map;

public class PluginDefinition {

    private Map<String,Object> loadParameters;
    private List<PluginParam> parameters;
    private String pluginType;

    public List<Task> execute(Map<String,Object> externalData){
        IPlugin plugin = getPlugin();
        plugin.load(new PluginConfiguration(loadParameters));
        Map<String,Object> result = plugin.run(parameters,externalData);
        return (List<Task>)result.get("taskList");
    }

    public Map<String, Object> getLoadParameters() {
        return loadParameters;
    }

    public void setLoadParameters(Map<String, Object> loadParameters) {
        this.loadParameters = loadParameters;
    }

    public List<PluginParam> getParameters() {
        return parameters;
    }

    public void setParameters(List<PluginParam> parameters) {
        this.parameters = parameters;
    }

    private IPlugin getPlugin() {
        if (pluginType.equals(EnumIntegration.JIRA.getCode())){
            return new JiraPlugin();
        }else if (pluginType.equals(EnumIntegration.SATINALMA.getCode())){
            return new SatinAlmaTalepleriPlugin();
        }else if (pluginType.equals(EnumIntegration.IZIN.getCode())){
            return new IzinTalepleriPlugin();
        }else if (pluginType.equals(EnumIntegration.SEYAHAT.getCode())){
            return new SeyahatTalepleriPlugin();
        }else if (pluginType.equals(EnumIntegration.KALITE.getCode())){
            return new KaliteTalepleriPlugin();
        }else if (pluginType.equals(EnumIntegration.EVRAKA.getCode())){
            return new EvrakaPlugin();
        }else if (pluginType.equals(EnumIntegration.EONAY.getCode())){
            return new EOnayPlugin();
        }else if (pluginType.equals(EnumIntegration.TFS.getCode())){
            return new TFSPlugin();
        }else if (pluginType.equals(EnumIntegration.TAKVIM.getCode())){
            return new CalendarPlugin();
        }else{
            return null;
        }
    }

    public String getPluginType() {
        return pluginType;
    }

    public void setPluginType(String pluginType) {
        this.pluginType = pluginType;
    }
}
