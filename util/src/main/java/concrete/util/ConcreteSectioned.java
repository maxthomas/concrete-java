/*
 * Copyright 2012-2014 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package concrete.util;

import java.util.Map;

import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.UUID;

/**
 * @deprecated
 */
@Deprecated
public interface ConcreteSectioned {
  public Map<UUID, Section> generateSectionIdToSectionMap();
}
