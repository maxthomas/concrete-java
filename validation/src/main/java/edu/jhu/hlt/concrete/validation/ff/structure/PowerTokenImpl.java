/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;

/**
 * Default implementation of the {@link PowerToken} interface.
 */
public class PowerTokenImpl implements PowerToken {

  private final Optional<PowerTextSpan> pts;
  private final int idx;
  private final Optional<String> ttext;

  /**
   * @param tok
   * @param c
   * @throws InvalidConcreteStructException on validation error
   */
  PowerTokenImpl(Token tok, Communication c) throws InvalidConcreteStructException {
    this.idx = tok.getTokenIndex();
    if (this.idx < 0)
      throw new InvalidConcreteStructException("Index cannot be less than zero.");
    this.ttext = Optional.ofNullable(tok.getText());
    this.pts = TextSpans.empower(tok.getTextSpan(), c);
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ValidToken#getTokenText()
   */
  @Override
  public Optional<String> getTokenText() {
    return this.ttext;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ZeroBasedIndexable#getIndex()
   */
  @Override
  public int getIndex() {
    return this.idx;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.PowerTextSpannable#getPowerTextSpan()
   */
  @Override
  public Optional<PowerTextSpan> getTextSpan() {
    return this.pts;
  }
}
