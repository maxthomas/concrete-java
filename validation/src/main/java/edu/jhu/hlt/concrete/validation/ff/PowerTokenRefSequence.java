/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.TextSpan;
import edu.jhu.hlt.concrete.TokenRefSequence;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.miscommunication.tokenized.PowerTokenization;

/**
 * Interface, extending {@link FlattenedTokenRefSequence}, that represents
 * a powerful alternative to {@link TokenRefSequence} objects.
 * This interface provides an additional set of methods that obviate the
 * need for construction of pointers, crawling through tokenizations, etc.
 * <br><br>
 * For example, instead of relying upon
 * {@link TokenRefSequence#getTokenIndexList()} and the correct {@link Tokenization}
 * to get, e.g., the {@link TextSpan} object, one can consume this interface's methods
 * to get wrapper objects that allow direct retrieval of the tokens, their textspans,
 * and their texts.
 */
public interface PowerTokenRefSequence extends FlattenedTokenRefSequence {
  public List<PowerToken> getTokens();

  public Optional<PowerToken> getAnchorToken();

  public PowerTokenization getTokenization();
}
