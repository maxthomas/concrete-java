/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.UUID;

/**
 * A wrapper object around {@link UUID}s that performs semantic validation. Should
 * the validation fail, an {@link InvalidConcreteStructException} will be thrown
 * at the first failed validation step.
 * <br><br>
 * The following checks are performed:
 * <ul>
 * <li>parsed into a {@link java.util.UUID} to ensure semantic validity</li>
 * </ul>
 */
public class SemanticallyValidNonNullUUID implements ValidUUID {

  private final UUID uuid;

  /**
   * @param uuid the {@link edu.jhu.hlt.concrete.UUID} to wrap
   * @throws InvalidConcreteStructException on semantic failure
   */
  SemanticallyValidNonNullUUID(final edu.jhu.hlt.concrete.UUID uuid) throws InvalidConcreteStructException {
    if (uuid == null)
      throw new InvalidConcreteStructException("UUID is null.");
    String us = uuid.getUuidString();
    if (us != null
        && us.length() == 36
        && us.contains("-")) {
      try {
        this.uuid = java.util.UUID.fromString(us);
      } catch (IllegalArgumentException iae) {
        throw new InvalidConcreteStructException(iae);
      }
    } else
      throw new InvalidConcreteStructException("UUID is not valid: " + us);
  }

  /* (non-Javadoc)
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    return this.uuid.hashCode();
  }

  @Override
  public final UUID get() {
    return this.uuid;
  }

  @Override
  public final edu.jhu.hlt.concrete.UUID toConcrete() {
    return new edu.jhu.hlt.concrete.UUID(this.uuid.toString());
  }

  /* (non-Javadoc)
   * @see java.lang.Object#toString()
   */
  @Override
  public String toString() {
    return this.uuid.toString();
  }

  /* (non-Javadoc)
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    SemanticallyValidNonNullUUID other = (SemanticallyValidNonNullUUID) obj;
    if (uuid == null) {
      if (other.uuid != null)
        return false;
    } else if (!uuid.equals(other.uuid))
      return false;
    return true;
  }
}
