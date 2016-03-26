/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;

import com.google.common.collect.ImmutableList.Builder;

import edu.jhu.hlt.concrete.SpanLink;
import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTokenRefSequence;

/**
 * Factory for creation of {@link ValidSpanLink} objects.
 */
public class SpanLinks {

  private SpanLinks() {
  }

  public static final List<ValidSpanLink> extract(Tokenization tkz) throws InvalidConcreteStructException {
    Builder<ValidSpanLink> b = new Builder<>();
    if (tkz.isSetSpanLinkList())
      for (SpanLink sl : tkz.getSpanLinkList()) {
        ValidSpanLink vsl = validate(sl);
        b.add(vsl);
      }

    return b.build();
  }

  public static final ValidSpanLink validate (SpanLink sl) throws InvalidConcreteStructException {
    return new SpanLinkWithEitherConcreteOrExternalTarget(sl);
  }

  public static final PowerSpanLink empower(ValidSpanLink sl, PowerTokenRefSequence ptrs) throws InvalidConcreteStructException {
    return new PowerSpanLinkImpl(sl, ptrs);
  }
}
