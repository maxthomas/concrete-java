package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.Map;

import edu.jhu.hlt.concrete.Tokenization;
import edu.jhu.hlt.concrete.validation.ff.AbstractConcreteStructWithNecessarilyUniqueUUIDs;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;
import edu.jhu.hlt.concrete.validation.ff.ValidUUID;

public class NecessarilyUniqueUUIDTokenization extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<Tokenization>
    implements ValidTokenization {

  private Map<ValidUUID, ValidParse> parses;
  private Map<ValidUUID, ValidDependencyParse> dps;
  private Map<ValidUUID, ValidTokenTagging> tts;

  NecessarilyUniqueUUIDTokenization(Tokenization tkz) throws InvalidConcreteStructException {
    super(tkz, tkz.getUuid());

    this.parses = Parses.extract(tkz);
    this.dps = DependencyParses.extract(tkz);
    this.tts = TokenTaggings.extract(tkz);
  }
}
