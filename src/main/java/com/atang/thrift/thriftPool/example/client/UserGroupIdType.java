/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.atang.thrift.thriftPool.example.client;

@SuppressWarnings({"cast", "rawtypes", "serial", "unchecked", "unused"})
@javax.annotation.Generated(value = "Autogenerated by Thrift Compiler (0.10.0)", date = "2017-02-15")
public class UserGroupIdType implements org.apache.thrift.TBase<UserGroupIdType, UserGroupIdType._Fields>, java.io.Serializable, Cloneable, Comparable<UserGroupIdType> {
  private static final org.apache.thrift.protocol.TStruct STRUCT_DESC = new org.apache.thrift.protocol.TStruct("UserGroupIdType");

  private static final org.apache.thrift.protocol.TField USER_GROUP_TYPE_FIELD_DESC = new org.apache.thrift.protocol.TField("userGroupType", org.apache.thrift.protocol.TType.I64, (short)1);
  private static final org.apache.thrift.protocol.TField USER_GROUP_ID_FIELD_DESC = new org.apache.thrift.protocol.TField("userGroupId", org.apache.thrift.protocol.TType.I64, (short)2);

  private static final org.apache.thrift.scheme.SchemeFactory STANDARD_SCHEME_FACTORY = new UserGroupIdTypeStandardSchemeFactory();
  private static final org.apache.thrift.scheme.SchemeFactory TUPLE_SCHEME_FACTORY = new UserGroupIdTypeTupleSchemeFactory();

  public long userGroupType; // required
  public long userGroupId; // required

  /** The set of fields this struct contains, along with convenience methods for finding and manipulating them. */
  public enum _Fields implements org.apache.thrift.TFieldIdEnum {
    USER_GROUP_TYPE((short)1, "userGroupType"),
    USER_GROUP_ID((short)2, "userGroupId");

    private static final java.util.Map<java.lang.String, _Fields> byName = new java.util.HashMap<java.lang.String, _Fields>();

