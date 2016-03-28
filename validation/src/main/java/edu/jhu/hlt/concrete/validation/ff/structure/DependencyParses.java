package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.DependencyParse;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class DependencyParses {

  private DependencyParses() {
  }

  public static final PowerDependencyParse validate(final DependencyParse dp)
      throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDDepParse(dp);
  }

  public static final Map<ValidUUID, PowerDependencyParse> extract(final Tokenization tkz) throws InvalidConcreteStructException {
    final int ps = tkz.getDependencyParseListSize();
    Builder<ValidUUID, PowerDependencyParse> b = new Builder<>();
    if (ps > 0)
      for (DependencyParse p : tkz.getDependencyParseList()) {
        PowerDependencyParse vp = validate(p);
        b.put(vp.getUUID(), vp);
      }

    return b.build();
  }
}
