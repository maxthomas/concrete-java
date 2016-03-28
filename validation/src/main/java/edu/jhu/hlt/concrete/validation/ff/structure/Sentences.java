package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.Sentence;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class Sentences {
  private Sentences() {

  }
  public static final PowerSentence empower(Sentence st, Communication c) throws InvalidConcreteStructException {
    // TODO
    return null;
  }

  public static final Map<ValidUUID, PowerSentence> extract(Section s, Communication c)
      throws InvalidConcreteStructException {
    ImmutableMap.Builder<ValidUUID, PowerSentence> mb = new ImmutableMap.Builder<>();
    if (s.getSentenceListSize() > 0)
      for (Sentence st : s.getSentenceList()) {
        PowerSentence pst = empower(st, c);
        mb.put(pst.getUUID(), pst);
      }
    return mb.build();
  }
}
