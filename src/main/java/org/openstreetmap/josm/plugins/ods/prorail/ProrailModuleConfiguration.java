package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.openstreetmap.josm.plugins.ods.OdsDataSource;
import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.OdsModuleConfiguration;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestDataSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.io.Host;

import exceptions.OdsException;

public class ProrailModuleConfiguration implements OdsModuleConfiguration {
    private final AGRestHost host = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
    private final Collection<Host> hosts = Collections.singletonList(host);
    
    private final OdsFeatureSource spoorSource = new AGRestFeatureSource(host, "Spoor/36");
    private final OdsFeatureSource wisselbeenSource = new AGRestFeatureSource(host, "Wisselbeen/37");
    private final List<OdsFeatureSource> featureSources = Arrays.asList(spoorSource, wisselbeenSource);
    
    private final OdsDataSource spoorDataSource = new AGRestDataSource(spoorSource);
    private final OdsDataSource wisselbeenDataSource = new AGRestDataSource(wisselbeenSource);
    private final Map< String, OdsDataSource> dataSources = new HashMap<>();
    {
        dataSources.put("spoor", spoorDataSource);
        dataSources.put("wisselbeen", wisselbeenDataSource);
    }
    
    
    public ProrailModuleConfiguration() {
        // TODO Auto-generated constructor stub
    }
    @Override
    public Collection<Host> getHosts() {
        return hosts;
    }

    @Override
    public List<? extends OdsFeatureSource> getFeatureSources() {
        return featureSources;
    }

    @Override
    public Collection<OdsDataSource> getDataSources() {
        return dataSources.values();
    }

    @Override
    public OdsDataSource getDataSource(String name) throws OdsException {
        return dataSources.get(name);
    }

}
