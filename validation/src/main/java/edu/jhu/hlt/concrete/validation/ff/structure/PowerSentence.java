/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.PowerTextSpannable;
import edu.jhu.hlt.concrete.validation.ff.UUIDable;

/**
 *
 */
public interface PowerSentence extends UUIDable, PowerTextSpannable {
  public Optional<PowerTokenization> getPowerTokenization();
}
