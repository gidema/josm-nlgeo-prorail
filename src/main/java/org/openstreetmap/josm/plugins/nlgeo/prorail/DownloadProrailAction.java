package org.openstreetmap.josm.plugins.nlgeo.prorail;

import static org.openstreetmap.josm.gui.help.HelpUtil.ht;
import static org.openstreetmap.josm.tools.I18n.tr;

import org.openstreetmap.josm.actions.downloadtasks.DownloadTask;
import org.openstreetmap.josm.plugins.openservices.CustomDownloadAction;

public class DownloadProrailAction extends CustomDownloadAction {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  private final ProrailDataType type;

  public DownloadProrailAction(ProrailDataType type) {
    super(tr("Download " + type), "download", tr("Download Prorail " + type +
        " data"), "Prorail" + type, ht("/Prorail/Download"));
    this.type = type;
  }

  @Override
  protected DownloadTask getDownloadTask() {
    return new DownloadProrailTask(type);
  }
}