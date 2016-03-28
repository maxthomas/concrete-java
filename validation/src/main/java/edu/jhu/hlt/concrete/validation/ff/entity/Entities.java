package edu.jhu.hlt.concrete.validation.ff.entity;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Entity;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class Entities {
  private Entities() {

  }

  public static final PowerEntity empower(Entity e, Communication c)
      throws InvalidConcreteStructException {
    return new FailFastEntity(e, c);
  }
}
