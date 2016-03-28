package edu.jhu.hlt.concrete.validation.ff.entity;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class EntityMentionGroups {
  private EntityMentionGroups() {

  }

  public static final PowerEntityMentionGroup empower(EntityMentionSet ems, Communication c)
      throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDEntityMentionGroup(ems, c);
  }
}
