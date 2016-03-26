/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.TextSpan;

/**
 *
 */
public class TextSpans {
  public static final Optional<FlattenedTextSpan> flatten(final TextSpan ts) throws InvalidConcreteStructException {
    if (ts == null)
      return Optional.empty();
    else
      return Optional.of(new FlatTextSpanImpl(ts));
  }

  public static final Optional<PowerTextSpan> empower(TextSpannable ts, Communication c) throws InvalidConcreteStructException {
    Optional<FlattenedTextSpan> ofts = ts.getTextSpan();
    if (ofts.isPresent())
      return Optional.of(new PowerTextSpanImpl(ofts.get(), c));
    else
      return Optional.empty();
  }

  public static final PowerTextSpan empower(FlattenedTextSpan fts, Communication c) throws InvalidConcreteStructException {
    return new PowerTextSpanImpl(fts, c);
  }
}
