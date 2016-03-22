/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.AnnotationMetadata;

public class FlatMetadataImpl implements FlattenedMetadata {

  private final String tool;
  private final int kb;
  private final long ts;

  FlatMetadataImpl(final AnnotationMetadata md) throws InvalidConcreteStructException {
    this(md.getTool(), md.getTimestamp(), md.getKBest());
  }

  FlatMetadataImpl(final String tool, final long ts, final int kb) throws InvalidConcreteStructException {
    this.tool = tool;
    this.kb = kb;
    if (this.kb <= 0)
      throw new InvalidConcreteStructException("KBest must be >0.");
    this.ts = ts;
  }

  @Override
  public String getTool() {
    return this.tool;
  }

  @Override
  public int getKBest() {
    return this.kb;
  }

  @Override
  public long getTimestamp() {
    return this.ts;
  }
}
