package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.Sentence;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.PowerTextSpan;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDSection extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Section>
    implements PowerSection {

  private final ImmutableMap<ValidUUID, PowerSentence> stl;

  NecessarilyUniqueUUIDSection(Section s, Communication c) throws InvalidConcreteStructException {
    super(s, s.getUuid());

    final int nSents = s.getSentenceListSize();
    Builder<ValidUUID, PowerSentence> b = new Builder<>();
    if (nSents > 0)
      for (Sentence st : s.getSentenceList()) {
        PowerSentence vst = Sentences.empower(st, c);
        b.put(vst.getUUID(), vst);
      }

    this.stl = b.build();
  }

  @Override
  public Optional<PowerTextSpan> getTextSpan() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Map<ValidUUID, PowerSentence> getIdToSentenceMap() {
    return this.stl;
  }

  @Override
  public String getKind() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Optional<String> getLabel() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public List<Integer> getNumbers() {
    // TODO Auto-generated method stub
    return null;
  }
}
