package tr.com.havelsan.hacknchange.hackreka.plugin;

import java.util.List;

public abstract class IAPlugin implements IPlugin{

    public PluginParam getParameter(String name, List<PluginParam> parameters) {
        for (PluginParam p: parameters){
            if (p.getName().equals(name)){
                //return new PluginParam(p.getName(),p.getType(),p.getValue());
                return p;
            }
        }
        //return null;
        throw new RuntimeException(name + " parameter not found!");
    }
}
