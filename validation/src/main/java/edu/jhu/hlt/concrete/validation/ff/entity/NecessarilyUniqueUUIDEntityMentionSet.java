package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.ArrayList;
import java.util.List;

import edu.jhu.hlt.concrete.EntityMention;
import edu.jhu.hlt.concrete.EntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class NecessarilyUniqueUUIDEntityMentionSet extends AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<EntityMentionSet>
    implements ValidEntityMentionSet {

  private final List<ValidEntityMention> ml;

  NecessarilyUniqueUUIDEntityMentionSet(EntityMentionSet ems) throws InvalidConcreteStructException {
    super(ems, ems.getUuid(), ems.getMetadata());

    final int mls = ems.getMentionListSize();
    this.ml = new ArrayList<>(mls);
    if (mls > 0)
      for (EntityMention em : ems.getMentionList())
        this.ml.add(EntityMentions.validate(em));
  }

  @Override
  public List<ValidEntityMention> getEntityList() {
    return new ArrayList<>(this.ml);
  }
}
