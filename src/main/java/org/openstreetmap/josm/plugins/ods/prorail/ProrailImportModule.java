package org.openstreetmap.josm.plugins.ods.prorail;

import org.openstreetmap.josm.data.Bounds;
import org.openstreetmap.josm.plugins.ods.OdsModule;
import org.openstreetmap.josm.plugins.ods.entities.opendata.OdLayerManager;
import org.openstreetmap.josm.plugins.ods.gui.OdsDownloadAction;
import org.openstreetmap.josm.plugins.ods.gui.OdsResetAction;
import org.openstreetmap.josm.plugins.ods.io.MainDownloader;
import org.openstreetmap.josm.tools.I18n;

public class ProrailImportModule extends OdsModule {
    private final static Bounds BOUNDS = new Bounds(50.734, 3.206, 53.583, 7.245);
    private MainDownloader mainDownloader;

    public ProrailImportModule() {
        super(new ProrailModuleSetup());
    }

    @Override
    public void initialize() throws Exception {
        super.initialize();
        OdLayerManager odLayerManager = getSetup().getOdLayerManager();

        //        OsmBuildingAligner osmBuildingAligner = new OsmBuildingAligner(osmBuildingStore);
        //        OsmNeighbourFinder osmNeighbourFinder = new OsmNeighbourFinder(osmBuildingAligner, getTolerance());

        this.mainDownloader = getSetup().getMainDownloader();
        //        OdsImporter importer = new OdsImporter(osmNeighbourFinder, odLayerManager, osmLayerManager, entitiesBuilder);
        //        OdsUpdater updater = new OdsUpdater(osmLayerManager);
        mainDownloader.initialize();
        addAction(new OdsDownloadAction(odLayerManager, mainDownloader, getName()));
        //        addAction(new RemoveAssociatedStreetsAction(this));
        //        addAction(new OdsImportAction(this));
        //        addAction(new OdsUpdateAction(osmLayerManager, odLayerManager, importer, updater));
        addAction(new OdsResetAction(this));
    }

    @Override
    public String getName() {
        return "Prorail";
    }

    @Override
    public String getDescription() {
        return I18n.tr("ODS module to import rail infrastructure in the Netherlands");
    }

    @Override
    public Bounds getBounds() {
        return BOUNDS;
    }

    @Override
    public MainDownloader getDownloader() {
        return mainDownloader;
    }

    @Override
    public boolean usePolygonFile() {
        return false;
    }

    @Override
    public Double getTolerance() {
        return 1e-5;
    }
}
