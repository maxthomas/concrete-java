/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.TextSpan;

/**
 * A flat version of a concrete {@link TextSpan}. Useful to avoid
 * one extra layer of indirection.
 * <br>
 * <br>
 * Implementations may optionally provide validation of the methods below.
 */
public interface FlattenedTextSpan {
  /**
   * @return the start of this span
   */
  public int getStart();

  /**
   * @return the ending of this span
   */
  public int getEnd();
}
