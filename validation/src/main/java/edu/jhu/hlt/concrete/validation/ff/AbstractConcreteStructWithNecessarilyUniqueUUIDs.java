/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.HashSet;
import java.util.Set;

import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;

import edu.jhu.hlt.concrete.UUID;
import edu.jhu.hlt.concrete.serialization.Concretable;

public abstract class AbstractConcreteStructWithNecessarilyUniqueUUIDs<T extends TBase<T, ? extends TFieldIdEnum>>
    implements Concretable<T>, UUIDable {

  protected final ValidUUID uuid;
  protected final Set<ValidUUID> necessarilyUniqueUUIDs;
  protected final T obj;

  protected AbstractConcreteStructWithNecessarilyUniqueUUIDs(T t, UUID uuid) throws InvalidConcreteStructException {
    this.obj = t;
    this.uuid = UUIDs.validate(uuid);

    this.necessarilyUniqueUUIDs = new HashSet<>();
    this.addNecessarilyUniqueUUID(uuid);
  }

  private final void addNecessarilyUniqueUUID(edu.jhu.hlt.concrete.UUID concUuid) throws InvalidConcreteStructException {
    ValidUUID vu = UUIDs.validate(concUuid);
    this.addNecessarilyUniqueUUID(vu);
  }

  private final void addNecessarilyUniqueUUID(ValidUUID uuid) throws InvalidConcreteStructException {
    if (!this.necessarilyUniqueUUIDs.add(uuid))
      throw new InvalidConcreteStructException("Communication contains at least one duplicate UUID: " + uuid.toString());
  }

  @Override
  public final T toThrift() {
    return this.obj;
  }

  @Override
  public final ValidUUID getUUID() {
    return this.uuid;
  }
}
