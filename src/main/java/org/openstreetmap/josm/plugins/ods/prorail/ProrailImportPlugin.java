package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.plugins.ods.OdsModulePlugin;

public class ProrailImportPlugin extends OdsModulePlugin {
    public ProrailImportPlugin(PluginInformation info) throws Exception {
        super(info, new ProrailImportModule());
    }
}