    static {
      for (_Fields field : java.util.EnumSet.allOf(_Fields.class)) {
        byName.put(field.getFieldName(), field);
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, or null if its not found.
     */
    public static _Fields findByThriftId(int fieldId) {
      switch(fieldId) {
        case 1: // USER_GROUP_TYPE
          return USER_GROUP_TYPE;
        case 2: // USER_GROUP_ID
          return USER_GROUP_ID;
        default:
          return null;
      }
    }

    /**
     * Find the _Fields constant that matches fieldId, throwing an exception
     * if it is not found.
     */
    public static _Fields findByThriftIdOrThrow(int fieldId) {
      _Fields fields = findByThriftId(fieldId);
      if (fields == null) throw new java.lang.IllegalArgumentException("Field " + fieldId + " doesn't exist!");
      return fields;
    }

    /**
     * Find the _Fields constant that matches name, or null if its not found.
     */
    public static _Fields findByName(java.lang.String name) {
      return byName.get(name);
    }

    private final short _thriftId;
    private final java.lang.String _fieldName;

    _Fields(short thriftId, java.lang.String fieldName) {
      _thriftId = thriftId;
      _fieldName = fieldName;
    }

    public short getThriftFieldId() {
      return _thriftId;
    }

    public java.lang.String getFieldName() {
      return _fieldName;
    }
  }

  // isset id assignments
  private static final int __USERGROUPTYPE_ISSET_ID = 0;
  private static final int __USERGROUPID_ISSET_ID = 1;
  private byte __isset_bitfield = 0;
  public static final java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> metaDataMap;
  static {
    java.util.Map<_Fields, org.apache.thrift.meta_data.FieldMetaData> tmpMap = new java.util.EnumMap<_Fields, org.apache.thrift.meta_data.FieldMetaData>(_Fields.class);
    tmpMap.put(_Fields.USER_GROUP_TYPE, new org.apache.thrift.meta_data.FieldMetaData("userGroupType", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    tmpMap.put(_Fields.USER_GROUP_ID, new org.apache.thrift.meta_data.FieldMetaData("userGroupId", org.apache.thrift.TFieldRequirementType.DEFAULT, 
        new org.apache.thrift.meta_data.FieldValueMetaData(org.apache.thrift.protocol.TType.I64)));
    metaDataMap = java.util.Collections.unmodifiableMap(tmpMap);
    org.apache.thrift.meta_data.FieldMetaData.addStructMetaDataMap(UserGroupIdType.class, metaDataMap);
  }

  public UserGroupIdType() {
  }

  public UserGroupIdType(
    long userGroupType,
    long userGroupId)
  {
    this();
    this.userGroupType = userGroupType;
    setUserGroupTypeIsSet(true);
    this.userGroupId = userGroupId;
    setUserGroupIdIsSet(true);
  }

  /**
   * Performs a deep copy on <i>other</i>.
   */
  public UserGroupIdType(UserGroupIdType other) {
    __isset_bitfield = other.__isset_bitfield;
    this.userGroupType = other.userGroupType;
    this.userGroupId = other.userGroupId;
  }

  public UserGroupIdType deepCopy() {
    return new UserGroupIdType(this);
  }

  @Override
  public void clear() {
    setUserGroupTypeIsSet(false);
    this.userGroupType = 0;
    setUserGroupIdIsSet(false);
    this.userGroupId = 0;
  }

  public long getUserGroupType() {
    return this.userGroupType;
  }

  public UserGroupIdType setUserGroupType(long userGroupType) {
    this.userGroupType = userGroupType;
    setUserGroupTypeIsSet(true);
    return this;
  }

  public void unsetUserGroupType() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __USERGROUPTYPE_ISSET_ID);
  }

  /** Returns true if field userGroupType is set (has been assigned a value) and false otherwise */
  public boolean isSetUserGroupType() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __USERGROUPTYPE_ISSET_ID);
  }

