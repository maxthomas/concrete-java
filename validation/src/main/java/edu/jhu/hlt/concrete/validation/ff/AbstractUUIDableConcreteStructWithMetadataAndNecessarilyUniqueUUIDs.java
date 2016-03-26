/**
 *
 */
package edu.jhu.hlt.concrete.validation.ff;

import org.apache.thrift.TBase;
import org.apache.thrift.TFieldIdEnum;

import edu.jhu.hlt.concrete.AnnotationMetadata;
import edu.jhu.hlt.concrete.UUID;

/**
 *
 */
public abstract class AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs<T extends TBase<T, ? extends TFieldIdEnum>>
    extends AbstractConcreteStructWithNecessarilyUniqueUUIDs<T> implements FlattenedMetadataWithValidUUID {

  protected final FlattenedMetadata md;

  protected AbstractUUIDableConcreteStructWithMetadataAndNecessarilyUniqueUUIDs(T t, UUID uuid, AnnotationMetadata md)
      throws InvalidConcreteStructException {
    super(t, uuid);
    this.md = Metadata.validate(md);
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata#getTool()
   */
  @Override
  public final String getTool() {
    return this.md.getTool();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata#getKBest()
   */
  @Override
  public int getKBest() {
    return this.md.getKBest();
  }

  /* (non-Javadoc)
   * @see edu.jhu.hlt.concrete.validation.ff.FlattenedMetadata#getTimestamp()
   */
  @Override
  public long getTimestamp() {
    return this.md.getTimestamp();
  }
}
