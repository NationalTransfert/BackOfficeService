package org.national.transfer.backoffice.service.exception;

import lombok.Getter;

public class CustomTransferException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    @Getter
    private Integer customStatus;

    public CustomTransferException() {
        super();
    }

    public CustomTransferException(String customMessage, Throwable cause) {
        super(customMessage, cause);
    }

    public CustomTransferException(String customMessage) {
        super(customMessage);
    }

    public CustomTransferException(String customMessage, int customStatus) {
        super(customMessage);
        this.customStatus = customStatus;
    }
}