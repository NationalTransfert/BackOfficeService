package org.national.transfer.backoffice.service.restcontroller;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.backoffice.service.service.CustomUnlockService;
import org.national.transfer.backoffice.service.utils.LoggingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CommonsLog
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/unlock")
public class CustomUnlockRestController {

    private final CustomUnlockService customUnlockService;

    @GetMapping("/getAllCustomBlockedTransfers")
    public ResponseEntity<List<Map>> getAllCustomBlockedTransfers() {
        log.info(LoggingUtils.getStartMessage());
        List<Map> customTransfers = customUnlockService.getAllCustomBlockedTransfers();
        log.info(LoggingUtils.getMessage(customTransfers ));
        return ResponseEntity.status(HttpStatus.OK).body(customTransfers );
    }

    @PostMapping("/{reference}")
    public ResponseEntity<?> customLockTransfer(@PathVariable String reference) {
        log.info(LoggingUtils.getStartMessage(reference));
        customUnlockService.customLockTransfer(reference);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
