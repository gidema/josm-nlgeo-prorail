package org.openstreetmap.josm.plugins.nlgeo.prorail;

import javax.swing.JMenu;

import org.openstreetmap.josm.plugins.Plugin;
import org.openstreetmap.josm.plugins.PluginInformation;
import org.openstreetmap.josm.plugins.openservices.OpenServicesPlugin;

public class ProrailPlugin extends Plugin {
  
  private JMenu prorailMenu;
//  private static Logger logger = Logger.getLogger(OpenServicesPlugin.class.getName());

  public ProrailPlugin(PluginInformation info) {
    super(info);
    addMenu();
  }

  private void addMenu() {
    JMenu mainMenu = OpenServicesPlugin.getMenu();
    prorailMenu = new JMenu("Prorail");
    mainMenu.add(prorailMenu);
    prorailMenu.add(new DownloadProrailAction(ProrailDataType.SPOOR));
    prorailMenu.add(new DownloadProrailAction(ProrailDataType.WISSEL));
    prorailMenu.add(new DownloadProrailAction(ProrailDataType.STOOTJUK));
    prorailMenu.add(new DownloadProrailAction(ProrailDataType.KUNSTWERK));
    return;
  }
}
