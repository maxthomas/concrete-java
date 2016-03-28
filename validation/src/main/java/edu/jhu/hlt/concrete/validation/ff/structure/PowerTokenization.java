/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;

import edu.jhu.hlt.concrete.TokenizationKind;
import edu.jhu.hlt.concrete.validation.ff.FlattenedMetadataWithValidUUID;
import edu.jhu.hlt.concrete.validation.ff.PowerToken;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

/**
 *
 */
public interface PowerTokenization extends FlattenedMetadataWithValidUUID {
  /**
   * @return a zero-based map of integer indices to their respective {@link ValidToken}s
   */
  public Map<Integer, PowerToken> getIndexToTokenMap();

  public Map<ValidUUID, PowerParse> getIdToParseMap();

  public Map<ValidUUID, PowerDependencyParse> getIdToDependencyParseMap();

  public Map<ValidUUID, PowerTokenTagging> getIdToTokenTaggingMap();

  public List<PowerSpanLink> getSpanLinks();

  public TokenizationKind getType();
}
