package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.plugins.ods.Context;
import org.openstreetmap.josm.plugins.ods.PrimitiveBuilder;
import org.openstreetmap.josm.plugins.ods.tasks.Task;

/**
 * This task creates the OSM primitives and draws them on the datalayer.
 * 
 * @author Gertjan Idema <mail@gertjanidema.nl>
 *
 */
public class CreateRailPrimitivesTask implements Task {
    private ODRailStore railStore;
    private PrimitiveBuilder<Rail> primitiveBuilder;

    public CreateRailPrimitivesTask(ODRailStore railStore,
            PrimitiveBuilder<Rail> primitiveBuilder) {
        super();
        this.railStore = railStore;
        this.primitiveBuilder = primitiveBuilder;
    }


    @Override
    public void run(Context ctx) {
        for (Rail rail : railStore) {
            primitiveBuilder.createPrimitive(rail);
        }
    }

}
