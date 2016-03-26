/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;

import edu.jhu.hlt.concrete.TokenizationKind;
import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 * Interface representing a valid tokenization.
 */
public interface ValidTokenization extends FlattenedMetadataWithValidUUID {

  /**
   * @return a zero-based map of integer indices to their respective {@link ValidToken}s
   */
  public Map<Integer, ValidToken> getIndexToTokenMap();

  public Map<ValidUUID, ValidParse> getIdToParseMap();

  public Map<ValidUUID, ValidDependencyParse> getIdToDependencyParseMap();

  public Map<ValidUUID, ValidTokenTagging> getIdToTokenTaggingMap();

  public List<ValidSpanLink> getSpanLinks();

  public TokenizationKind getType();
}
