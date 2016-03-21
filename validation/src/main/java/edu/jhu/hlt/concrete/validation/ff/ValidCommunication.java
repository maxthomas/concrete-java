package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.serialization.Concretable;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntitySet;

public interface ValidCommunication extends UUIDable, Concretable<Communication> {
  public List<ValidEntitySet> getEntitySetList();
}
