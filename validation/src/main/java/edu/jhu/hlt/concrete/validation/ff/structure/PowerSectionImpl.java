/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public class PowerSectionImpl implements PowerSection {

  private final ValidSection vs;
  private final Optional<PowerTextSpan> pts;

  /**
   * @throws InvalidConcreteStructException
   *
   */
  PowerSectionImpl(ValidSection vs, Communication c) throws InvalidConcreteStructException {
    this.vs = vs;
    this.pts = TextSpans.empower(vs, c);
  }

  @Override
  public List<ValidSentence> getSentences() {
    return this.vs.getSentences();
  }

  @Override
  public String getKind() {
    return this.vs.getKind();
  }

  @Override
  public Optional<String> getLabel() {
    return this.vs.getLabel();
  }

  @Override
  public List<Integer> getNumbers() {
    return this.vs.getNumbers();
  }

  @Override
  public ValidUUID getUUID() {
    return this.vs.getUUID();
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.vs.getTextSpan();
  }

  @Override
  public Optional<PowerTextSpan> getPowerTextSpan() {
    return this.pts;
  }
}
