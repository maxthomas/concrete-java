package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.TokenTagging;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class TokenTaggings {

  private TokenTaggings() {
  }

  public static final PowerTokenTagging validate(final TokenTagging tt) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDTokenTagging(tt);
  }

  public static final Map<ValidUUID, PowerTokenTagging> extract(final Tokenization tkz) throws InvalidConcreteStructException {
    final int ps = tkz.getTokenTaggingListSize();
    Builder<ValidUUID, PowerTokenTagging> b = new Builder<>();
    if (ps > 0)
      for (TokenTagging p : tkz.getTokenTaggingList()) {
        PowerTokenTagging vtt = validate(p);
        b.put(vtt.getUUID(), vtt);
      }

    return b.build();
  }
}
