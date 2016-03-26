/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.Communication;

/**
 * Implementation of {@link PowerTextSpan}. Throws an {@link InvalidConcreteStructException}
 * if the passed in communication's text field is shorter than the maximum length of the span.
 */
public class PowerTextSpanImpl implements PowerTextSpan {

  private final FlattenedTextSpan fts;
  private final String fromComm;

  /**
   * @throws InvalidConcreteStructException
   */
  PowerTextSpanImpl(FlattenedTextSpan ts, Communication c) throws InvalidConcreteStructException {
    final String txt = c.getText();
    final int ctxtl = txt.length();
    if (ts.getEnd() > ctxtl)
      throw new InvalidConcreteStructException("Span end is greater than the length of communication.text field.");
    this.fts = ts;
    this.fromComm = txt.substring(ts.getStart(), ts.getEnd());
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan#getStart()
   */
  @Override
  public int getStart() {
    return this.fts.getStart();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan#getEnd()
   */
  @Override
  public int getEnd() {
    return this.fts.getEnd();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.PowerTextSpan#getTextFromDocument()
   */
  @Override
  public String getTextFromDocument() {
    return this.fromComm;
  }
}
