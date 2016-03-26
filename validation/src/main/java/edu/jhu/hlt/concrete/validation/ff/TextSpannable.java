/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

import edu.jhu.hlt.concrete.TextSpan;

/**
 * Interface that represents an object with an optional
 * Concrete {@link TextSpan} object, reduced down to a
 * {@link FlattenedTextSpan}. Useful for avoiding <code>null</code>
 * values in optional fields that detonate code when hit.
 *
 * @see FlattenedTextSpan
 */
public interface TextSpannable {
  /**
   * @return an {@link Optional} wrapping a {@link FlattenedTextSpan}
   */
  public Optional<FlattenedTextSpan> getTextSpan();
}
