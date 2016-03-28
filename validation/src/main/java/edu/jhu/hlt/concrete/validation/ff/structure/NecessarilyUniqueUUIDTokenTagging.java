package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.TokenTagging;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.Metadata;

public class NecessarilyUniqueUUIDTokenTagging extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<TokenTagging>
    implements PowerTokenTagging {

  private final FlattenedMetadata md;
  private final Optional<String> tagType;

  NecessarilyUniqueUUIDTokenTagging(TokenTagging tkz) throws InvalidConcreteStructException {
    super(tkz, tkz.getUuid());
    this.md = Metadata.validate(tkz.getMetadata());
    this.tagType = Optional.ofNullable(tkz.getTaggingType());
  }

  @Override
  public int getIndex() {
    return 0;
  }

  @Override
  public String getTool() {
    return this.md.getTool();
  }

  @Override
  public int getKBest() {
    return this.md.getKBest();
  }

  @Override
  public long getTimestamp() {
    return this.md.getTimestamp();
  }

  @Override
  public Optional<String> getTaggingType() {
    return this.tagType;
  }

  @Override
  public Map<Integer, PowerTaggedToken> getIndexToTaggedTokenMap() {
    //TODO:
    return null;
  }
}
