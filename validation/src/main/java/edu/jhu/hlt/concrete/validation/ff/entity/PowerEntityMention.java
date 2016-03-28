/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.UUIDable;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public interface PowerEntityMention extends UUIDable, ConfidenceScorable, PowerTokenRefSequence {
  public Optional<String> getText();

  public Optional<String> getEntityType();

  public Optional<String> getPhraseType();

  public List<ValidUUID> getChildMentionUUIDList();

  public List<PowerEntityMention> getChildMentions();
}
