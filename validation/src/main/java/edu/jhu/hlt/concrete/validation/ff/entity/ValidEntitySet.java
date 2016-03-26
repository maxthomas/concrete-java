package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public interface ValidEntitySet extends FlattenedMetadataWithValidUUID {
  public List<ValidEntity> getEntityList();

  public Optional<ValidUUID> getMentionSetUUID();
}
