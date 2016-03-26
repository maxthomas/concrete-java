/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.validation.ff.ConfidenceImpl;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;

/**
 *
 */
public class PowerTaggedTokenImpl implements PowerTaggedToken {

  private final ValidTaggedToken vtt;

  /**
   *
   */
  public PowerTaggedTokenImpl(ValidTaggedToken vtt, Communication c) {
    this.vtt = vtt;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ValidTaggedToken#getBestTag()
   */
  @Override
  public Optional<String> getBestTag() {
    return this.vtt.getBestTag();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ValidTaggedToken#getTagToConfidenceMap()
   */
  @Override
  public Map<String, ConfidenceImpl> getTagToConfidenceMap() {
    return this.vtt.getTagToConfidenceMap();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable#getConfidence()
   */
  @Override
  public Optional<Double> getConfidence() {
    return this.vtt.getConfidence();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.structure.ZeroBasedIndexable#getIndex()
   */
  @Override
  public int getIndex() {
    return this.vtt.getIndex();
  }

  @Override
  public Optional<String> getTokenText() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.vtt.get;
  }

  @Override
  public Optional<PowerTextSpan> getPowerTextSpan() {
    // TODO Auto-generated method stub
    return null;
  }
}
