/*
 * Copyright 2012-2014 Johns Hopkins University HLTCOE. All rights reserved.
 * This software is released under the 2-clause BSD license.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation;

import java.util.Iterator;

import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.Section;
import edu.jhu.hlt.concrete.SectionSegmentation;

/**
 * @author max
 *
 */
public class ValidatableSectionSegmentation extends AbstractAnnotation<SectionSegmentation> {

  /**
   *
   */
  public ValidatableSectionSegmentation(SectionSegmentation annot) {
    super(annot);
  }

  /*
   * (non-Javadoc)
   * @see edu.jhu.hlt.rebar.ballast.validation.AbstractAnnotation#isValid(edu.jhu.hlt.concrete.Communication)
   */
  @Override
  protected boolean isValidWithComm(Communication c) {
    boolean valid = true;
    Iterator<Section> sects = this.annotation.getSectionListIterator();
    while (valid && sects.hasNext()) {
      Section s = sects.next();
      valid = this.printStatus("Section must be valid wrt comm", new ValidatableSection(s).validate(c));
    }

    return valid;
  }

  @Override
  public boolean isValid() {
    return
        // Rebar depends upon valid metadata.
        this.printStatus("Metadata must be set", this.annotation.isSetMetadata())
        && this.printStatus("Metadata must be valid", new ValidatableMetadata(this.annotation.getMetadata()).isValid())
        // UUID string must be a UUID string.
        && this.validateUUID(this.annotation.getUuid())
        // Sections must exist.
        && this.printStatus("Sections must exist", this.annotation.isSetSectionList())
        // Section size must be >0.
        && this.printStatus("Section size must be >0", this.annotation.getSectionListSize() > 0);
  }
}
