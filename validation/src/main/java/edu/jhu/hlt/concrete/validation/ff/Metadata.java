/**
 *
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.AnnotationMetadata;

/**
 *
 */
public class Metadata {

  /**
   *
   */
  private Metadata() {

  }

  public static final FlattenedMetadata validate(AnnotationMetadata md)
      throws InvalidConcreteStructException {
    return new FlatMetadataImpl(md);
  }

  public static final FlattenedMetadata construct(final String tool, final long ts, final int kb)
      throws InvalidConcreteStructException {
    return new FlatMetadataImpl(tool, ts, kb);
  }
}
