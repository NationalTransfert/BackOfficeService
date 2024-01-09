package org.national.transfer.backoffice.service.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Response;
import feign.codec.ErrorDecoder;

import java.io.IOException;
import java.io.InputStream;

public class CustomErrorDecoder implements ErrorDecoder {

    private ErrorDecoder errorDecoder = new Default();

    @Override
    public Exception decode(String methodKey, Response response) {
        ErrorMessage errorMessage = null;
        try (InputStream bodyIs = response.body().asInputStream()) {
            ObjectMapper mapper = new ObjectMapper();
            errorMessage = mapper.readValue(bodyIs, ErrorMessage.class);
        } catch (IOException e) {
            return new Exception(e.getMessage());
        }
        switch (response.status()) {
            case 404:
                return new ResourceNotFoundException(errorMessage.getErrorMessage() != null ? errorMessage.getErrorMessage() : "Not found");
            case 409:
                return new CustomTransferException(errorMessage.getErrorMessage() != null ? errorMessage.getErrorMessage() : "Transfer issue");
            default:
                return errorDecoder.decode(methodKey, response);
        }
    }
}
