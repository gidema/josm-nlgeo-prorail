package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.AbstractModuleConfiguration;
import org.openstreetmap.josm.plugins.ods.DefaultOdsDataSource;
import org.openstreetmap.josm.plugins.ods.OdsFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestFeatureSource;
import org.openstreetmap.josm.plugins.ods.arcgis.rest.AGRestHost;
import org.openstreetmap.josm.plugins.ods.entities.EntityMapperFactory;
import org.openstreetmap.josm.plugins.ods.prorail.gt.build.ProrailEntityMapperFactory;

public class ProrailModuleConfiguration extends AbstractModuleConfiguration {

    public ProrailModuleConfiguration() {
        AGRestHost host = new AGRestHost("Geleidingssysteem_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/Geleidingssysteem_001/MapServer", 10000);
        addHost(host);
        OdsFeatureSource spoorasSource = new AGRestFeatureSource(host, "Spooras/3");
        OdsFeatureSource wisselSource = new AGRestFeatureSource(host, "Wissel/4");
        OdsFeatureSource kruisingSource = new AGRestFeatureSource(host, "Kruising/5");
        OdsFeatureSource stootjukSource = new AGRestFeatureSource(host, "Stootjuk/1");
        host = new AGRestHost("BBK_spoorobjecten_003", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten_003/MapServer", 10000);
        addHost(host);
        OdsFeatureSource perronSource = new AGRestFeatureSource(host, "Perron/17");
        OdsFeatureSource overwegPuntSource = new AGRestFeatureSource(host, "Overweg/20");
        OdsFeatureSource schermSource = new AGRestFeatureSource(host, "Scherm/10");
        OdsFeatureSource deurSource = new AGRestFeatureSource(host, "Deur/9");

        EntityMapperFactory entityMapperFactory = new ProrailEntityMapperFactory();
        addDataSource(new DefaultOdsDataSource(spoorasSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(wisselSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(kruisingSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(stootjukSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(perronSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(overwegPuntSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(schermSource, null, entityMapperFactory));
        addDataSource(new DefaultOdsDataSource(deurSource, null, entityMapperFactory));
    }
}
