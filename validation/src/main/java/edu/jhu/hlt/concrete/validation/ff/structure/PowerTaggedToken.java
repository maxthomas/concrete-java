/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.validation.ff.PowerToken;

/**
 *
 */
public interface PowerTaggedToken extends ValidTaggedToken, PowerToken {
  public PowerToken getToken();
}
