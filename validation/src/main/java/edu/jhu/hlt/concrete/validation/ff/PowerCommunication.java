/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Map;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.serialization.Concretable;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntityGroup;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntityMentionGroup;
import edu.jhu.hlt.concrete.validation.ff.situation.PowerSituationGroup;
import edu.jhu.hlt.concrete.validation.ff.situation.PowerSituationMentionGroup;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerSection;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerSentence;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerTokenization;

/**
 * A powerful alternative to Concrete {@link Communication} objects,
 * adding utility functionality otherwise not present.
 * <br><br>
 * This interfaces offers {@link ValidUUID}:struct lookups for most
 * annotations, allowing a specific {@link UUIDable} object to be
 * retrieved from the top of the communication object.
 */
public interface PowerCommunication extends UUIDable, Concretable<Communication> {
  public Map<ValidUUID, PowerSection> getIdToSectionMap();
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap();
  public Map<ValidUUID, PowerTokenization> getIdToTokenizationMap();

  public Map<ValidUUID, PowerEntityMentionGroup> getIdToEntityMentionsMap();
  public Map<ValidUUID, PowerEntityGroup> getIdToEntitiesMap();

  public Map<ValidUUID, PowerSituationMentionGroup> getIdToSituationMentionsMap();
  public Map<ValidUUID, PowerSituationGroup> getIdToSituationsMap();
}
