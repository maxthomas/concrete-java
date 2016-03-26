/**
 *
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.EntityMentionSet;
import edu.jhu.hlt.concrete.EntitySet;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.validation.ff.entity.EntityMentionSets;
import edu.jhu.hlt.concrete.validation.ff.entity.EntitySets;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntity;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntityMention;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntityMentionSet;
import edu.jhu.hlt.concrete.validation.ff.entity.ValidEntitySet;
import edu.jhu.hlt.concrete.validation.ff.situation.ValidSituationMentionSet;
import edu.jhu.hlt.concrete.validation.ff.situation.ValidSituationSet;
import edu.jhu.hlt.concrete.validation.ff.structure.Sections;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidSection;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidSentence;
import edu.jhu.hlt.concrete.validation.ff.structure.ValidTokenization;

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

  private final ImmutableMap<ValidUUID, ValidSection> idToSectMap;
  private final ImmutableMap<ValidUUID, ValidSentence> idToSentMap;
  private final ImmutableMap<ValidUUID, ValidTokenization> idToTkzMap;
  private final ImmutableMap<ValidUUID, ValidEntityMentionSet> idToEMSMap;
  private final ImmutableMap<ValidUUID, ValidEntitySet> idToESMap;
  private final ImmutableMap<ValidUUID, ValidSituationMentionSet> idToSitMensMap;
  private final ImmutableMap<ValidUUID, ValidSituationSet> idToSitsMap;

  //////////////////////////////////////////////////////
  // The following are lazily computed upon invocation
  // of the methods that use them
  //////////////////////////////////////////////////////
  private ImmutableList<ValidSection> sList = null;
  private ImmutableList<ValidEntityMentionSet> emsL = null;
  private ImmutableList<ValidEntitySet> esL = null;

  /**
   * @param c the {@link Communication} to wrap
   * @throws InvalidConcreteStructException on validation failure
   */
  NecessarilyUniqueUUIDCommunication(final Communication c) throws InvalidConcreteStructException {
    super(c, c.getUuid());
    LOGGER.debug("Validating communication: {} [{}]", c.getId(), this.getUUID().toString());
    final int nSects = c.getSectionListSize();
    LOGGER.debug("Contains {} sections.", nSects);
    ImmutableMap.Builder<ValidUUID, ValidSection> uuidToSectMap = new ImmutableMap.Builder<>();
    if (nSects > 0)
      for (Section s : c.getSectionList()) {
        ValidSection vs = Sections.validate(s);
        uuidToSectMap.put(vs.getUUID(), vs);
      }

    this.idToSectMap = uuidToSectMap.build();

    final int nEMS = c.getEntityMentionSetListSize();
    ImmutableMap.Builder<ValidUUID, ValidEntityMentionSet> uuidToEMSMap = new ImmutableMap.Builder<>();
    LOGGER.debug("Contains {} EntityMentionSets.", nEMS);
    if (nEMS > 0) {
      for (EntityMentionSet ems : c.getEntityMentionSetList()) {
        ValidEntityMentionSet vems = EntityMentionSets.validate(ems);
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
        .map(ValidEntityMention::getUUID)
        .forEach(b::add);
    this.entityMentionUUIDSet = b.build();

    // Validate EntitySet ptrs against these
    LOGGER.debug("EntityMentionSet UUIDs (stream):");
    Builder<ValidUUID> emsUUIDBuilder = ImmutableSet.builder();
    this.idToEMSMap.values()
        .stream()
        .map(ValidEntityMentionSet::getUUID)
        .forEach(emsUUIDBuilder::add);
    ImmutableSet<ValidUUID> vemsUUIDSet = emsUUIDBuilder.build();
    LOGGER.debug("EMS ID set contains {} items.", vemsUUIDSet.size());

    final int nES = c.getEntitySetListSize();
    LOGGER.debug("Contains {} EntitySets.", nES);
    ImmutableMap.Builder<ValidUUID, ValidEntitySet> uuidToESMap = new ImmutableMap.Builder<>();
    if (nES > 0) {
      for (EntitySet es : c.getEntitySetList()) {
        ValidEntitySet ves = EntitySets.validate(es);
        uuidToESMap.put(ves.getUUID(), ves);
        if (ves.getMentionSetUUID().isPresent()) {
          ValidUUID ptr = ves.getMentionSetUUID().get();
          LOGGER.debug("Checking pointer: {}", ptr.toString());
          if (!vemsUUIDSet.contains(ptr))
            throw new InvalidConcreteStructException("At least one EntitySet "
                + "EntityMentionSet UUID pointer is not present in this communication.");
        }

        List<Set<ValidUUID>> mentionUUIDSetList = ves.getEntityList().stream()
            .map(ValidEntity::getMentionIDSet)
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
    this.esL = ImmutableList.copyOf(this.idToESMap.values());

    // TODO
    this.idToSentMap = ImmutableMap.of();
    this.idToTkzMap = ImmutableMap.of();
    this.idToSitMensMap = ImmutableMap.of();
    this.idToSitsMap = ImmutableMap.of();
  }

  @Override
  public List<ValidEntitySet> getEntitySetList() {
    if (this.esL == null)
      this.esL = ImmutableList.copyOf(this.idToESMap.values());
    return this.esL;
  }

  @Override
  public List<ValidEntityMentionSet> getEntityMentionSetList() {
    if (this.emsL == null)
      this.emsL = ImmutableList.copyOf(this.emsL);
    return this.emsL;
  }

  @Override
  public List<ValidSection> getSectionList() {
    if (this.sList == null)
      this.sList = ImmutableList.copyOf(this.idToSectMap.values());
    return this.sList;
  }

  @Override
  public Optional<ValidEntitySet> getEntitySet(ValidUUID uuid) {
    return Optional.ofNullable(this.idToESMap.get(uuid));
  }

  @Override
  public Optional<ValidEntityMentionSet> getEntityMentionSet(ValidUUID uuid) {
    return Optional.ofNullable(this.idToEMSMap.get(uuid));
  }

  @Override
  public Optional<ValidSection> getSection(ValidUUID uuid) {
    return Optional.ofNullable(this.idToSectMap.get(uuid));
  }

  @Override
  public List<ValidSituationSet> getSituationSetList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ValidSituationMentionSet> getSituationMentionSetList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ValidSentence> getSentenceList() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<ValidTokenization> getTokenizationList() {
    // TODO Auto-generated method stub
    return null;
  }
}
