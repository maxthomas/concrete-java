/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

import edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable;

/**
 * Implementation of {@link ConfidenceScorable} with bounds [0.0, 1.0].
 */
public class ConfidenceImpl implements ConfidenceScorable {

  private final double conf;

  /**
   * @throws InvalidConcreteStructException
   */
  ConfidenceImpl(double conf) throws InvalidConcreteStructException {
    if (conf < 0.0d
        || conf > 1.0d) {
      throw new InvalidConcreteStructException("Confidence values below 0.0 and above 1.0 are not supported.");
    }

    this.conf = conf;
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable#getConfidence()
   */
  @Override
  public Optional<Double> getConfidence() {
    return Optional.of(this.conf);
  }

  public double get() {
    return this.conf;
  }
}
