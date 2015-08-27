package com.tonto.hms.model;

public class Constant {
    private Integer keyId;

    private String keyDesc;

    private Integer constantId;

    private String constantValue;

    public Integer getKeyId() {
        return keyId;
    }

    public void setKeyId(Integer keyId) {
        this.keyId = keyId;
    }

    public String getKeyDesc() {
        return keyDesc;
    }

    public void setKeyDesc(String keyDesc) {
        this.keyDesc = keyDesc == null ? null : keyDesc.trim();
    }

    public Integer getConstantId() {
        return constantId;
    }

    public void setConstantId(Integer constantId) {
        this.constantId = constantId;
    }

    public String getConstantValue() {
        return constantValue;
    }

    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue == null ? null : constantValue.trim();
    }
}