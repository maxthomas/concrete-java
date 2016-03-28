package edu.jhu.hlt.concrete.validation.ff.entity;

import edu.jhu.hlt.concrete.EntitySet;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class EntityGroups {

  private EntityGroups() {

  }

  public static final PowerEntityGroup validate(EntitySet es) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDEntityGroup(es);
  }
}
