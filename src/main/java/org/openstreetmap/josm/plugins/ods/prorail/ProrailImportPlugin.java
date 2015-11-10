package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.OdsModulePlugin;

public class ProrailImportPlugin extends OdsModulePlugin {
    private OdsModule module;
    
    public ProrailImportPlugin(PluginInformation info) throws Exception {
        super(info);
    }

    @Override
    public OdsModule getModule() {
        if (module == null) {
            module = new ProrailImportModule(this);
        }
        return module;
    }
}
