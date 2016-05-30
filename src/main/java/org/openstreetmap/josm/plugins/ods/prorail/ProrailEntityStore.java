package org.openstreetmap.josm.plugins.ods.prorail;

import java.util.function.Function;

import org.openstreetmap.josm.plugins.ods.entities.Entity;
import org.openstreetmap.josm.plugins.ods.entities.EntityStore;
import org.openstreetmap.josm.plugins.ods.entities.GeoIndex;
import org.openstreetmap.josm.plugins.ods.entities.Index;

/**
 * Store building entities created from geotools features.
 * This store has indexes on the referenceId and a geoIndex.
 * The primary index is on the primitive Id
 * 
 * @author Gertjan Idema <mail@gertjanidema.nl>
 *
 */
public class ProrailEntityStore<T extends Entity> extends EntityStore<T> {

    public ProrailEntityStore(Class<T> clazz, Function<T, ?> getter) {
        super(clazz);
        createUniqueIndex(getter, true);
    }

    @Override
    public Index<T> getIdIndex() {
        return getPrimaryIndex();
    }

    @Override
    public GeoIndex<T> getGeoIndex() {
        return null;
    }
}
