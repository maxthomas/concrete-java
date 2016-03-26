/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;

/**
 * Default implementation of the {@link PowerToken} interface.
 */
public class PowerTokenImpl implements PowerToken {

  private final ValidToken vt;
  private final Optional<PowerTextSpan> pts;

  /**
   * @param tok
   * @param c
   * @throws InvalidConcreteStructException on validation error
   */
  PowerTokenImpl(ValidToken tok, Communication c) throws InvalidConcreteStructException {
    this.pts = TextSpans.empower(tok, c);
    this.vt = tok;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ValidToken#getTokenText()
   */
  @Override
  public Optional<String> getTokenText() {
    return this.vt.getTokenText();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.TextSpannable#getTextSpan()
   */
  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.vt.getTextSpan();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ZeroBasedIndexable#getIndex()
   */
  @Override
  public int getIndex() {
    return this.vt.getIndex();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.PowerTextSpannable#getPowerTextSpan()
   */
  @Override
  public Optional<PowerTextSpan> getPowerTextSpan() {
    return this.pts;
  }
}
