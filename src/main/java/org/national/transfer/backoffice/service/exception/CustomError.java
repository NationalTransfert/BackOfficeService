package org.national.transfer.backoffice.service.exception;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.Date;

@JsonInclude(Include.NON_NULL)
@Getter
public class CustomError implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long errorTimestamp = new Date().getTime();
    private HttpStatus errorCode;
    private String errorMessage;
    private String errorDescription;
    @Setter
    private String transactionIdentifier;

    public CustomError(HttpStatus status, String errorMessage) {
        super();
        this.errorCode = status;
        this.errorMessage = errorMessage;
        addTransactionIdentifier();
    }

    public CustomError(HttpStatus status, String errorMessage, String errorDescription) {
        super();
        this.errorCode = status;
        this.errorMessage = errorMessage;
        this.errorDescription = errorDescription;
        addTransactionIdentifier();
    }

    private void addTransactionIdentifier() {
        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        //this.transactionId = (String) httpRequest.getAttribute(Constants.TRANSACTION_ID);
    }
}
