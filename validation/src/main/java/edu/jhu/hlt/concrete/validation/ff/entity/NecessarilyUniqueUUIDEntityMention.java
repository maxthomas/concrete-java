/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMention;
import edu.jhu.hlt.concrete.UUID;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.UUIDs;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerTokenization;
import edu.jhu.hlt.concrete.validation.ff.structure.TokenRefSequences;

/**
 * Implementation of {@link ValidEntityMention} that makes the following assumptions:
 * <ul>
 * </ul>
 */
public class NecessarilyUniqueUUIDEntityMention extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<EntityMention>
    implements PowerEntityMention {

  private final FlattenedTokenRefSequence trs;

  private final Optional<String> entityType;
  private final Optional<String> phraseType;
  private final Optional<String> text;
  private final Optional<Double> conf;
  private final List<ValidUUID> children;


  /**
   * @throws InvalidConcreteStructException on invalid {@link EntityMention}
   */
  NecessarilyUniqueUUIDEntityMention(final EntityMention e, Communication c)
      throws InvalidConcreteStructException {
    super(e, e.getUuid());

    this.entityType = Optional.ofNullable(e.getEntityType());
    this.phraseType = Optional.ofNullable(e.getPhraseType());
    this.text = Optional.ofNullable(e.getText());
    this.conf = Optional.ofNullable(e.getConfidence());

    // TODO: handle validation
//    // flatten tokens
//    ValidUUID tkzID = this.em.getTokenizationUUID();
//    if (!tptrs.containsKey(tkzID))
//      throw new InvalidConcreteStructException("Tokenization UUID for this EntityMention was not found in "
//          + "the list of passed in pointers.");
//
//    this.tptr = tptrs.get(tkzID);
//    this.ptrs = TokenRefSequences.empower(em, this.tptr);

    this.trs = TokenRefSequences.flatten(e.getTokens());
    final int cls = e.getChildMentionIdListSize();
    this.children = new ArrayList<>(cls);
    if (cls > 0)
      for (UUID u : e.getChildMentionIdList())
        this.children.add(UUIDs.validate(u)
            .orElseThrow(() -> new InvalidConcreteStructException("null children UUIDs are not allowed.")));
  }

  @Override
  public Optional<String> getEntityType() {
    return entityType;
  }

  @Override
  public Optional<String> getPhraseType() {
    return phraseType;
  }

  @Override
  public Optional<String> getText() {
    return text;
  }

  @Override
  public Optional<Double> getConfidence() {
    return this.conf;
  }

  @Override
  public List<Integer> getTokenIndices() {
    return this.trs.getTokenIndices();
  }

  @Override
  public Optional<Integer> getAnchorTokenIndex() {
    return this.trs.getAnchorTokenIndex();
  }

  @Override
  public ValidUUID getTokenizationUUID() {
    return this.trs.getTokenizationUUID();
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.trs.getTextSpan();
  }

  @Override
  public List<ValidUUID> getChildMentionUUIDList() {
    return new ArrayList<>(this.children);
  }

  @Override
  public List<PowerToken> getTokens() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<PowerToken> getAnchorToken() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public PowerTokenization getTokenization() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<PowerEntityMention> getChildMentions() {
    // TODO Auto-generated method stub
    return null;
  }
}
