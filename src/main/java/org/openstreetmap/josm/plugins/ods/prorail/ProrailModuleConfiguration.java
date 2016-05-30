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
        AGRestHost spoorObjectenHost = new AGRestHost("BBK_spoorobjecten_001", "http://mapservices.prorail.nl/ArcGIS/rest/services/BBK_spoorobjecten/MapServer", 10000);
        OdsFeatureSource spoorasSource = new AGRestFeatureSource(spoorObjectenHost, "Spooras/34");
        OdsFeatureSource wisselSource = new AGRestFeatureSource(spoorObjectenHost, "Wissel/35");
        OdsFeatureSource kruisingSource = new AGRestFeatureSource(spoorObjectenHost, "Kruising/36");
        OdsFeatureSource stootjukSource = new AGRestFeatureSource(spoorObjectenHost, "Stootjuk/32");
        OdsFeatureSource perronSource = new AGRestFeatureSource(spoorObjectenHost, "Perron/20");
        OdsFeatureSource overwegPuntSource = new AGRestFeatureSource(spoorObjectenHost, "Overweg/29");
        OdsFeatureSource schermSource = new AGRestFeatureSource(spoorObjectenHost, "Scherm/12");
        OdsFeatureSource deurSource = new AGRestFeatureSource(spoorObjectenHost, "Deur/11");

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
