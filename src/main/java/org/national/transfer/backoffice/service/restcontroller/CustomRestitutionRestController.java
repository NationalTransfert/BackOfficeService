package org.national.transfer.backoffice.service.restcontroller;

import lombok.AllArgsConstructor;
import lombok.extern.apachecommons.CommonsLog;
import org.national.transfer.backoffice.service.dto.ModifiedCustomTransactionSummary;
import org.national.transfer.backoffice.service.service.ModifiedCustomRestitutionService;
import org.national.transfer.backoffice.service.utils.LoggingUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@CommonsLog
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/custom-restitution")
public class CustomRestitutionRestController {

    private final ModifiedCustomRestitutionService ModifiedCustomRestitutionService;

    @GetMapping("/getCustomTransfer/{customReference}")
    public ResponseEntity<ModifiedCustomTransactionSummary> getCustomTransfer(@PathVariable String customReference) {
        log.info(LoggingUtils.getStartMessage(customReference));
        ModifiedCustomTransactionSummary customRecap = ModifiedCustomRestitutionService.getCustomTransfer(customReference);
        log.debug(LoggingUtils.getEndMessage(customRecap ));
        return ResponseEntity.status(HttpStatus.OK).body(customRecap );
    }

    @PostMapping("/motif")
    public ResponseEntity<?> addCustomMotif(@RequestParam String motif) {
        log.info(LoggingUtils.getStartMessage(motif));
        ModifiedCustomRestitutionService.addModifiedMotif(motif);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/validate")
    public ResponseEntity<Map> validateRestitution(@RequestParam String canalId) {
        log.info(LoggingUtils.getMessage(canalId));
        Map customRecap = ModifiedCustomRestitutionService.validateModifiedRestitution(canalId);
        log.debug(LoggingUtils.getEndMessage(customRecap));
        return ResponseEntity.status(HttpStatus.OK).body(customRecap);
    }
}
