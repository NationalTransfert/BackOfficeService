package org.national.transfer.backoffice.service.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ModifiedCustomTransactionSummary {

    private String agentID;
    private String clientID;
    private String clientFullName;
    private String creationDate;
    private Double transferAmount;
    private String beneficiaryFirstName;
    private String beneficiaryLastName;
}
