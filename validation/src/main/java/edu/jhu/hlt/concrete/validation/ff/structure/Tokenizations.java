package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class Tokenizations {
  private Tokenizations() {

  }

  public static final Optional<PowerTokenization> empower(final Tokenization p, Communication c)
      throws InvalidConcreteStructException {
    if (p == null)
      return Optional.empty();
    else
      return Optional.of(new NecessarilyUniqueUUIDTokenization(p, c));
  }
}
