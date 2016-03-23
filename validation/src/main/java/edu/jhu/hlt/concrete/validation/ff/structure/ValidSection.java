package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.List;

import edu.jhu.hlt.concrete.validation.ff.UUIDable;

public interface ValidSection extends UUIDable {
  public List<ValidSentence> getSentences();
}
