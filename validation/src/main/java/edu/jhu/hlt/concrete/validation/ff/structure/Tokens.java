/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Token;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;

/**
 * Factory for production of valid {@link Token} object wrappers.
 */
public class Tokens {

  private Tokens() {
  }

  /**
   * @param t a {@link Token}
   * @return a {@link ValidToken}
   * @throws InvalidConcreteStructException on invalid struct (e.g. idx < 0)
   */
  public static final ValidToken validate(Token t) throws InvalidConcreteStructException {
    return new ValidTokenImpl(t);
  }

  /**
   * @param t a {@link Token}
   * @param c the {@link Communication} that contains the token
   * @return a {@link PowerToken}
   * @throws InvalidConcreteStructException on validation failure
   */
  public static final PowerToken empower(Token t, Communication c) throws InvalidConcreteStructException {
    return new PowerTokenImpl(validate(t), c);
  }
}
