/*
 *
 */
package edu.jhu.hlt.concrete.validation.ff;

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
   * Returns a semantically valid UUID.
   *
   * @param uuid the {@link UUID} to validate
   * @return
   * @throws InvalidConcreteStructException on invalid UUID
   */
  public static final ValidUUID validate(UUID uuid) throws InvalidConcreteStructException {
    return new SemanticallyValidNonNullUUID(uuid);
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
