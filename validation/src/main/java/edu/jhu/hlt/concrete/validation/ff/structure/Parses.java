package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.Parse;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class Parses {
  private Parses() {

  }

  public static final ValidParse validate(final Parse p) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDParse(p);
  }

  public static final Map<ValidUUID, ValidParse> extract(final Tokenization tkz) throws InvalidConcreteStructException {
    final int ps = tkz.getParseListSize();
    Builder<ValidUUID, ValidParse> b = new Builder<>();
    if (ps > 0)
      for (Parse p : tkz.getParseList()) {
        ValidParse vp = Parses.validate(p);
        b.put(vp.getUUID(), vp);
      }

    return b.build();
  }
}
