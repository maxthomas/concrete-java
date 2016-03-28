/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public interface PowerSpanLink extends PowerTokenRefSequence {
  public Optional<String> getExternalTarget();

  public Optional<ValidUUID> getConcreteTarget();

  public String getLinkType();
}
