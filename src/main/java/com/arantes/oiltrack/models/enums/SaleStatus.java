package com.arantes.oiltrack.models.enums;

import lombok.Getter;

public enum SaleStatus {

    DELIVERED(1),
    PENDING(2),
    CANCELED(3);

    @Getter
    private int code;

    private SaleStatus(int code) {
        this.code = code;
    }

    public static SaleStatus valueOf(int code) {
        for(SaleStatus value : SaleStatus.values()) {
            if (value.getCode() == code) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid SaleStatus code");
    }
}
