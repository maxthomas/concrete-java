/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.TextSpans;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public class PowerSectionImpl extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Section>
    implements PowerSection {

  private final String k;
  private final Optional<String> l;
  private final Optional<PowerTextSpan> pts;
  private final ImmutableList<Integer> nl;
  private final Map<ValidUUID, PowerSentence> idToSM;

  /**
   * @throws InvalidConcreteStructException
   */
  PowerSectionImpl(Section vs, Communication c) throws InvalidConcreteStructException {
    super(vs, vs.getUuid());
    this.idToSM = Sentences.extract(vs, c);
    this.k = vs.getKind();
    this.l = Optional.ofNullable(vs.getLabel());
    Builder<Integer> b = new Builder<>();
    if (vs.getNumberListSize() > 0)
      b.addAll(vs.getNumberList());

    this.nl = b.build();
    this.pts = TextSpans.empower(vs.getTextSpan(), c);
  }

  @Override
  public String getKind() {
    return this.k;
  }

  @Override
  public Optional<String> getLabel() {
    return this.l;
  }

  @Override
  public List<Integer> getNumbers() {
    return this.nl;
  }

  @Override
  public Optional<PowerTextSpan> getTextSpan() {
    return this.pts;
  }

  @Override
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap() {
    return this.idToSM;
  }
}
