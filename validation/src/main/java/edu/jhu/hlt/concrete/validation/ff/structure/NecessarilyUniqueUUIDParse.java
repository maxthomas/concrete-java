package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.Parse;
import edu.jhu.hlt.concrete.validation.ff.AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class NecessarilyUniqueUUIDParse extends AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<Parse>
    implements ValidParse {

  public NecessarilyUniqueUUIDParse(Parse p) throws InvalidConcreteStructException {
    super(p, p.getUuid(), p.getMetadata());
  }
}
