/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.TextSpan;
import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidToken;

/**
 * An interface that extends {@link ValidToken} by adding
 * the {@link PowerTextSpannable} interface.
 * <br><br>
 * Useful in the case of
 * wrapping {@link Token} objects with an unset {@link TextSpan}. To obtain
 * the underlying text represented by this object, the {@link PowerTextSpan}
 * can be used.
 *
 * @see ValidToken
 * @see PowerTextSpannable
 */
public interface PowerToken extends ValidToken, PowerTextSpannable {

}
