// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: Time.proto

package org.robocup_logistics.llsf_msgs;

public final class TimeProtos {
  private TimeProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface TimeOrBuilder extends
      // @@protoc_insertion_point(interface_extends:llsf_msgs.Time)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * Time in seconds since the Unix epoch
     * in UTC or seconds part of duration
     * </pre>
     *
     * <code>required int64 sec = 1;</code>
     * @return Whether the sec field is set.
     */
    boolean hasSec();
    /**
     * <pre>
     * Time in seconds since the Unix epoch
     * in UTC or seconds part of duration
     * </pre>
     *
     * <code>required int64 sec = 1;</code>
     * @return The sec.
     */
    long getSec();

    /**
     * <pre>
     * Nano seconds after seconds for a time
     * or nanoseconds part for duration
     * </pre>
     *
     * <code>required int64 nsec = 2;</code>
     * @return Whether the nsec field is set.
     */
    boolean hasNsec();
    /**
     * <pre>
     * Nano seconds after seconds for a time
     * or nanoseconds part for duration
     * </pre>
     *
     * <code>required int64 nsec = 2;</code>
     * @return The nsec.
     */
    long getNsec();
  }
  /**
   * <pre>
   * Time stamp and duration structure.
   * Can be used for absolute times or
   * durations alike.
   * </pre>
   *
   * Protobuf type {@code llsf_msgs.Time}
   */
  public static final class Time extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:llsf_msgs.Time)
      TimeOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use Time.newBuilder() to construct.
    private Time(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private Time() {
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new Time();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private Time(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      this();
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      int mutable_bitField0_ = 0;
      com.google.protobuf.UnknownFieldSet.Builder unknownFields =
          com.google.protobuf.UnknownFieldSet.newBuilder();
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 8: {
              bitField0_ |= 0x00000001;
              sec_ = input.readInt64();
              break;
            }
            case 16: {
              bitField0_ |= 0x00000002;
              nsec_ = input.readInt64();
              break;
            }
            default: {
              if (!parseUnknownField(
                  input, unknownFields, extensionRegistry, tag)) {
                done = true;
              }
              break;
            }
          }
        }
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.setUnfinishedMessage(this);
      } catch (java.io.IOException e) {
        throw new com.google.protobuf.InvalidProtocolBufferException(
            e).setUnfinishedMessage(this);
      } finally {
        this.unknownFields = unknownFields.build();
        makeExtensionsImmutable();
      }
    }
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return internal_static_llsf_msgs_Time_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return internal_static_llsf_msgs_Time_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.robocup_logistics.llsf_msgs.TimeProtos.Time.class, org.robocup_logistics.llsf_msgs.TimeProtos.Time.Builder.class);
    }

    private int bitField0_;
    public static final int SEC_FIELD_NUMBER = 1;
    private long sec_;
    /**
     * <pre>
     * Time in seconds since the Unix epoch
     * in UTC or seconds part of duration
     * </pre>
     *
     * <code>required int64 sec = 1;</code>
     * @return Whether the sec field is set.
     */
    @java.lang.Override
    public boolean hasSec() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     * Time in seconds since the Unix epoch
     * in UTC or seconds part of duration
     * </pre>
     *
     * <code>required int64 sec = 1;</code>
     * @return The sec.
     */
    @java.lang.Override
    public long getSec() {
      return sec_;
    }

    public static final int NSEC_FIELD_NUMBER = 2;
    private long nsec_;
    /**
     * <pre>
     * Nano seconds after seconds for a time
     * or nanoseconds part for duration
     * </pre>
     *
     * <code>required int64 nsec = 2;</code>
     * @return Whether the nsec field is set.
     */
    @java.lang.Override
    public boolean hasNsec() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <pre>
     * Nano seconds after seconds for a time
     * or nanoseconds part for duration
     * </pre>
     *
     * <code>required int64 nsec = 2;</code>
     * @return The nsec.
     */
    @java.lang.Override
    public long getNsec() {
      return nsec_;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasSec()) {
        memoizedIsInitialized = 0;
        return false;
      }
      if (!hasNsec()) {
        memoizedIsInitialized = 0;
        return false;
      }
      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (((bitField0_ & 0x00000001) != 0)) {
        output.writeInt64(1, sec_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeInt64(2, nsec_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(1, sec_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt64Size(2, nsec_);
      }
      size += unknownFields.getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof org.robocup_logistics.llsf_msgs.TimeProtos.Time)) {
        return super.equals(obj);
      }
      org.robocup_logistics.llsf_msgs.TimeProtos.Time other = (org.robocup_logistics.llsf_msgs.TimeProtos.Time) obj;

      if (hasSec() != other.hasSec()) return false;
      if (hasSec()) {
        if (getSec()
            != other.getSec()) return false;
      }
      if (hasNsec() != other.hasNsec()) return false;
      if (hasNsec()) {
        if (getNsec()
            != other.getNsec()) return false;
      }
      if (!unknownFields.equals(other.unknownFields)) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      if (hasSec()) {
        hash = (37 * hash) + SEC_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getSec());
      }
      if (hasNsec()) {
        hash = (37 * hash) + NSEC_FIELD_NUMBER;
        hash = (53 * hash) + com.google.protobuf.Internal.hashLong(
            getNsec());
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(org.robocup_logistics.llsf_msgs.TimeProtos.Time prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * <pre>
     * Time stamp and duration structure.
     * Can be used for absolute times or
     * durations alike.
     * </pre>
     *
     * Protobuf type {@code llsf_msgs.Time}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:llsf_msgs.Time)
        org.robocup_logistics.llsf_msgs.TimeProtos.TimeOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return internal_static_llsf_msgs_Time_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return internal_static_llsf_msgs_Time_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.robocup_logistics.llsf_msgs.TimeProtos.Time.class, org.robocup_logistics.llsf_msgs.TimeProtos.Time.Builder.class);
      }

      // Construct using org.robocup_logistics.llsf_msgs.TimeProtos.Time.newBuilder()
      private Builder() {
        maybeForceBuilderInitialization();
      }

      private Builder(
          com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
        super(parent);
        maybeForceBuilderInitialization();
      }
      private void maybeForceBuilderInitialization() {
        if (com.google.protobuf.GeneratedMessageV3
                .alwaysUseFieldBuilders) {
        }
      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        sec_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000001);
        nsec_ = 0L;
        bitField0_ = (bitField0_ & ~0x00000002);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return internal_static_llsf_msgs_Time_descriptor;
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.TimeProtos.Time getDefaultInstanceForType() {
        return getDefaultInstance();
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.TimeProtos.Time build() {
        org.robocup_logistics.llsf_msgs.TimeProtos.Time result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.TimeProtos.Time buildPartial() {
        org.robocup_logistics.llsf_msgs.TimeProtos.Time result = new org.robocup_logistics.llsf_msgs.TimeProtos.Time(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.sec_ = sec_;
          to_bitField0_ |= 0x00000001;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.nsec_ = nsec_;
          to_bitField0_ |= 0x00000002;
        }
        result.bitField0_ = to_bitField0_;
        onBuilt();
        return result;
      }

      @java.lang.Override
      public Builder clone() {
        return super.clone();
      }
      @java.lang.Override
      public Builder setField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.setField(field, value);
      }
      @java.lang.Override
      public Builder clearField(
          com.google.protobuf.Descriptors.FieldDescriptor field) {
        return super.clearField(field);
      }
      @java.lang.Override
      public Builder clearOneof(
          com.google.protobuf.Descriptors.OneofDescriptor oneof) {
        return super.clearOneof(oneof);
      }
      @java.lang.Override
      public Builder setRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          int index, java.lang.Object value) {
        return super.setRepeatedField(field, index, value);
      }
      @java.lang.Override
      public Builder addRepeatedField(
          com.google.protobuf.Descriptors.FieldDescriptor field,
          java.lang.Object value) {
        return super.addRepeatedField(field, value);
      }
      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof org.robocup_logistics.llsf_msgs.TimeProtos.Time) {
          return mergeFrom((org.robocup_logistics.llsf_msgs.TimeProtos.Time)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.robocup_logistics.llsf_msgs.TimeProtos.Time other) {
        if (other == getDefaultInstance()) return this;
        if (other.hasSec()) {
          setSec(other.getSec());
        }
        if (other.hasNsec()) {
          setNsec(other.getNsec());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasSec()) {
          return false;
        }
        if (!hasNsec()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.robocup_logistics.llsf_msgs.TimeProtos.Time parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.robocup_logistics.llsf_msgs.TimeProtos.Time) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private long sec_ ;
      /**
       * <pre>
       * Time in seconds since the Unix epoch
       * in UTC or seconds part of duration
       * </pre>
       *
       * <code>required int64 sec = 1;</code>
       * @return Whether the sec field is set.
       */
      @java.lang.Override
      public boolean hasSec() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       * Time in seconds since the Unix epoch
       * in UTC or seconds part of duration
       * </pre>
       *
       * <code>required int64 sec = 1;</code>
       * @return The sec.
       */
      @java.lang.Override
      public long getSec() {
        return sec_;
      }
      /**
       * <pre>
       * Time in seconds since the Unix epoch
       * in UTC or seconds part of duration
       * </pre>
       *
       * <code>required int64 sec = 1;</code>
       * @param value The sec to set.
       * @return This builder for chaining.
       */
      public Builder setSec(long value) {
        bitField0_ |= 0x00000001;
        sec_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Time in seconds since the Unix epoch
       * in UTC or seconds part of duration
       * </pre>
       *
       * <code>required int64 sec = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearSec() {
        bitField0_ = (bitField0_ & ~0x00000001);
        sec_ = 0L;
        onChanged();
        return this;
      }

      private long nsec_ ;
      /**
       * <pre>
       * Nano seconds after seconds for a time
       * or nanoseconds part for duration
       * </pre>
       *
       * <code>required int64 nsec = 2;</code>
       * @return Whether the nsec field is set.
       */
      @java.lang.Override
      public boolean hasNsec() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <pre>
       * Nano seconds after seconds for a time
       * or nanoseconds part for duration
       * </pre>
       *
       * <code>required int64 nsec = 2;</code>
       * @return The nsec.
       */
      @java.lang.Override
      public long getNsec() {
        return nsec_;
      }
      /**
       * <pre>
       * Nano seconds after seconds for a time
       * or nanoseconds part for duration
       * </pre>
       *
       * <code>required int64 nsec = 2;</code>
       * @param value The nsec to set.
       * @return This builder for chaining.
       */
      public Builder setNsec(long value) {
        bitField0_ |= 0x00000002;
        nsec_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Nano seconds after seconds for a time
       * or nanoseconds part for duration
       * </pre>
       *
       * <code>required int64 nsec = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearNsec() {
        bitField0_ = (bitField0_ & ~0x00000002);
        nsec_ = 0L;
        onChanged();
        return this;
      }
      @java.lang.Override
      public final Builder setUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.setUnknownFields(unknownFields);
      }

      @java.lang.Override
      public final Builder mergeUnknownFields(
          final com.google.protobuf.UnknownFieldSet unknownFields) {
        return super.mergeUnknownFields(unknownFields);
      }


      // @@protoc_insertion_point(builder_scope:llsf_msgs.Time)
    }

    // @@protoc_insertion_point(class_scope:llsf_msgs.Time)
    private static final org.robocup_logistics.llsf_msgs.TimeProtos.Time DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.robocup_logistics.llsf_msgs.TimeProtos.Time();
    }

    public static org.robocup_logistics.llsf_msgs.TimeProtos.Time getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<Time>
        PARSER = new com.google.protobuf.AbstractParser<Time>() {
      @java.lang.Override
      public Time parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new Time(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<Time> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Time> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.robocup_logistics.llsf_msgs.TimeProtos.Time getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_llsf_msgs_Time_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_llsf_msgs_Time_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\nTime.proto\022\tllsf_msgs\"!\n\004Time\022\013\n\003sec\030\001" +
      " \002(\003\022\014\n\004nsec\030\002 \002(\003B-\n\037org.robocup_logist" +
      "ics.llsf_msgsB\nTimeProtos"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_llsf_msgs_Time_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_llsf_msgs_Time_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_llsf_msgs_Time_descriptor,
        new java.lang.String[] { "Sec", "Nsec", });
  }

  // @@protoc_insertion_point(outer_class_scope)
}