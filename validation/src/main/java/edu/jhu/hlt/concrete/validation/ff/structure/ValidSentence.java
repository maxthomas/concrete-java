package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.UUIDable;

public interface ValidSentence extends UUIDable {
  public Optional<ValidTokenization> getTokenization();
}
