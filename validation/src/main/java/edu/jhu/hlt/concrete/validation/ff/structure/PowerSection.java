/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import edu.jhu.hlt.concrete.validation.ff.PowerTextSpannable;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public interface PowerSection extends ValidSection, PowerTextSpannable {
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap();
}
