/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff;

import java.util.Optional;

/**
 * Interface that represents an object that can return an optional
 * {@link PowerTextSpan} object.
 *
 * @see PowerTextSpan
 */
public interface PowerTextSpannable {
  public Optional<PowerTextSpan> getTextSpan();
}