package tr.com.havelsan.hacknchange.hackreka.plugin;

import java.util.Map;

public class PluginConfiguration {

    public PluginConfiguration(Map properties) {
        this.properties = properties;
    }

    private Map<String,Object> properties;

    public Map<String,Object> getProperties() {
        return properties;
    }

    public void setProperties(Map<String,Object> properties) {
        this.properties = properties;
    }
}
