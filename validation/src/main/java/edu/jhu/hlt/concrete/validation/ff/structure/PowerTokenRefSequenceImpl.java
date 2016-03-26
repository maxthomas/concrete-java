/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList.Builder;

import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public class PowerTokenRefSequenceImpl implements PowerTokenRefSequence {

  private final FlattenedTokenRefSequence vtrs;
  private final ValidTokenization ptr;

  private final List<ValidToken> tokenList;
  private final Optional<ValidToken> anchorT;

  /**
   *
   */
  PowerTokenRefSequenceImpl(FlattenedTokenRefSequence vtrs, ValidTokenization ptr) throws InvalidConcreteStructException {
    if (!vtrs.getTokenizationUUID().equals(ptr.getUUID()))
      throw new InvalidConcreteStructException("Incorrect Tokenization: the pointer of the TRS is not the same as the Tokenization's UUID.");
    this.vtrs = vtrs;
    this.ptr = ptr;

    Builder<ValidToken> b = new Builder<>();
    Map<Integer, ValidToken> tm = ptr.getIndexToTokenMap();
    for (int i : vtrs.getTokenIndices()) {
      if (!tm.containsKey(i))
        throw new InvalidConcreteStructException("TokenRefSequence references token index: "
            + i + ", but this index is not in the referenced Tokenization object.");
      else
        b.add(tm.get(i));
    }

    this.tokenList = b.build();
    if (vtrs.getAnchorTokenIndex().isPresent()) {
      int aidx = vtrs.getAnchorTokenIndex().get();
      if (!tm.containsKey(aidx))
        throw new InvalidConcreteStructException("Anchor token index is: " + aidx
            + ", but this index does not exist in the referenced Tokenization.");
      else
        this.anchorT = Optional.of(tm.get(aidx));
    } else
      this.anchorT = Optional.empty();
  }

  @Override
  public List<PowerToken> getTokens() {
    return this.tokenList;
  }

  @Override
  public Optional<PowerToken> getAnchorToken() {
    return this.anchorT;
  }

  @Override
  public ValidTokenization getTokenization() {
    return this.ptr;
  }

  @Override
  public List<Integer> getTokenIndices() {
    return this.vtrs.getTokenIndices();
  }

  @Override
  public Optional<Integer> getAnchorTokenIndex() {
    return this.vtrs.getAnchorTokenIndex();
  }

  @Override
  public ValidUUID getTokenizationUUID() {
    return this.vtrs.getTokenizationUUID();
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.vtrs.getTextSpan();
  }
}
