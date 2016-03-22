/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.AnnotationMetadata;

public interface FlattenedMetadata {
  public String getTool();

  public int getKBest();

  public long getTimestamp();

  default AnnotationMetadata toConcrete() {
    return new AnnotationMetadata(this.getTool(), this.getTimestamp(), this.getKBest());
  }
}
