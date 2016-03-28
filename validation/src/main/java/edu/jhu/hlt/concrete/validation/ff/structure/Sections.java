package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class Sections {

  private Sections() {

  }

  public static final PowerSection empower (Section s, Communication c)
      throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDSection(s, c);
  }
}
