/**
 *
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMentionSet;
import edu.jhu.hlt.concrete.EntitySet;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.validation.ff.entity.EntityGroups;
import edu.jhu.hlt.concrete.validation.ff.entity.EntityMentionGroups;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntity;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntityGroup;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntityMention;
import edu.jhu.hlt.concrete.validation.ff.entity.PowerEntityMentionGroup;
import edu.jhu.hlt.concrete.validation.ff.situation.PowerSituationGroup;
import edu.jhu.hlt.concrete.validation.ff.situation.PowerSituationMentionGroup;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerSection;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerSentence;
import edu.jhu.hlt.concrete.validation.ff.structure.PowerTokenization;
import edu.jhu.hlt.concrete.validation.ff.structure.Sections;

/**
 * Implementation of {@link ValidCommunication} that provides validation and utility
 * wrapping of some communication-based methods.
 * <br><br>
 * This class that has the following validation checks:
 * <ul>
 * <li>all necessarily unique {@link UUID} objects within a {@link Communication}
 * that should be unique are truly unique,</li>
 * <li>all EntitySet EntityMentionSet UUID pointers are actually present
 * in the communication,</li>
 * <li>all mentionIDs of Entity objects are actually present in the
 * EntityMentionSets of the communication</li>
 * </ul>
 * An {@link InvalidConcreteStructException} is thrown if as soon as a check fails.
 */
public class NecessarilyUniqueUUIDCommunication extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Communication>
    implements PowerCommunication {

  private static final Logger LOGGER = LoggerFactory.getLogger(NecessarilyUniqueUUIDCommunication.class);

  protected final ImmutableSet<ValidUUID> entityMentionUUIDSet;

  private final ImmutableMap<ValidUUID, PowerSection> idToSectMap;
  private final ImmutableMap<ValidUUID, PowerSentence> idToSentMap;
  private final ImmutableMap<ValidUUID, PowerTokenization> idToTkzMap;
  private final ImmutableMap<ValidUUID, PowerEntityMentionGroup> idToEMSMap;
  private final ImmutableMap<ValidUUID, PowerEntityGroup> idToESMap;
  private final ImmutableMap<ValidUUID, PowerSituationMentionGroup> idToSitMensMap;
  private final ImmutableMap<ValidUUID, PowerSituationGroup> idToSitsMap;

  /**
   * @param c the {@link Communication} to wrap
   * @throws InvalidConcreteStructException on validation failure
   */
  NecessarilyUniqueUUIDCommunication(final Communication c) throws InvalidConcreteStructException {
    super(c, c.getUuid());
    LOGGER.debug("Validating communication: {} [{}]", c.getId(), this.getUUID().toString());
    final int nSects = c.getSectionListSize();
    LOGGER.debug("Contains {} sections.", nSects);
    ImmutableMap.Builder<ValidUUID, PowerSection> uuidToSectMap = new ImmutableMap.Builder<>();
    if (nSects > 0)
      for (Section s : c.getSectionList()) {
        PowerSection vs = Sections.empower(s, c);
        uuidToSectMap.put(vs.getUUID(), vs);
      }

    this.idToSectMap = uuidToSectMap.build();

    final int nEMS = c.getEntityMentionSetListSize();
    ImmutableMap.Builder<ValidUUID, PowerEntityMentionGroup> uuidToEMSMap = new ImmutableMap.Builder<>();
    LOGGER.debug("Contains {} EntityMentionSets.", nEMS);
    if (nEMS > 0) {
      for (EntityMentionSet ems : c.getEntityMentionSetList()) {
        PowerEntityMentionGroup vems = EntityMentionGroups.empower(ems, c);
        uuidToEMSMap.put(vems.getUUID(), vems);
      }

    }

    this.idToEMSMap = uuidToEMSMap.build();

    // Validation step: cache list of EntityMention UUIDs
    // to validate Entity pointers later on
    Builder<ValidUUID> b = ImmutableSet.builder();
    this.idToEMSMap.values()
        .stream()
        .flatMap(vems -> vems.getEntityList().stream())
        .map(PowerEntityMention::getUUID)
        .forEach(b::add);
    this.entityMentionUUIDSet = b.build();

    // Validate EntitySet ptrs against these
    LOGGER.debug("EntityMentionSet UUIDs (stream):");
    Builder<ValidUUID> emsUUIDBuilder = ImmutableSet.builder();
    this.idToEMSMap.values()
        .stream()
        .map(PowerEntityMentionGroup::getUUID)
        .forEach(emsUUIDBuilder::add);
    ImmutableSet<ValidUUID> vemsUUIDSet = emsUUIDBuilder.build();
    LOGGER.debug("EMS ID set contains {} items.", vemsUUIDSet.size());

    final int nES = c.getEntitySetListSize();
    LOGGER.debug("Contains {} EntitySets.", nES);
    ImmutableMap.Builder<ValidUUID, PowerEntityGroup> uuidToESMap = new ImmutableMap.Builder<>();
    if (nES > 0) {
      for (EntitySet es : c.getEntitySetList()) {
        PowerEntityGroup ves = EntityGroups.validate(es);
        uuidToESMap.put(ves.getUUID(), ves);
        if (ves.getMentionSetUUID().isPresent()) {
          ValidUUID ptr = ves.getMentionSetUUID().get();
          LOGGER.debug("Checking pointer: {}", ptr.toString());
          if (!vemsUUIDSet.contains(ptr))
            throw new InvalidConcreteStructException("At least one EntitySet "
                + "EntityMentionSet UUID pointer is not present in this communication.");
        }

        List<Set<ValidUUID>> mentionUUIDSetList = ves.getEntityList().stream()
            .map(PowerEntity::getMentionIDSet)
            .collect(Collectors.toList());

        // Assert that all pointers of mentions are actually
        // represented inside the entity mentions in this communication
        for (Set<ValidUUID> ve : mentionUUIDSetList)
          if (!this.entityMentionUUIDSet.containsAll(ve))
            throw new InvalidConcreteStructException("At least one Entity UUID Mention pointer "
                + "does not point to a mention in this Communication.");
      }
    }

    this.idToESMap = uuidToESMap.build();

    // TODO
    this.idToSentMap = ImmutableMap.of();
    this.idToTkzMap = ImmutableMap.of();
    this.idToSitMensMap = ImmutableMap.of();
    this.idToSitsMap = ImmutableMap.of();
  }

  @Override
  public Map<ValidUUID, PowerSection> getIdToSectionMap() {
    return this.idToSectMap;
  }

  @Override
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap() {
    return this.idToSentMap;
  }

  @Override
  public Map<ValidUUID, PowerTokenization> getIdToTokenizationMap() {
    return this.idToTkzMap;
  }

  @Override
  public Map<ValidUUID, PowerEntityMentionGroup> getIdToEntityMentionsMap() {
    return this.idToEMSMap;
  }

  @Override
  public Map<ValidUUID, PowerSituationMentionGroup> getIdToSituationMentionsMap() {
    return this.idToSitMensMap;
  }

  @Override
  public Map<ValidUUID, PowerEntityGroup> getIdToEntitiesMap() {
    return this.idToESMap;
  }

  @Override
  public Map<ValidUUID, PowerSituationGroup> getIdToSituationsMap() {
    return this.idToSitsMap;
  }
}
