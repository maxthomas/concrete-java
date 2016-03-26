/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.TextSpan;

/**
 * Interface representing a {@link FlattenedTextSpan} capable of
 * retrieving the underlying text represented by the same in the document.
 * <br><br>
 * Useful for Concrete structures that define a {@link TextSpan} that is optional.
 * With this interface, one can obtain the underlying string captured by the
 * underlying {@link FlattenedTextSpan} object.
 *
 * @see FlattenedTextSpan
 */
public interface PowerTextSpan extends FlattenedTextSpan {
  /**
   * @return the text encompassed by this {@link FlattenedTextSpan}
   */
  public String getTextFromDocument();
}
