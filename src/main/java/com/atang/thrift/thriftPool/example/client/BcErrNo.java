/**
 * Autogenerated by Thrift Compiler (0.10.0)
 *
 * DO NOT EDIT UNLESS YOU ARE SURE THAT YOU KNOW WHAT YOU ARE DOING
 *  @generated
 */
package com.atang.thrift.thriftPool.example.client;


import java.util.Map;
import java.util.HashMap;
import org.apache.thrift.TEnum;

public enum BcErrNo implements org.apache.thrift.TEnum {
  BEN_SUCCUSS(0),
  BEN_ERROR(1),
  BEN_OVERFLOW(2);

  private final int value;

  private BcErrNo(int value) {
    this.value = value;
  }

  /**
   * Get the integer value of this enum value, as defined in the Thrift IDL.
   */
  public int getValue() {
    return value;
  }

  /**
   * Find a the enum type by its integer value, as defined in the Thrift IDL.
   * @return null if the value is not found.
   */
  public static BcErrNo findByValue(int value) { 
    switch (value) {
      case 0:
        return BEN_SUCCUSS;
      case 1:
        return BEN_ERROR;
      case 2:
        return BEN_OVERFLOW;
      default:
        return null;
    }
  }
}
