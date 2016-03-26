/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

import edu.jhu.hlt.concrete.UUID;

/**
 * A factory that provides fail-fast utility for {@link UUID} objects.
 */
public class UUIDs {

  /**
   *
   */
  private UUIDs() {

  }

  /**
   * @param uuid the {@link UUID} to validate. <code>null</code> is OK.
   * @return an {@link Optional} wrapping a {@link ValidUUID}, guaranteed
   *  to be a semantically valid UUID.
   * @throws InvalidConcreteStructException on invalid UUID
   */
  public static final Optional<ValidUUID> validate(UUID uuid) throws InvalidConcreteStructException {
    if (uuid == null)
      return Optional.empty();
    else
      return Optional.of(new SemanticallyValidNonNullUUID(uuid));
  }

  /**
   * @param high the most sig bits
   * @param low the least sig bits
   * @return a Concrete {@link UUID} wrapping the {@link java.util.UUID}
   */
  public static final UUID construct(final long high, final long low) {
    return new UUID(new java.util.UUID(high, low).toString());
  }
}
