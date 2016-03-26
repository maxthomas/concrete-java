/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.TokenRefSequence;

/**
 * A 'lightly' validated wrapper around {@link TokenRefSequence} that does
 * a few standalone checks, including:
 * <ul>
 * <li>UUID validation</li>
 * <li>TextSpan validation</li>
 * </ul>
 */
public class StandaloneValidTokenRefSequence implements FlattenedTokenRefSequence {

  private final List<Integer> tokList;
  private final ValidUUID tkzUUID;
  private final Optional<Integer> anchorTokenIdx;
  private final Optional<FlattenedTextSpan> ts;

  /**
   * @throws InvalidConcreteStructException
   *
   */
  public StandaloneValidTokenRefSequence(final TokenRefSequence trs) throws InvalidConcreteStructException {
    this.tokList = trs.getTokenIndexList();
    this.tkzUUID = UUIDs.validate(trs.getTokenizationId())
        .orElseThrow(() -> new InvalidConcreteStructException("Tokenization UUID cannot be null."));
    this.anchorTokenIdx = Optional.ofNullable(trs.getAnchorTokenIndex());
    this.ts = TextSpans.flatten(trs.getTextSpan());
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence#getTokens()
   */
  @Override
  public List<Integer> getTokenIndices() {
    return new ArrayList<>(this.tokList);
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence#getAnchorToken()
   */
  @Override
  public Optional<Integer> getAnchorTokenIndex() {
    return this.anchorTokenIdx;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence#getTokenizationUUID()
   */
  @Override
  public ValidUUID getTokenizationUUID() {
    return this.tkzUUID;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence#getTextSpan()
   */
  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.ts;
  }
}
