/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import edu.jhu.hlt.concrete.Communication;

public class Communications {
  private Communications() {

  }

  public static final ValidCommunication validate(Communication c) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDCommunication(c);
  }

  public static final PowerCommunication empower(Communication c) throws InvalidConcreteStructException {
    return new NecessarilyUniqueUUIDCommunication(c);
  }
}
