/*
 * Copyright 2012-2016 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */
package edu.jhu.hlt.concrete.validation.ff.structure;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.google.common.collect.ImmutableMap.Builder;

import edu.jhu.hlt.concrete.TaggedToken;
import edu.jhu.hlt.concrete.validation.ff.ConfidenceImpl;
import edu.jhu.hlt.concrete.validation.ff.Confidences;
import edu.jhu.hlt.concrete.validation.ff.InvalidConcreteStructException;

/**
 * An implementation of {@link ValidTaggedToken} with some strong assumptions
 * made about the validity of the underlying structure. <br>
 * <br>
 * The following are considered invalid data by this implementation and will
 * result in a {@link InvalidConcreteStructException} being thrown.
 * <ul>
 * <li><code>tokenIndex</code> is absent. This is an optional field, but its
 * absence is considered an error case by this class.</li>
 * <li>if either <code>tagList</code> or <code>confidenceList</code> is set, but
 * not both</li>
 * <li>if both <code>tagList</code> and <code>confidenceList</code> are set but
 * their lengths differ</li>
 * <li>if <code>tagList</code> contains more than one of the same string</li>
 * <li>all <code>double</code> values, if present, are between 0.0 and 1.0,
 * inclusive</li>
 * </ul>
 * The {@link #getTagToConfidenceMap()} function maintains the order of both
 * lists. The second item of the returned map will be the second item in both
 * the <code>tagList</code> and <code>confidenceList</code>.
 */
public class StrictValidTokenTagging implements ValidTaggedToken {

  private final int idx;
  private final Optional<String> tag;
  private final Optional<Double> conf;
  private final Map<String, ConfidenceImpl> tagToConfMap;

  /**
   * @throws InvalidConcreteStructException
   *           on invalid data. See class documentation for information about
   *           what is considered valid data.
   */
  StrictValidTokenTagging(final TaggedToken tt) throws InvalidConcreteStructException {
    if (!tt.isSetTokenIndex())
      throw new InvalidConcreteStructException("Token index is not set: considered an error by this impl.");
    this.idx = tt.getTokenIndex();
    this.tag = Optional.ofNullable(tt.getTag());
    if (tt.isSetConfidence())
      this.conf = Confidences.validate(tt.getConfidence()).getConfidence();
    else
      this.conf = Optional.empty();

    Builder<String, ConfidenceImpl> b = new Builder<>();

    // Possible exit points:
    // one list is set, the other isn't
    // list sizes are not equal
    // multiple equal elements in tagList if set
    if (tt.isSetTagList()) {
      // If tag list is set, but conf list isn't, throw.
      if (!tt.isSetConfidenceList())
        throw new InvalidConcreteStructException("TagList is set, but confidence list is unset.");
      final int tls = tt.getTagListSize();
      final int cls = tt.getConfidenceListSize();
      // if list sizes are not ==, throw.
      if (tls != cls)
        throw new InvalidConcreteStructException("TagList size is different from confidence list size.");

      List<String> tl = tt.getTagList();
      // if tagList contains multiple == elements, throw.
      if (new HashSet<String>(tl).size() != tls)
        throw new InvalidConcreteStructException("TagList contains duplicate tags.");

      List<Double> dl = tt.getConfidenceList();
      for (int i = 0; i < tls; i++) {
        String k = tl.get(i);
        double d = dl.get(i);
        b.put(k, Confidences.validate(d));
      }
    } else {
      // if tag list is unset, but conf list is set, throw.
      if (tt.isSetConfidenceList())
        throw new InvalidConcreteStructException("confidenceList is set, but tagList is unset.");
    }

    this.tagToConfMap = b.build();
  }

  @Override
  public Optional<Double> getConfidence() {
    return this.conf;
  }

  @Override
  public int getIndex() {
    return this.idx;
  }

  @Override
  public Optional<String> getBestTag() {
    return this.tag;
  }

  @Override
  public Map<String, ConfidenceImpl> getTagToConfidenceMap() {
    return this.tagToConfMap;
  }
}
