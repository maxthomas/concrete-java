package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Optional;

import edu.jhu.hlt.concrete.SpanLink;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTextSpan;
import edu.jhu.hlt.concrete.validation.ff.FlattenedTokenRefSequence;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.UUIDs;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class SpanLinkWithEitherConcreteOrExternalTarget implements ValidSpanLink {

  private final String type;
  private final Optional<ValidUUID> concreteTarget;
  private final Optional<String> externalTarget;
  private final FlattenedTokenRefSequence trs;

  SpanLinkWithEitherConcreteOrExternalTarget(final SpanLink vsl)
      throws InvalidConcreteStructException {

    if (!vsl.isSetConcreteTarget()
        && !vsl.isSetExternalTarget())
      throw new InvalidConcreteStructException("Neither Concrete nor external targets are set in this SpanLink.");

    this.type = vsl.getLinkType();
    this.concreteTarget = UUIDs.validate(vsl.getConcreteTarget());
    this.externalTarget = Optional.ofNullable(vsl.getExternalTarget());
    this.trs = TokenRefSequences.flatten(vsl.getTokens());
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
  public Optional<String> getExternalTarget() {
    return this.externalTarget;
  }

  @Override
  public Optional<ValidUUID> getConcreteTarget() {
    return this.concreteTarget;
  }

  @Override
  public String getLinkType() {
    return this.type;
  }
}
