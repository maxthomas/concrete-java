package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class PowerSpanLinkImpl implements PowerSpanLink {

  private final ValidSpanLink sl;
  private final PowerTokenRefSequence ptrs;

  PowerSpanLinkImpl(final ValidSpanLink vsl, final PowerTokenRefSequence sl)
      throws InvalidConcreteStructException {
    this.ptrs = sl;
    this.sl = vsl;
  }

  @Override
  public List<Integer> getTokenIndices() {
    return this.sl.getTokenIndices();
  }

  @Override
  public Optional<Integer> getAnchorTokenIndex() {
    return this.sl.getAnchorTokenIndex();
  }

  @Override
  public ValidUUID getTokenizationUUID() {
    return this.sl.getTokenizationUUID();
  }

  @Override
  public Optional<FlattenedTextSpan> getTextSpan() {
    return this.ptrs.getTextSpan();
  }

  @Override
  public Optional<String> getExternalTarget() {
    return this.sl.getExternalTarget();
  }

  @Override
  public Optional<ValidUUID> getConcreteTarget() {
    return this.sl.getConcreteTarget();
  }

  @Override
  public String getLinkType() {
    return this.sl.getLinkType();
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
    return this.ptrs.getTokenization();
  }
}
