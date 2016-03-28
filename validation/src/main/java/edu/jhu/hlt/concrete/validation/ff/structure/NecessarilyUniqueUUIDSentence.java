/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Sentence;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;

public class NecessarilyUniqueUUIDSentence extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Sentence>
    implements PowerSentence {

  private final Optional<PowerTokenization> tkz;
  private final Optional<PowerTextSpan> opts;

  public NecessarilyUniqueUUIDSentence(Sentence s, Communication c) throws InvalidConcreteStructException {
    super(s, s.getUuid());

    this.tkz = Tokenizations.empower(s.getTokenization(), c);
    this.opts = TextSpans.empower(s.getTextSpan(), c);
  }

  @Override
  public Optional<PowerTextSpan> getTextSpan() {
    return this.opts;
  }

  @Override
  public Optional<PowerTokenization> getPowerTokenization() {
    return this.tkz;
  }
}
