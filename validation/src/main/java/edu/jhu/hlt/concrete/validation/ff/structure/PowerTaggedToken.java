/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable;
import edu.jhu.hlt.concrete.validation.ff.ConfidenceImpl;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;

/**
 *
 */
public interface PowerTaggedToken extends ConfidenceScorable, ZeroBasedIndexable, PowerToken {
  public Optional<String> getBestTag();

  public Map<String, ConfidenceImpl> getTagToConfidenceMap();

  public PowerToken getToken();
}
