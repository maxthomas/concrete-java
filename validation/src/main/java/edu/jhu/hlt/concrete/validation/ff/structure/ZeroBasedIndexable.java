/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.Token;

/**
 * Interface representing an object that has a zero-based index field.
 * Most notably used to represent concrete {@link Token} and token-like objects.
 * <br><br>
 * It is up to implementations to ensure that the indices are reasonable:
 * for example, that they are non-negative.
 */
public interface ZeroBasedIndexable {
  /**
   * @return the zero-based index
   */
  public int getIndex();
}
