package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;

import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;

public interface ValidEntityMentionSet extends FlattenedMetadataWithValidUUID {
  public List<ValidEntityMention> getEntityList();
}
