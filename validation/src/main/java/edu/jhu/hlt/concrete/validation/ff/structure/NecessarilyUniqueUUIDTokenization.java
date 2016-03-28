package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.TokenizationKind;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.Metadata;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDTokenization extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Tokenization>
    implements PowerTokenization {

  private final Map<Integer, PowerToken> idxToTokenMap;
  private final FlattenedMetadata md;
  private final TokenizationKind type;

  private final Map<ValidUUID, PowerParse> parses;
  private final Map<ValidUUID, PowerDependencyParse> dps;
  private final Map<ValidUUID, PowerTokenTagging> tts;
  private final List<PowerSpanLink> sll;

  NecessarilyUniqueUUIDTokenization(Tokenization tkz, Communication c)
      throws InvalidConcreteStructException {
    super(tkz, tkz.getUuid());
    this.type = tkz.getKind();
    this.md = Metadata.validate(tkz.getMetadata());
    Builder<Integer, PowerToken> b = new Builder<>();
    for (Token t : tkz.getTokenList().getTokenList()) {
      PowerToken vt = Tokens.empower(t, c);
      b.put(vt.getIndex(), vt);
    }

    this.idxToTokenMap = b.build();

    this.parses = Parses.extract(tkz);
    this.dps = DependencyParses.extract(tkz);
    this.tts = TokenTaggings.extract(tkz);
    this.sll = SpanLinks.extract(tkz, c);
  }

  @Override
  public Map<Integer, PowerToken> getIndexToTokenMap() {
    return this.idxToTokenMap;
  }

  @Override
  public Map<ValidUUID, PowerParse> getIdToParseMap() {
    return this.parses;
  }

  @Override
  public Map<ValidUUID, PowerDependencyParse> getIdToDependencyParseMap() {
    return this.dps;
  }

  @Override
  public Map<ValidUUID, PowerTokenTagging> getIdToTokenTaggingMap() {
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
  public List<PowerSpanLink> getSpanLinks() {
    return this.sll;
  }
}
