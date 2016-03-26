/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntitySet;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidSection;

public interface PowerCommunication extends ValidCommunication {

  public List<PowerEntityMentionSet> getEntityMentionSets();

  ////////////////////////////////////////
  // UUID-lookup methods
  ////////////////////////////////////////
  public Optional<ValidEntitySet> getEntitySet(ValidUUID uuid);

  public Optional<ValidEntityMentionSet> getEntityMentionSet(ValidUUID uuid);

  public Optional<ValidSection> getSection(ValidUUID uuid);
}
