package org.openstreetmap.josm.plugins.nlgeo.prorail;

import java.util.HashMap;
import java.util.Map;

import org.openstreetmap.josm.plugins.openservices.ObjectToJosmMapper;
import org.openstreetmap.josm.plugins.openservices.arcgis.rest.ArggisRestDataSet;


public class ProrailDataSet extends ArggisRestDataSet<ProrailObject> {
  private final Map<Class<? extends ProrailObject>, Map<Long, ProrailObject>> data = 
    new HashMap<Class<? extends ProrailObject>, Map<Long, ProrailObject>>();
//  private final Map<Long, Spoor> sporen = new HashMap<Long, Spoor>();
//  private final Map<Long, Wissel> wissels = new HashMap<Long, Wissel>();
//  private final Map<Long, Stootjuk> stootjukken = new HashMap<Long, Stootjuk>();
//  private final Map<Long, Kunstwerk> kunstwerken = new HashMap<Long, Kunstwerk>();
  private final ObjectToJosmMapper<ProrailObject> objectMapper = new ProrailToJosmMapper(this);

  public ProrailDataSet() {
    super(new ProrailJsonParser());
  }

  @Override
  public void add(ProrailObject object) {
    Class<? extends ProrailObject> clazz = object.getClass();
    Map<Long, ProrailObject> objectMap = data.get(clazz);
    if (objectMap == null) {
      objectMap = new HashMap<Long, ProrailObject>();
      data.put(clazz,  objectMap);
    }
    if (objectMap.get(object.getId()) == null) {
      objectMap.put(object.getId(), object);
      objectMapper.create(object);
    }
  }
}
