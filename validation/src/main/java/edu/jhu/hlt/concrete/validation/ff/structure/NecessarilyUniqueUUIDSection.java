package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.Sentence;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDSection extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Section>
    implements ValidSection {

  private final ImmutableMap<ValidUUID, ValidSentence> stl;

  private ImmutableList<ValidSentence> sL;

  NecessarilyUniqueUUIDSection(Section s) throws InvalidConcreteStructException {
    super(s, s.getUuid());

    final int nSents = s.getSentenceListSize();
    Builder<ValidUUID, ValidSentence> b = new Builder<>();
    if (nSents > 0)
      for (Sentence st : s.getSentenceList()) {
        ValidSentence vst = Sentences.validate(st);
        b.put(vst.getUUID(), vst);
      }

    this.stl = b.build();
  }

  @Override
  public List<ValidSentence> getSentences() {
    if (this.sL == null)
      this.sL = ImmutableList.copyOf(stl.values());
    return this.sL;
  }
}
