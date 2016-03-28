/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

/**
 * An interface for objects that can produce {@link PowerToken}s.
 */
public interface Tokenable {
  public PowerToken getToken();
}
