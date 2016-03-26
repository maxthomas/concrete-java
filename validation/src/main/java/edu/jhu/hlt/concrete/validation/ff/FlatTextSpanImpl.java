/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.TextSpan;

/**
 * Simple implementation of {@link FlattenedTextSpan}.
 */
public class FlatTextSpanImpl implements FlattenedTextSpan {

  private final int begin;
  private final int end;

  FlatTextSpanImpl(TextSpan annotation) throws InvalidConcreteStructException {
    this.begin = annotation.getStart();
    this.end = annotation.getEnding();

    if (this.begin < 0)
      throw new InvalidConcreteStructException("TextSpan begin must be positive or zero, not: " + this.begin);
    if (this.end < this.begin)
      throw new InvalidConcreteStructException("TextSpan end must be >= begin.");
  }

  @Override
  public int getStart() {
    return begin;
  }

  @Override
  public int getEnd() {
    return end;
  }
}
