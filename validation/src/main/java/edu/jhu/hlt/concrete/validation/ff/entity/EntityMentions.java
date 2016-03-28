package edu.jhu.hlt.concrete.validation.ff.entity;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMention;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class EntityMentions {

  private EntityMentions() {

  }

  public static final PowerEntityMention empower(final EntityMention em, Communication c)
      throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDEntityMention(em, c);
  }
}
