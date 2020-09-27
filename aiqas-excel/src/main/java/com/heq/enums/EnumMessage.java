package com.heq.enums;

import com.google.common.base.CaseFormat;

public interface EnumMessage {
    default String getMessageKey(Enum<?> e) {
        return CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, e.getClass().getSimpleName()) + '.' + e.name().toLowerCase();
    }
}
