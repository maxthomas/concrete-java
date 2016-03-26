package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.Entity;
import edu.jhu.hlt.concrete.EntitySet;
import edu.jhu.hlt.concrete.validation.ff.AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.UUIDs;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDEntitySet extends AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<EntitySet>
    implements ValidEntitySet {

  private final List<ValidEntity> el;
  private final Optional<ValidUUID> mentionSetUUID;

  NecessarilyUniqueUUIDEntitySet(EntitySet es) throws InvalidConcreteStructException {
    super(es, es.getUuid(), es.getMetadata());

    final int els = es.getEntityListSize();
    this.el = new ArrayList<>(els);
    if (els > 0)
      for (Entity e : es.getEntityList())
        this.el.add(Entities.validate(e));

    this.mentionSetUUID = UUIDs.validate(es.getMentionSetId());
  }

  @Override
  public List<ValidEntity> getEntityList() {
    return new ArrayList<>(this.el);
  }

  @Override
  public Optional<ValidUUID> getMentionSetUUID() {
    return this.mentionSetUUID;
  }
}
