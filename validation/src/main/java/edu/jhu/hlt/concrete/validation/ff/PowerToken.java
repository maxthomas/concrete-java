/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

import edu.jhu.hlt.concrete.TextSpan;
import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.structure.ZeroBasedIndexable;

/**
 * An interface that extends {@link Token} by adding
 * the {@link PowerTextSpannable} interface.
 * <br><br>
 * Useful in the case of
 * wrapping {@link Token} objects with an unset {@link TextSpan}. To obtain
 * the underlying text represented by this object, the {@link PowerTextSpan}
 * can be used.
 *
 * @see PowerTextSpannable
 */
public interface PowerToken extends ZeroBasedIndexable, PowerTextSpannable {
  public Optional<String> getTokenText();
}
