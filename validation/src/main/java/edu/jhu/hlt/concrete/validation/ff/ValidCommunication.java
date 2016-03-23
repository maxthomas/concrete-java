/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.serialization.Concretable;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntitySet;
import edu.jhu.hlt.concrete.validation.ff.situation.ValidSituationMentionSet;
import edu.jhu.hlt.concrete.validation.ff.situation.ValidSituationSet;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidSection;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidSentence;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidTokenization;

public interface ValidCommunication extends UUIDable, Concretable<Communication> {

  ////////////////////////////////////////
  // Methods mirroring default Communication access
  ////////////////////////////////////////
  public List<ValidEntitySet> getEntitySetList();

  public List<ValidEntityMentionSet> getEntityMentionSetList();

  public List<ValidSituationSet> getSituationSetList();

  public List<ValidSituationMentionSet> getSituationMentionSetList();

  public List<ValidSection> getSectionList();

  public List<ValidSentence> getSentenceList();

  public List<ValidTokenization> getTokenizationList();
}
