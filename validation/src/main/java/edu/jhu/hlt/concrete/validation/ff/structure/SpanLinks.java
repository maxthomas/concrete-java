/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;

import com.google.common.collect.ImmutableList.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.SpanLink;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

/**
 * Factory for creation of {@link ValidSpanLink} objects.
 */
public class SpanLinks {

  private SpanLinks() {
  }

  public static final List<PowerSpanLink> extract(Tokenization tkz, Communication c)
      throws InvalidConcreteStructException {
    Builder<PowerSpanLink> b = new Builder<>();
    if (tkz.isSetSpanLinkList())
      for (SpanLink sl : tkz.getSpanLinkList()) {
        PowerSpanLink vsl = empower(sl, c);
        b.add(vsl);
      }

    return b.build();
  }

  public static final PowerSpanLink empower(SpanLink sl, Communication c)
      throws InvalidConcreteStructException {
    return new SpanLinkWithEitherConcreteOrExternalTarget(sl, c);
  }
}
