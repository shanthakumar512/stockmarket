/**
 * Autogenerated by Avro
 *
 * DO NOT EDIT DIRECTLY
 */
package net.mguenther.gtd.kafka.serialization;

import org.apache.avro.specific.SpecificData;

@SuppressWarnings("all")
@org.apache.avro.specific.AvroGenerated
public class AvroStockDeleted extends org.apache.avro.specific.SpecificRecordBase implements org.apache.avro.specific.SpecificRecord {
  private static final long serialVersionUID = 2083531911418165591L;
  public static final org.apache.avro.Schema SCHEMA$ = new org.apache.avro.Schema.Parser().parse("{\"type\":\"record\",\"name\":\"AvroStockDeleted\",\"namespace\":\"net.mguenther.gtd.kafka.serialization\",\"fields\":[{\"name\":\"comapnyCode\",\"type\":{\"type\":\"string\",\"avro.java.string\":\"String\"}}]}");
  public static org.apache.avro.Schema getClassSchema() { return SCHEMA$; }
  @Deprecated public java.lang.String comapnyCode;

  /**
   * Default constructor.  Note that this does not initialize fields
   * to their default values from the schema.  If that is desired then
   * one should use <code>newBuilder()</code>.
   */
  public AvroStockDeleted() {}

  /**
   * All-args constructor.
   * @param comapnyCode The new value for comapnyCode
   */
  public AvroStockDeleted(java.lang.String comapnyCode) {
    this.comapnyCode = comapnyCode;
  }

  public org.apache.avro.Schema getSchema() { return SCHEMA$; }
  // Used by DatumWriter.  Applications should not call.
  public java.lang.Object get(int field$) {
    switch (field$) {
    case 0: return comapnyCode;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  // Used by DatumReader.  Applications should not call.
  @SuppressWarnings(value="unchecked")
  public void put(int field$, java.lang.Object value$) {
    switch (field$) {
    case 0: comapnyCode = (java.lang.String)value$; break;
    default: throw new org.apache.avro.AvroRuntimeException("Bad index");
    }
  }

  /**
   * Gets the value of the 'comapnyCode' field.
   * @return The value of the 'comapnyCode' field.
   */
  public java.lang.String getComapnyCode() {
    return comapnyCode;
  }

  /**
   * Sets the value of the 'comapnyCode' field.
   * @param value the value to set.
   */
  public void setComapnyCode(java.lang.String value) {
    this.comapnyCode = value;
  }

  /**
   * Creates a new AvroStockDeleted RecordBuilder.
   * @return A new AvroStockDeleted RecordBuilder
   */
  public static net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder newBuilder() {
    return new net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder();
  }

  /**
   * Creates a new AvroStockDeleted RecordBuilder by copying an existing Builder.
   * @param other The existing builder to copy.
   * @return A new AvroStockDeleted RecordBuilder
   */
  public static net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder newBuilder(net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder other) {
    return new net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder(other);
  }

  /**
   * Creates a new AvroStockDeleted RecordBuilder by copying an existing AvroStockDeleted instance.
   * @param other The existing instance to copy.
   * @return A new AvroStockDeleted RecordBuilder
   */
  public static net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder newBuilder(net.mguenther.gtd.kafka.serialization.AvroStockDeleted other) {
    return new net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder(other);
  }

  /**
   * RecordBuilder for AvroStockDeleted instances.
   */
  public static class Builder extends org.apache.avro.specific.SpecificRecordBuilderBase<AvroStockDeleted>
    implements org.apache.avro.data.RecordBuilder<AvroStockDeleted> {

    private java.lang.String comapnyCode;

    /** Creates a new Builder */
    private Builder() {
      super(SCHEMA$);
    }

    /**
     * Creates a Builder by copying an existing Builder.
     * @param other The existing Builder to copy.
     */
    private Builder(net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder other) {
      super(other);
      if (isValidValue(fields()[0], other.comapnyCode)) {
        this.comapnyCode = data().deepCopy(fields()[0].schema(), other.comapnyCode);
        fieldSetFlags()[0] = true;
      }
    }

    /**
     * Creates a Builder by copying an existing AvroStockDeleted instance
     * @param other The existing instance to copy.
     */
    private Builder(net.mguenther.gtd.kafka.serialization.AvroStockDeleted other) {
            super(SCHEMA$);
      if (isValidValue(fields()[0], other.comapnyCode)) {
        this.comapnyCode = data().deepCopy(fields()[0].schema(), other.comapnyCode);
        fieldSetFlags()[0] = true;
      }
    }

    /**
      * Gets the value of the 'comapnyCode' field.
      * @return The value.
      */
    public java.lang.String getComapnyCode() {
      return comapnyCode;
    }

    /**
      * Sets the value of the 'comapnyCode' field.
      * @param value The value of 'comapnyCode'.
      * @return This builder.
      */
    public net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder setComapnyCode(java.lang.String value) {
      validate(fields()[0], value);
      this.comapnyCode = value;
      fieldSetFlags()[0] = true;
      return this;
    }

    /**
      * Checks whether the 'comapnyCode' field has been set.
      * @return True if the 'comapnyCode' field has been set, false otherwise.
      */
    public boolean hasComapnyCode() {
      return fieldSetFlags()[0];
    }


    /**
      * Clears the value of the 'comapnyCode' field.
      * @return This builder.
      */
    public net.mguenther.gtd.kafka.serialization.AvroStockDeleted.Builder clearComapnyCode() {
      comapnyCode = null;
      fieldSetFlags()[0] = false;
      return this;
    }

    @Override
    public AvroStockDeleted build() {
      try {
        AvroStockDeleted record = new AvroStockDeleted();
        record.comapnyCode = fieldSetFlags()[0] ? this.comapnyCode : (java.lang.String) defaultValue(fields()[0]);
        return record;
      } catch (Exception e) {
        throw new org.apache.avro.AvroRuntimeException(e);
      }
    }
  }

  private static final org.apache.avro.io.DatumWriter
    WRITER$ = new org.apache.avro.specific.SpecificDatumWriter(SCHEMA$);

  @Override public void writeExternal(java.io.ObjectOutput out)
    throws java.io.IOException {
    WRITER$.write(this, SpecificData.getEncoder(out));
  }

  private static final org.apache.avro.io.DatumReader
    READER$ = new org.apache.avro.specific.SpecificDatumReader(SCHEMA$);

  @Override public void readExternal(java.io.ObjectInput in)
    throws java.io.IOException {
    READER$.read(this, SpecificData.getDecoder(in));
  }

}
