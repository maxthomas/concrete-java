/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;

/**
 * Simple implementation of {@link ValidToken}. {@link Optional} fields
 * correspond to thrift optional fields.
 */
public class ValidTokenImpl implements ValidToken {

  private final Optional<FlattenedTextSpan> ts;
  private final int idx;
  private final Optional<String> ttext;

  /**
   * @param t a {@link Token}
   * @throws InvalidConcreteStructException on invalid token (idx < 0)
   */
  public ValidTokenImpl(Token t) throws InvalidConcreteStructException {
    this.idx = t.getTokenIndex();
    if (this.idx < 0)
      throw new InvalidConcreteStructException("Index cannot be less than zero.");
    this.ttext = Optional.ofNullable(t.getText());
    this.ts = TextSpans.flatten(t.getTextSpan());
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.ts;
  }

  @Override
  public int getIndex() {
    return this.idx;
  }

  @Override
  public Optional<String> getTokenText() {
    return this.ttext;
  }
}
