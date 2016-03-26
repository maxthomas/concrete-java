package edu.jhu.hlt.concrete.validation.ff.entity;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;
import edu.jhu.hlt.concrete.validation.ff.structure.TokenRefSequences;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidToken;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidTokenization;

public class PowerEntityMentionImpl implements PowerEntityMention {

  private final ValidEntityMention em;
  private final ValidTokenization tptr;
  private final PowerTokenRefSequence ptrs;

  public PowerEntityMentionImpl(final ValidEntityMention em,
      final Map<ValidUUID, ValidTokenization> tptrs, final Map<ValidUUID, ValidEntityMention> emptrs) throws InvalidConcreteStructException {
    this.em = em;

    // flatten tokens
    ValidUUID tkzID = this.em.getTokenizationUUID();
    if (!tptrs.containsKey(tkzID))
      throw new InvalidConcreteStructException("Tokenization UUID for this EntityMention was not found in "
          + "the list of passed in pointers.");

    this.tptr = tptrs.get(tkzID);
    this.ptrs = TokenRefSequences.empower(em, this.tptr);
  }

  @Override
  public ValidUUID getUUID() {
    return this.em.getUUID();
  }

  @Override
  public List<Integer> getTokenIndices() {
    return this.em.getTokenIndices();
  }

  @Override
  public Optional<Integer> getAnchorTokenIndex() {
    return this.em.getAnchorTokenIndex();
  }

  @Override
  public ValidUUID getTokenizationUUID() {
    return this.em.getTokenizationUUID();
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.em.getTextSpan();
  }

  @Override
  public Optional<Double> getConfidence() {
    return this.em.getConfidence();
  }

  @Override
  public List<ValidToken> getTokens() {
    return this.ptrs.getTokens();
  }

  @Override
  public Optional<ValidToken> getAnchorToken() {
    return this.ptrs.getAnchorToken();
  }

  @Override
  public ValidTokenization getTokenization() {
    return this.tptr;
  }

  @Override
  public Optional<String> getText() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<String> getEntityType() {
    return this.em.getEntityType();
  }

  @Override
  public Optional<String> getPhraseType() {
    return this.em.getPhraseType();
  }

  @Override
  public List<ValidUUID> getChildMentionUUIDList() {
    return this.em.getChildMentionUUIDList();
  }

  @Override
  public List<PowerEntityMention> getChildMentions() {
    // TODO Auto-generated method stub
    return null;
  }
}
