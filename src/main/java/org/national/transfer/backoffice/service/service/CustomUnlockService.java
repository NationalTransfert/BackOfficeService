package org.national.transfer.backoffice.service.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.backoffice.service.proxy.ModifiedCustomEmissionServiceProxy;
import org.national.transfer.backoffice.service.utils.Constants;
import org.national.transfer.backoffice.service.utils.LoggingUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@CommonsLog
@RequiredArgsConstructor
@SuppressWarnings({"rawtypes", "unchecked"})
public class CustomUnlockService {

    private final ModifiedCustomEmissionServiceProxy modifiedCustomEmissionServiceProxy;

    public List<Map> getAllCustomBlockedTransfers() {
        log.info(LoggingUtils.getMessage());
        List<Map> transfers = modifiedCustomEmissionServiceProxy.getAllCustomLockedTransfers();
        log.debug(LoggingUtils.getEndMessage(transfers));
        return transfers;
    }

    public void customLockTransfer(String reference) {
        log.info(LoggingUtils.getStartMessage());
        modifiedCustomEmissionServiceProxy.updateCustomTransferStatus(Constants.UNLOCK_TO_SERVE, reference);
    }
}
