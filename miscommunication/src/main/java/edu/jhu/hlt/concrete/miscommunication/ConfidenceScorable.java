/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.miscommunication;

import java.util.Optional;

/**
 * An interface for things with confidence scores.
 */
public interface ConfidenceScorable {
  public Optional<Double> getConfidence();
}
