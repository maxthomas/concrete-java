/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.PowerTextSpannable;
import edu.jhu.hlt.concrete.validation.ff.UUIDable;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public interface PowerSection extends UUIDable, PowerTextSpannable {
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap();

  public String getKind();

  public Optional<String> getLabel();

  public List<Integer> getNumbers();
}
