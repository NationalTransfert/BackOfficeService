package org.national.transfer.backoffice.service.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.backoffice.service.dto.ModifiedCustomTransactionSummary;
import org.national.transfer.backoffice.service.proxy.ModifiedCustomEmailServiceProxy;
import org.national.transfer.backoffice.service.proxy.ModifiedCustomEmissionServiceProxy;
import org.national.transfer.backoffice.service.proxy.ModifiedCustomWalletServiceProxy;
import org.national.transfer.backoffice.service.utils.Constants;
import org.national.transfer.backoffice.service.utils.LoggingUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@CommonsLog
@RequiredArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class ModifiedCustomRestitutionService {

    private final ModifiedCustomEmailServiceProxy modifiedCustomEmailServiceProxy;
    private final ModifiedCustomWalletServiceProxy modifiedCustomWalletServiceProxy;
    private final ModifiedCustomEmissionServiceProxy modifiedCustomEmissionServiceProxy;
    private final ObjectMapper mapper;
    private Map<String, Object> modifiedTransfer = new HashMap<>();

    public ModifiedCustomTransactionSummary getCustomTransfer(String reference) {
        log.info(LoggingUtils.getStartMessage(reference));
        modifiedTransfer = modifiedCustomEmissionServiceProxy.getCustomTransfer(reference);
        log.debug(LoggingUtils.getEndMessage(modifiedTransfer));
        ModifiedCustomTransactionSummary recap = mapper.convertValue(modifiedTransfer, ModifiedCustomTransactionSummary.class);
        return recap;
    }

    public void addModifiedMotif(String motif) {
        log.info(LoggingUtils.getStartMessage(motif));
        modifiedTransfer.put(Constants.REVERSE_MOTIF, motif);
    }

    public Map validateModifiedRestitution(String canalId) {
        modifiedCustomWalletServiceProxy.updateCustomWalletAmount((Double) modifiedTransfer.get("amount"), String.valueOf(modifiedTransfer.get("agentId")));
        String reference = String.valueOf(modifiedTransfer.get("_id"));
        modifiedCustomEmissionServiceProxy.updateCustomTransferStatus(Constants.RETURNED, reference);
        modifiedTransfer.put(Constants.CANAL_ID, canalId);
        sendModifiedEmailNotification();
        return modifiedTransfer;
    }

    private void sendModifiedEmailNotification() {
        if (modifiedTransfer.get("withNotification").equals(true)) {
            Map<String, String> notificationDetails = new HashMap<>();
            notificationDetails.put("clientEmail", (String) modifiedTransfer.get("clientEmail"));
            notificationDetails.put("clientFullName", (String) modifiedTransfer.get("clientFullName"));
            notificationDetails.put("beneficiaryFullName", modifiedTransfer.get("beneficiaryFirstName") + " " + modifiedTransfer.get("beneficiaryLastName"));
            notificationDetails.put("status", Constants.RETURNED);
            log.info(LoggingUtils.getMessage(notificationDetails));
            modifiedCustomEmailServiceProxy.sendCustomStatusNotification(notificationDetails);
        }
    }
}
