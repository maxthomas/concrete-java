package edu.jhu.hlt.concrete.validation.ff.structure;

import edu.jhu.hlt.concrete.DependencyParse;
import edu.jhu.hlt.concrete.validation.ff.AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

public class NecessarilyUniqueUUIDDepParse extends AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<DependencyParse>
    implements ValidDependencyParse {

  public NecessarilyUniqueUUIDDepParse(DependencyParse dp) throws InvalidConcreteStructException {
    super(dp, dp.getUuid(), dp.getMetadata());
  }
}
