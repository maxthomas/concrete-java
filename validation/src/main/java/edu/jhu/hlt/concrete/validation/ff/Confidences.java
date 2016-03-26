/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

/**
 *
 */
public class Confidences {

  private Confidences() {
  }

  public static final ConfidenceImpl validate(double conf) throws InvalidConcreteStructException {
    return new ConfidenceImpl(conf);
  }
}