  public void setUserGroupTypeIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __USERGROUPTYPE_ISSET_ID, value);
  }

  public long getUserGroupId() {
    return this.userGroupId;
  }

  public UserGroupIdType setUserGroupId(long userGroupId) {
    this.userGroupId = userGroupId;
    setUserGroupIdIsSet(true);
    return this;
  }

  public void unsetUserGroupId() {
    __isset_bitfield = org.apache.thrift.EncodingUtils.clearBit(__isset_bitfield, __USERGROUPID_ISSET_ID);
  }

  /** Returns true if field userGroupId is set (has been assigned a value) and false otherwise */
  public boolean isSetUserGroupId() {
    return org.apache.thrift.EncodingUtils.testBit(__isset_bitfield, __USERGROUPID_ISSET_ID);
  }

  public void setUserGroupIdIsSet(boolean value) {
    __isset_bitfield = org.apache.thrift.EncodingUtils.setBit(__isset_bitfield, __USERGROUPID_ISSET_ID, value);
  }

  public void setFieldValue(_Fields field, java.lang.Object value) {
    switch (field) {
    case USER_GROUP_TYPE:
      if (value == null) {
        unsetUserGroupType();
      } else {
        setUserGroupType((java.lang.Long)value);
      }
      break;

    case USER_GROUP_ID:
      if (value == null) {
        unsetUserGroupId();
      } else {
        setUserGroupId((java.lang.Long)value);
      }
      break;

    }
  }

  public java.lang.Object getFieldValue(_Fields field) {
    switch (field) {
    case USER_GROUP_TYPE:
      return getUserGroupType();

    case USER_GROUP_ID:
      return getUserGroupId();

    }
    throw new java.lang.IllegalStateException();
  }

  /** Returns true if field corresponding to fieldID is set (has been assigned a value) and false otherwise */
  public boolean isSet(_Fields field) {
    if (field == null) {
      throw new java.lang.IllegalArgumentException();
    }

    switch (field) {
    case USER_GROUP_TYPE:
      return isSetUserGroupType();
    case USER_GROUP_ID:
      return isSetUserGroupId();
    }
    throw new java.lang.IllegalStateException();
  }

  @Override
  public boolean equals(java.lang.Object that) {
    if (that == null)
      return false;
    if (that instanceof UserGroupIdType)
      return this.equals((UserGroupIdType)that);
    return false;
  }

  public boolean equals(UserGroupIdType that) {
    if (that == null)
      return false;
    if (this == that)
      return true;

    boolean this_present_userGroupType = true;
    boolean that_present_userGroupType = true;
    if (this_present_userGroupType || that_present_userGroupType) {
      if (!(this_present_userGroupType && that_present_userGroupType))
        return false;
      if (this.userGroupType != that.userGroupType)
        return false;
    }

    boolean this_present_userGroupId = true;
    boolean that_present_userGroupId = true;
    if (this_present_userGroupId || that_present_userGroupId) {
      if (!(this_present_userGroupId && that_present_userGroupId))
        return false;
      if (this.userGroupId != that.userGroupId)
        return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int hashCode = 1;

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(userGroupType);

    hashCode = hashCode * 8191 + org.apache.thrift.TBaseHelper.hashCode(userGroupId);

    return hashCode;
  }

  @Override
  public int compareTo(UserGroupIdType other) {
    if (!getClass().equals(other.getClass())) {
      return getClass().getName().compareTo(other.getClass().getName());
    }

    int lastComparison = 0;

    lastComparison = java.lang.Boolean.valueOf(isSetUserGroupType()).compareTo(other.isSetUserGroupType());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserGroupType()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userGroupType, other.userGroupType);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    lastComparison = java.lang.Boolean.valueOf(isSetUserGroupId()).compareTo(other.isSetUserGroupId());
    if (lastComparison != 0) {
      return lastComparison;
    }
    if (isSetUserGroupId()) {
      lastComparison = org.apache.thrift.TBaseHelper.compareTo(this.userGroupId, other.userGroupId);
      if (lastComparison != 0) {
        return lastComparison;
      }
    }
    return 0;
  }

  public _Fields fieldForId(int fieldId) {
    return _Fields.findByThriftId(fieldId);
  }

  public void read(org.apache.thrift.protocol.TProtocol iprot) throws org.apache.thrift.TException {
    scheme(iprot).read(iprot, this);
  }

  public void write(org.apache.thrift.protocol.TProtocol oprot) throws org.apache.thrift.TException {
    scheme(oprot).write(oprot, this);
  }

  @Override
  public java.lang.String toString() {
    java.lang.StringBuilder sb = new java.lang.StringBuilder("UserGroupIdType(");
    boolean first = true;

    sb.append("userGroupType:");
    sb.append(this.userGroupType);
    first = false;
    if (!first) sb.append(", ");
    sb.append("userGroupId:");
    sb.append(this.userGroupId);
    first = false;
    sb.append(")");
    return sb.toString();
  }

  public void validate() throws org.apache.thrift.TException {
    // check for required fields
    // check for sub-struct validity
  }

  private void writeObject(java.io.ObjectOutputStream out) throws java.io.IOException {
    try {
      write(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(out)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private void readObject(java.io.ObjectInputStream in) throws java.io.IOException, java.lang.ClassNotFoundException {
    try {
      // it doesn't seem like you should have to do this, but java serialization is wacky, and doesn't call the default constructor.
      __isset_bitfield = 0;
      read(new org.apache.thrift.protocol.TCompactProtocol(new org.apache.thrift.transport.TIOStreamTransport(in)));
    } catch (org.apache.thrift.TException te) {
      throw new java.io.IOException(te);
    }
  }

  private static class UserGroupIdTypeStandardSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UserGroupIdTypeStandardScheme getScheme() {
      return new UserGroupIdTypeStandardScheme();
    }
  }

  private static class UserGroupIdTypeStandardScheme extends org.apache.thrift.scheme.StandardScheme<UserGroupIdType> {

    public void read(org.apache.thrift.protocol.TProtocol iprot, UserGroupIdType struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TField schemeField;
      iprot.readStructBegin();
      while (true)
      {
        schemeField = iprot.readFieldBegin();
        if (schemeField.type == org.apache.thrift.protocol.TType.STOP) { 
          break;
        }
        switch (schemeField.id) {
          case 1: // USER_GROUP_TYPE
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.userGroupType = iprot.readI64();
              struct.setUserGroupTypeIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          case 2: // USER_GROUP_ID
            if (schemeField.type == org.apache.thrift.protocol.TType.I64) {
              struct.userGroupId = iprot.readI64();
              struct.setUserGroupIdIsSet(true);
            } else { 
              org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
            }
            break;
          default:
            org.apache.thrift.protocol.TProtocolUtil.skip(iprot, schemeField.type);
        }
        iprot.readFieldEnd();
      }
      iprot.readStructEnd();

      // check for required fields of primitive type, which can't be checked in the validate method
      struct.validate();
    }

    public void write(org.apache.thrift.protocol.TProtocol oprot, UserGroupIdType struct) throws org.apache.thrift.TException {
      struct.validate();

      oprot.writeStructBegin(STRUCT_DESC);
      oprot.writeFieldBegin(USER_GROUP_TYPE_FIELD_DESC);
      oprot.writeI64(struct.userGroupType);
      oprot.writeFieldEnd();
      oprot.writeFieldBegin(USER_GROUP_ID_FIELD_DESC);
      oprot.writeI64(struct.userGroupId);
      oprot.writeFieldEnd();
      oprot.writeFieldStop();
      oprot.writeStructEnd();
    }

  }

  private static class UserGroupIdTypeTupleSchemeFactory implements org.apache.thrift.scheme.SchemeFactory {
    public UserGroupIdTypeTupleScheme getScheme() {
      return new UserGroupIdTypeTupleScheme();
    }
  }

  private static class UserGroupIdTypeTupleScheme extends org.apache.thrift.scheme.TupleScheme<UserGroupIdType> {

    @Override
    public void write(org.apache.thrift.protocol.TProtocol prot, UserGroupIdType struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol oprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet optionals = new java.util.BitSet();
      if (struct.isSetUserGroupType()) {
        optionals.set(0);
      }
      if (struct.isSetUserGroupId()) {
        optionals.set(1);
      }
      oprot.writeBitSet(optionals, 2);
      if (struct.isSetUserGroupType()) {
        oprot.writeI64(struct.userGroupType);
      }
      if (struct.isSetUserGroupId()) {
        oprot.writeI64(struct.userGroupId);
      }
    }

    @Override
    public void read(org.apache.thrift.protocol.TProtocol prot, UserGroupIdType struct) throws org.apache.thrift.TException {
      org.apache.thrift.protocol.TTupleProtocol iprot = (org.apache.thrift.protocol.TTupleProtocol) prot;
      java.util.BitSet incoming = iprot.readBitSet(2);
      if (incoming.get(0)) {
        struct.userGroupType = iprot.readI64();
        struct.setUserGroupTypeIsSet(true);
      }
      if (incoming.get(1)) {
        struct.userGroupId = iprot.readI64();
        struct.setUserGroupIdIsSet(true);
      }
    }
  }

  private static <S extends org.apache.thrift.scheme.IScheme> S scheme(org.apache.thrift.protocol.TProtocol proto) {
    return (org.apache.thrift.scheme.StandardScheme.class.equals(proto.getScheme()) ? STANDARD_SCHEME_FACTORY : TUPLE_SCHEME_FACTORY).getScheme();
  }
}

