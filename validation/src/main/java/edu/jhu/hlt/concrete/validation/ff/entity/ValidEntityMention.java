package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.miscommunication.ConfidenceScorable;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.UUIDable;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public interface ValidEntityMention extends UUIDable, FlattenedTokenRefSequence, ConfidenceScorable {
  public Optional<String> getText();

  public Optional<String> getEntityType();

  public Optional<String> getPhraseType();

  public List<ValidUUID> getChildMentionUUIDList();
}
