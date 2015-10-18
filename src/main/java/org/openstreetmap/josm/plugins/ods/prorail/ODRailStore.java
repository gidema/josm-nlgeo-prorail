package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.entities.EntityStore;
import org.openstreetmap.josm.plugins.ods.entities.GeoIndex;
import org.openstreetmap.josm.plugins.ods.entities.Index;
import org.openstreetmap.josm.plugins.ods.entities.UniqueIndexImpl;

/**
 * Store building entities created from geotools features.
 * This store has indexes on the referenceId and a geoIndex.
 * The primary index is on the primitive Id
 * 
 * @author Gertjan Idema <mail@gertjanidema.nl>
 *
 */
public class ODRailStore extends EntityStore<Rail> {
    private UniqueIndexImpl<Rail> idIndex = new UniqueIndexImpl<>(Rail.class, "referenceId");

    public ODRailStore() {
        super();
        getIndexes().add(idIndex);
    }


    @Override
    public Index<Rail> getIdIndex() {
        return idIndex;
    }


    @Override
    public UniqueIndexImpl<Rail> getPrimaryIndex() {
        return idIndex;
    }


    @Override
    public GeoIndex<Rail> getGeoIndex() {
        return null;
    }
}
