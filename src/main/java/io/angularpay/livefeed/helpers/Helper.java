package io.angularpay.livefeed.helpers;

import org.slf4j.MDC;
import org.springframework.util.StringUtils;

import java.util.UUID;

public class Helper {

    public static void addToMappedDiagnosticContext(String name, String value) {
        if (StringUtils.hasText(value)) {
            MDC.put(name, value);
        }
    }

    public static void addToMappedDiagnosticContextOrRandomUUID(String name, String value) {
        if (StringUtils.hasText(value)) {
            MDC.put(name, value);
        } else {
            MDC.put(name, UUID.randomUUID().toString());
        }
    }

}
