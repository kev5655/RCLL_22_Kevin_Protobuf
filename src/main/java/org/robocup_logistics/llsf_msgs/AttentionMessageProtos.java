// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: AttentionMessage.proto

package org.robocup_logistics.llsf_msgs;

public final class AttentionMessageProtos {
  private AttentionMessageProtos() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  public interface AttentionMessageOrBuilder extends
      // @@protoc_insertion_point(interface_extends:llsf_msgs.AttentionMessage)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return Whether the message field is set.
     */
    boolean hasMessage();
    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return The message.
     */
    java.lang.String getMessage();
    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return The bytes for message.
     */
    com.google.protobuf.ByteString
        getMessageBytes();

    /**
     * <pre>
     * Time (sec) the msg should be visible
     * </pre>
     *
     * <code>optional float time_to_show = 2;</code>
     * @return Whether the timeToShow field is set.
     */
    boolean hasTimeToShow();
    /**
     * <pre>
     * Time (sec) the msg should be visible
     * </pre>
     *
     * <code>optional float time_to_show = 2;</code>
     * @return The timeToShow.
     */
    float getTimeToShow();

    /**
     * <pre>
     * if the message only regards one team
     * </pre>
     *
     * <code>optional .llsf_msgs.Team team_color = 3;</code>
     * @return Whether the teamColor field is set.
     */
    boolean hasTeamColor();
    /**
     * <pre>
     * if the message only regards one team
     * </pre>
     *
     * <code>optional .llsf_msgs.Team team_color = 3;</code>
     * @return The teamColor.
     */
    org.robocup_logistics.llsf_msgs.TeamProtos.Team getTeamColor();
  }
  /**
   * Protobuf type {@code llsf_msgs.AttentionMessage}
   */
  public static final class AttentionMessage extends
      com.google.protobuf.GeneratedMessageV3 implements
      // @@protoc_insertion_point(message_implements:llsf_msgs.AttentionMessage)
      AttentionMessageOrBuilder {
  private static final long serialVersionUID = 0L;
    // Use AttentionMessage.newBuilder() to construct.
    private AttentionMessage(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
      super(builder);
    }
    private AttentionMessage() {
      message_ = "";
      teamColor_ = 0;
    }

    @java.lang.Override
    @SuppressWarnings({"unused"})
    protected java.lang.Object newInstance(
        UnusedPrivateParameter unused) {
      return new AttentionMessage();
    }

    @java.lang.Override
    public final com.google.protobuf.UnknownFieldSet
    getUnknownFields() {
      return this.unknownFields;
    }
    private AttentionMessage(
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
            case 10: {
              com.google.protobuf.ByteString bs = input.readBytes();
              bitField0_ |= 0x00000001;
              message_ = bs;
              break;
            }
            case 21: {
              bitField0_ |= 0x00000002;
              timeToShow_ = input.readFloat();
              break;
            }
            case 24: {
              int rawValue = input.readEnum();
                @SuppressWarnings("deprecation")
              org.robocup_logistics.llsf_msgs.TeamProtos.Team value = org.robocup_logistics.llsf_msgs.TeamProtos.Team.valueOf(rawValue);
              if (value == null) {
                unknownFields.mergeVarintField(3, rawValue);
              } else {
                bitField0_ |= 0x00000004;
                teamColor_ = rawValue;
              }
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
      return internal_static_llsf_msgs_AttentionMessage_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return internal_static_llsf_msgs_AttentionMessage_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.class, org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.Builder.class);
    }

    /**
     * Protobuf enum {@code llsf_msgs.AttentionMessage.CompType}
     */
    public enum CompType
        implements com.google.protobuf.ProtocolMessageEnum {
      /**
       * <code>COMP_ID = 2000;</code>
       */
      COMP_ID(2000),
      /**
       * <code>MSG_TYPE = 2;</code>
       */
      MSG_TYPE(2),
      ;

      /**
       * <code>COMP_ID = 2000;</code>
       */
      public static final int COMP_ID_VALUE = 2000;
      /**
       * <code>MSG_TYPE = 2;</code>
       */
      public static final int MSG_TYPE_VALUE = 2;


      public final int getNumber() {
        return value;
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       * @deprecated Use {@link #forNumber(int)} instead.
       */
      @java.lang.Deprecated
      public static CompType valueOf(int value) {
        return forNumber(value);
      }

      /**
       * @param value The numeric wire value of the corresponding enum entry.
       * @return The enum associated with the given numeric wire value.
       */
      public static CompType forNumber(int value) {
        switch (value) {
          case 2000: return COMP_ID;
          case 2: return MSG_TYPE;
          default: return null;
        }
      }

      public static com.google.protobuf.Internal.EnumLiteMap<CompType>
          internalGetValueMap() {
        return internalValueMap;
      }
      private static final com.google.protobuf.Internal.EnumLiteMap<
          CompType> internalValueMap =
            new com.google.protobuf.Internal.EnumLiteMap<CompType>() {
              public CompType findValueByNumber(int number) {
                return CompType.forNumber(number);
              }
            };

      public final com.google.protobuf.Descriptors.EnumValueDescriptor
          getValueDescriptor() {
        return getDescriptor().getValues().get(ordinal());
      }
      public final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptorForType() {
        return getDescriptor();
      }
      public static final com.google.protobuf.Descriptors.EnumDescriptor
          getDescriptor() {
        return org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.getDescriptor().getEnumTypes().get(0);
      }

      private static final CompType[] VALUES = values();

      public static CompType valueOf(
          com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
          throw new java.lang.IllegalArgumentException(
            "EnumValueDescriptor is not for this type.");
        }
        return VALUES[desc.getIndex()];
      }

      private final int value;

      private CompType(int value) {
        this.value = value;
      }

      // @@protoc_insertion_point(enum_scope:llsf_msgs.AttentionMessage.CompType)
    }

    private int bitField0_;
    public static final int MESSAGE_FIELD_NUMBER = 1;
    private volatile java.lang.Object message_;
    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return Whether the message field is set.
     */
    @java.lang.Override
    public boolean hasMessage() {
      return ((bitField0_ & 0x00000001) != 0);
    }
    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return The message.
     */
    @java.lang.Override
    public java.lang.String getMessage() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        return (java.lang.String) ref;
      } else {
        com.google.protobuf.ByteString bs = 
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        if (bs.isValidUtf8()) {
          message_ = s;
        }
        return s;
      }
    }
    /**
     * <pre>
     * The message to display, be brief!
     * </pre>
     *
     * <code>required string message = 1;</code>
     * @return The bytes for message.
     */
    @java.lang.Override
    public com.google.protobuf.ByteString
        getMessageBytes() {
      java.lang.Object ref = message_;
      if (ref instanceof java.lang.String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        message_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }

    public static final int TIME_TO_SHOW_FIELD_NUMBER = 2;
    private float timeToShow_;
    /**
     * <pre>
     * Time (sec) the msg should be visible
     * </pre>
     *
     * <code>optional float time_to_show = 2;</code>
     * @return Whether the timeToShow field is set.
     */
    @java.lang.Override
    public boolean hasTimeToShow() {
      return ((bitField0_ & 0x00000002) != 0);
    }
    /**
     * <pre>
     * Time (sec) the msg should be visible
     * </pre>
     *
     * <code>optional float time_to_show = 2;</code>
     * @return The timeToShow.
     */
    @java.lang.Override
    public float getTimeToShow() {
      return timeToShow_;
    }

    public static final int TEAM_COLOR_FIELD_NUMBER = 3;
    private int teamColor_;
    /**
     * <pre>
     * if the message only regards one team
     * </pre>
     *
     * <code>optional .llsf_msgs.Team team_color = 3;</code>
     * @return Whether the teamColor field is set.
     */
    @java.lang.Override public boolean hasTeamColor() {
      return ((bitField0_ & 0x00000004) != 0);
    }
    /**
     * <pre>
     * if the message only regards one team
     * </pre>
     *
     * <code>optional .llsf_msgs.Team team_color = 3;</code>
     * @return The teamColor.
     */
    @java.lang.Override public org.robocup_logistics.llsf_msgs.TeamProtos.Team getTeamColor() {
      @SuppressWarnings("deprecation")
      org.robocup_logistics.llsf_msgs.TeamProtos.Team result = org.robocup_logistics.llsf_msgs.TeamProtos.Team.valueOf(teamColor_);
      return result == null ? org.robocup_logistics.llsf_msgs.TeamProtos.Team.CYAN : result;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      if (!hasMessage()) {
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
        com.google.protobuf.GeneratedMessageV3.writeString(output, 1, message_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        output.writeFloat(2, timeToShow_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        output.writeEnum(3, teamColor_);
      }
      unknownFields.writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (((bitField0_ & 0x00000001) != 0)) {
        size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, message_);
      }
      if (((bitField0_ & 0x00000002) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeFloatSize(2, timeToShow_);
      }
      if (((bitField0_ & 0x00000004) != 0)) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(3, teamColor_);
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
      if (!(obj instanceof org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage)) {
        return super.equals(obj);
      }
      org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage other = (org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage) obj;

      if (hasMessage() != other.hasMessage()) return false;
      if (hasMessage()) {
        if (!getMessage()
            .equals(other.getMessage())) return false;
      }
      if (hasTimeToShow() != other.hasTimeToShow()) return false;
      if (hasTimeToShow()) {
        if (java.lang.Float.floatToIntBits(getTimeToShow())
            != java.lang.Float.floatToIntBits(
                other.getTimeToShow())) return false;
      }
      if (hasTeamColor() != other.hasTeamColor()) return false;
      if (hasTeamColor()) {
        if (teamColor_ != other.teamColor_) return false;
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
      if (hasMessage()) {
        hash = (37 * hash) + MESSAGE_FIELD_NUMBER;
        hash = (53 * hash) + getMessage().hashCode();
      }
      if (hasTimeToShow()) {
        hash = (37 * hash) + TIME_TO_SHOW_FIELD_NUMBER;
        hash = (53 * hash) + java.lang.Float.floatToIntBits(
            getTimeToShow());
      }
      if (hasTeamColor()) {
        hash = (37 * hash) + TEAM_COLOR_FIELD_NUMBER;
        hash = (53 * hash) + teamColor_;
      }
      hash = (29 * hash) + unknownFields.hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessageV3
          .parseWithIOException(PARSER, input);
    }
    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parseFrom(
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
    public static Builder newBuilder(org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage prototype) {
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
     * Protobuf type {@code llsf_msgs.AttentionMessage}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:llsf_msgs.AttentionMessage)
        org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessageOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return internal_static_llsf_msgs_AttentionMessage_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return internal_static_llsf_msgs_AttentionMessage_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.class, org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.Builder.class);
      }

      // Construct using org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage.newBuilder()
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
        message_ = "";
        bitField0_ = (bitField0_ & ~0x00000001);
        timeToShow_ = 0F;
        bitField0_ = (bitField0_ & ~0x00000002);
        teamColor_ = 0;
        bitField0_ = (bitField0_ & ~0x00000004);
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return internal_static_llsf_msgs_AttentionMessage_descriptor;
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage getDefaultInstanceForType() {
        return getDefaultInstance();
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage build() {
        org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage buildPartial() {
        org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage result = new org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage(this);
        int from_bitField0_ = bitField0_;
        int to_bitField0_ = 0;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          to_bitField0_ |= 0x00000001;
        }
        result.message_ = message_;
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.timeToShow_ = timeToShow_;
          to_bitField0_ |= 0x00000002;
        }
        if (((from_bitField0_ & 0x00000004) != 0)) {
          to_bitField0_ |= 0x00000004;
        }
        result.teamColor_ = teamColor_;
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
        if (other instanceof org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage) {
          return mergeFrom((org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage other) {
        if (other == getDefaultInstance()) return this;
        if (other.hasMessage()) {
          bitField0_ |= 0x00000001;
          message_ = other.message_;
          onChanged();
        }
        if (other.hasTimeToShow()) {
          setTimeToShow(other.getTimeToShow());
        }
        if (other.hasTeamColor()) {
          setTeamColor(other.getTeamColor());
        }
        this.mergeUnknownFields(other.unknownFields);
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        if (!hasMessage()) {
          return false;
        }
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage parsedMessage = null;
        try {
          parsedMessage = PARSER.parsePartialFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          parsedMessage = (org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage) e.getUnfinishedMessage();
          throw e.unwrapIOException();
        } finally {
          if (parsedMessage != null) {
            mergeFrom(parsedMessage);
          }
        }
        return this;
      }
      private int bitField0_;

      private java.lang.Object message_ = "";
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @return Whether the message field is set.
       */
      public boolean hasMessage() {
        return ((bitField0_ & 0x00000001) != 0);
      }
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @return The message.
       */
      public java.lang.String getMessage() {
        java.lang.Object ref = message_;
        if (!(ref instanceof java.lang.String)) {
          com.google.protobuf.ByteString bs =
              (com.google.protobuf.ByteString) ref;
          java.lang.String s = bs.toStringUtf8();
          if (bs.isValidUtf8()) {
            message_ = s;
          }
          return s;
        } else {
          return (java.lang.String) ref;
        }
      }
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @return The bytes for message.
       */
      public com.google.protobuf.ByteString
          getMessageBytes() {
        java.lang.Object ref = message_;
        if (ref instanceof String) {
          com.google.protobuf.ByteString b = 
              com.google.protobuf.ByteString.copyFromUtf8(
                  (java.lang.String) ref);
          message_ = b;
          return b;
        } else {
          return (com.google.protobuf.ByteString) ref;
        }
      }
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @param value The message to set.
       * @return This builder for chaining.
       */
      public Builder setMessage(
          java.lang.String value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        message_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearMessage() {
        bitField0_ = (bitField0_ & ~0x00000001);
        message_ = getDefaultInstance().getMessage();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * The message to display, be brief!
       * </pre>
       *
       * <code>required string message = 1;</code>
       * @param value The bytes for message to set.
       * @return This builder for chaining.
       */
      public Builder setMessageBytes(
          com.google.protobuf.ByteString value) {
        if (value == null) {
    throw new NullPointerException();
  }
  bitField0_ |= 0x00000001;
        message_ = value;
        onChanged();
        return this;
      }

      private float timeToShow_ ;
      /**
       * <pre>
       * Time (sec) the msg should be visible
       * </pre>
       *
       * <code>optional float time_to_show = 2;</code>
       * @return Whether the timeToShow field is set.
       */
      @java.lang.Override
      public boolean hasTimeToShow() {
        return ((bitField0_ & 0x00000002) != 0);
      }
      /**
       * <pre>
       * Time (sec) the msg should be visible
       * </pre>
       *
       * <code>optional float time_to_show = 2;</code>
       * @return The timeToShow.
       */
      @java.lang.Override
      public float getTimeToShow() {
        return timeToShow_;
      }
      /**
       * <pre>
       * Time (sec) the msg should be visible
       * </pre>
       *
       * <code>optional float time_to_show = 2;</code>
       * @param value The timeToShow to set.
       * @return This builder for chaining.
       */
      public Builder setTimeToShow(float value) {
        bitField0_ |= 0x00000002;
        timeToShow_ = value;
        onChanged();
        return this;
      }
      /**
       * <pre>
       * Time (sec) the msg should be visible
       * </pre>
       *
       * <code>optional float time_to_show = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearTimeToShow() {
        bitField0_ = (bitField0_ & ~0x00000002);
        timeToShow_ = 0F;
        onChanged();
        return this;
      }

      private int teamColor_ = 0;
      /**
       * <pre>
       * if the message only regards one team
       * </pre>
       *
       * <code>optional .llsf_msgs.Team team_color = 3;</code>
       * @return Whether the teamColor field is set.
       */
      @java.lang.Override public boolean hasTeamColor() {
        return ((bitField0_ & 0x00000004) != 0);
      }
      /**
       * <pre>
       * if the message only regards one team
       * </pre>
       *
       * <code>optional .llsf_msgs.Team team_color = 3;</code>
       * @return The teamColor.
       */
      @java.lang.Override
      public org.robocup_logistics.llsf_msgs.TeamProtos.Team getTeamColor() {
        @SuppressWarnings("deprecation")
        org.robocup_logistics.llsf_msgs.TeamProtos.Team result = org.robocup_logistics.llsf_msgs.TeamProtos.Team.valueOf(teamColor_);
        return result == null ? org.robocup_logistics.llsf_msgs.TeamProtos.Team.CYAN : result;
      }
      /**
       * <pre>
       * if the message only regards one team
       * </pre>
       *
       * <code>optional .llsf_msgs.Team team_color = 3;</code>
       * @param value The teamColor to set.
       * @return This builder for chaining.
       */
      public Builder setTeamColor(org.robocup_logistics.llsf_msgs.TeamProtos.Team value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000004;
        teamColor_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <pre>
       * if the message only regards one team
       * </pre>
       *
       * <code>optional .llsf_msgs.Team team_color = 3;</code>
       * @return This builder for chaining.
       */
      public Builder clearTeamColor() {
        bitField0_ = (bitField0_ & ~0x00000004);
        teamColor_ = 0;
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


      // @@protoc_insertion_point(builder_scope:llsf_msgs.AttentionMessage)
    }

    // @@protoc_insertion_point(class_scope:llsf_msgs.AttentionMessage)
    private static final org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage();
    }

    public static org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    @java.lang.Deprecated public static final com.google.protobuf.Parser<AttentionMessage>
        PARSER = new com.google.protobuf.AbstractParser<AttentionMessage>() {
      @java.lang.Override
      public AttentionMessage parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        return new AttentionMessage(input, extensionRegistry);
      }
    };

    public static com.google.protobuf.Parser<AttentionMessage> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<AttentionMessage> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public org.robocup_logistics.llsf_msgs.AttentionMessageProtos.AttentionMessage getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_llsf_msgs_AttentionMessage_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_llsf_msgs_AttentionMessage_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\026AttentionMessage.proto\022\tllsf_msgs\032\nTea" +
      "m.proto\"\206\001\n\020AttentionMessage\022\017\n\007message\030" +
      "\001 \002(\t\022\024\n\014time_to_show\030\002 \001(\002\022#\n\nteam_colo" +
      "r\030\003 \001(\0162\017.llsf_msgs.Team\"&\n\010CompType\022\014\n\007" +
      "COMP_ID\020\320\017\022\014\n\010MSG_TYPE\020\002B9\n\037org.robocup_" +
      "logistics.llsf_msgsB\026AttentionMessagePro" +
      "tos"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          org.robocup_logistics.llsf_msgs.TeamProtos.getDescriptor(),
        });
    internal_static_llsf_msgs_AttentionMessage_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_llsf_msgs_AttentionMessage_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_llsf_msgs_AttentionMessage_descriptor,
        new java.lang.String[] { "Message", "TimeToShow", "TeamColor", });
    org.robocup_logistics.llsf_msgs.TeamProtos.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
