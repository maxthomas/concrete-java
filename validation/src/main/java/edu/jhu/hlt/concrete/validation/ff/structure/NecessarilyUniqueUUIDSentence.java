/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Sentence;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class NecessarilyUniqueUUIDSentence extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Sentence>
    implements ValidSentence {

  private final Optional<ValidTokenization> tkz;

  public NecessarilyUniqueUUIDSentence(Sentence s) throws InvalidConcreteStructException {
    super(s, s.getUuid());
    this.tkz = s.isSetTokenization() ?
        Optional.of(Tokenizations.validate(s.getTokenization())) : Optional.empty();
  }

  @Override
  public Optional<ValidTokenization> getTokenization() {
    return this.tkz;
  }
}
