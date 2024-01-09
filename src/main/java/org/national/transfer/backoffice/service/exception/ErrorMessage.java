package org.national.transfer.backoffice.service.exception;

import lombok.Data;

@Data
public class ErrorMessage {
    private String errorTimestamp;
    private String errorStatus;
    private String errorType;
    private String errorMessage;
    private String errorPath;
    private String requestIdentifier;
}
