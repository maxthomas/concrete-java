/*
 * Copyright 2012-2014 Johns Hopkins University HLTCOE. All rights reserved.
 * See LICENSE in the project root directory.
 */

package edu.jhu.hlt.concrete.serialization.iterators;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import edu.jhu.hlt.acute.iterators.tar.TarGzArchiveEntryByteIterator;
import edu.jhu.hlt.concrete.Communication;
import edu.jhu.hlt.concrete.serialization.CommunicationSerializer;
import edu.jhu.hlt.concrete.serialization.CompactCommunicationSerializer;
import edu.jhu.hlt.concrete.util.ConcreteException;

/**
 * @author max
 *
 */
public class TarGzArchiveEntryCommunicationIterator implements Iterator<Communication> {

  private final TarGzArchiveEntryByteIterator byteIter;
  protected final CommunicationSerializer cs = new CompactCommunicationSerializer();
  
  /**
   * @throws IOException 
   * 
   */
  public TarGzArchiveEntryCommunicationIterator(InputStream is) throws IOException {
    this.byteIter = new TarGzArchiveEntryByteIterator(is);
  }

  @Override
  public boolean hasNext() {
    return this.byteIter.hasNext();
  }
  
  @Override
  public Communication next() {
    try {
      return this.cs.fromBytes(this.byteIter.next());
    } catch (ConcreteException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void remove() {
    throw new UnsupportedOperationException("You can't remove with this iterator.");
  }
}
