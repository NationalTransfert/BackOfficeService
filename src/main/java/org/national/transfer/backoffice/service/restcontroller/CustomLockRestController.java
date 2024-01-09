package org.national.transfer.backoffice.service.restcontroller;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.backoffice.service.service.ModifiedCustomLockService;
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
@RequestMapping("/lock")
public class CustomLockRestController {

    private final ModifiedCustomLockService ModifiedCustomLockService;

    @GetMapping("/getAllToServeTransfers")
    public ResponseEntity<List<Map>> getAllCustomToServeTransfers() {
        log.info(LoggingUtils.getStartMessage());
        List<Map> customTransfers = ModifiedCustomLockService.getAllModifiedCustomToServeTransfers();
        log.info(LoggingUtils.getMessage(customTransfers));
        return ResponseEntity.status(HttpStatus.OK).body(customTransfers);
    }

    @PostMapping("/{customReference}")
    public ResponseEntity<?> lockCustomTransfer(@PathVariable String customReference) {
        log.info(LoggingUtils.getStartMessage(customReference));
        ModifiedCustomLockService.modifiedLockTransfer(customReference);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
