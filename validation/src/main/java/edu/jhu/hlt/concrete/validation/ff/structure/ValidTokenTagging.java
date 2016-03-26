package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;

public interface ValidTokenTagging extends ZeroBasedIndexable, FlattenedMetadataWithValidUUID {
  public Optional<String> getTaggingType();

  public Map<Integer, ValidTaggedToken> getIndexToTaggedTokenMap();
}
