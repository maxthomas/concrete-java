/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.TextSpannable;

/**
 * Interface providing additional utility on top of Concrete {@link Token}
 * objects.
 *
 * @see TextSpannable
 * @see ZeroBasedIndexable
 */
public interface ValidToken extends TextSpannable, ZeroBasedIndexable {
  /**
   * @return an {@link Optional} wrapping a {@link String}, representing the optional
   * text field of the token.
   */
  public Optional<String> getTokenText();
}
