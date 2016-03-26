package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.TokenizationKind;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.Metadata;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDTokenization extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Tokenization>
    implements ValidTokenization {

  private final Map<Integer, ValidToken> idxToTokenMap;
  private final FlattenedMetadata md;
  private final TokenizationKind type;

  private final Map<ValidUUID, ValidParse> parses;
  private final Map<ValidUUID, ValidDependencyParse> dps;
  private final Map<ValidUUID, ValidTokenTagging> tts;
  private final List<ValidSpanLink> sll;

  NecessarilyUniqueUUIDTokenization(Tokenization tkz) throws InvalidConcreteStructException {
    super(tkz, tkz.getUuid());
    this.type = tkz.getKind();
    this.md = Metadata.validate(tkz.getMetadata());
    Builder<Integer, ValidToken> b = new Builder<>();
    for (Token t : tkz.getTokenList().getTokenList()) {
      ValidToken vt = Tokens.validate(t);
      b.put(vt.getIndex(), vt);
    }

    this.idxToTokenMap = b.build();

    this.parses = Parses.extract(tkz);
    this.dps = DependencyParses.extract(tkz);
    this.tts = TokenTaggings.extract(tkz);
    this.sll = SpanLinks.extract(tkz);
  }

  @Override
  public Map<Integer, ValidToken> getIndexToTokenMap() {
    return this.idxToTokenMap;
  }

  @Override
  public Map<ValidUUID, ValidParse> getIdToParseMap() {
    return this.parses;
  }

  @Override
  public Map<ValidUUID, ValidDependencyParse> getIdToDependencyParseMap() {
    return this.dps;
  }

  @Override
  public Map<ValidUUID, ValidTokenTagging> getIdToTokenTaggingMap() {
    return this.tts;
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
  public TokenizationKind getType() {
    return this.type;
  }

  @Override
  public List<ValidSpanLink> getSpanLinks() {
    return this.sll;
  }
}
