package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;

import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;

public interface PowerEntityMentionGroup extends FlattenedMetadataWithValidUUID {
  public List<PowerEntityMention> getEntityList();
}
