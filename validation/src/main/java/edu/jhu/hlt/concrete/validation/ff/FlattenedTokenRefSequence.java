/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;
import java.util.Optional;

public interface FlattenedTokenRefSequence extends TextSpannable {
  public List<Integer> getTokenIndices();

  public Optional<Integer> getAnchorTokenIndex();

  public ValidUUID getTokenizationUUID();
}
