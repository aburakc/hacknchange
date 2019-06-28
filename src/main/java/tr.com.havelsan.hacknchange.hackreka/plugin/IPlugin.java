package tr.com.havelsan.hacknchange.hackreka.plugin;

import java.util.List;
import java.util.Map;

/**
 * Interface for the plugin classes.
 *
 * @author tdemir
 * @version $Id$
 */
public interface IPlugin {

    /**
     * Configure and initialize
     */
    void load(PluginConfiguration pluginConfiguration);

    /**
     * unload, close session
     */
    void unLoad();

    /**
     * Performs the plugin action.
     */
    Map<String,Object> run(List<PluginParam> parameters,Map<String,Object> dataMap);

    /**
     * Plugin parameters
     * gets required or optional parameters for connector execution
     */
    PluginParam[] getParameters();

    /**
     * get by name
     * parameter values for before run plugin
     */
    PluginParam getParameter(String name,List<PluginParam> parameters);
}
