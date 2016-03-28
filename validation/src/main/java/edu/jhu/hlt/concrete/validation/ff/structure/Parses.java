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

  public static final PowerParse validate(final Parse p) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDParse(p);
  }

  public static final Map<ValidUUID, PowerParse> extract(final Tokenization tkz) throws InvalidConcreteStructException {
    final int ps = tkz.getParseListSize();
    Builder<ValidUUID, PowerParse> b = new Builder<>();
    if (ps > 0)
      for (Parse p : tkz.getParseList()) {
        PowerParse vp = Parses.validate(p);
        b.put(vp.getUUID(), vp);
      }

    return b.build();
  }
}
