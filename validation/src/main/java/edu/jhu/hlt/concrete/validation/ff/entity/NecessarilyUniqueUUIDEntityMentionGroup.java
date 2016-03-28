package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.ArrayList;
import java.util.List;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMention;
import edu.jhu.hlt.concrete.EntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class NecessarilyUniqueUUIDEntityMentionGroup extends AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<EntityMentionSet>
    implements PowerEntityMentionGroup {

  private final List<PowerEntityMention> ml;

  NecessarilyUniqueUUIDEntityMentionGroup(EntityMentionSet ems, Communication c)
      throws InvalidConcreteStructException {
    super(ems, ems.getUuid(), ems.getMetadata());

    final int mls = ems.getMentionListSize();
    this.ml = new ArrayList<>(mls);
    if (mls > 0)
      for (EntityMention em : ems.getMentionList())
        this.ml.add(EntityMentions.empower(em, c));
  }

  @Override
  public List<PowerEntityMention> getEntityList() {
    return new ArrayList<>(this.ml);
  }
}
