/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.TokenRefSequence;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.StandaloneValidTokenRefSequence;

/**
 * Factory for producing valid {@link TokenRefSequence} objects.
 */
public class TokenRefSequences {

  private TokenRefSequences() {
  }

  public static final FlattenedTokenRefSequence flatten(TokenRefSequence trs) throws InvalidConcreteStructException {
    return new StandaloneValidTokenRefSequence(trs);
  }

  public static final PowerTokenRefSequence empower(FlattenedTokenRefSequence trs, ValidTokenization vtkz) throws InvalidConcreteStructException {
    return new PowerTokenRefSequenceImpl(trs, vtkz);
  }

  public static final PowerTokenRefSequence empower(TokenRefSequence trs, Tokenization vtkz) throws InvalidConcreteStructException {
    return new PowerTokenRefSequenceImpl(flatten(trs),
        Tokenizations.validate(vtkz));
  }
}
