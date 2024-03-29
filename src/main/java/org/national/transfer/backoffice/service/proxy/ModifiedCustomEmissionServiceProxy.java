package org.national.transfer.backoffice.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Map;

@FeignClient(name = "emission-service",url="emission.national-transfer.svc.cluster.local:8082")
public interface ModifiedCustomEmissionServiceProxy {

    @GetMapping("/ws-emission/cash/getTransfer/{customReference}")
    Map<String, Object> getCustomTransfer(@PathVariable String customReference);

    @PutMapping("/ws-emission/cash/updateTransferStatus/{customStatus}/{customReference}")
    void updateCustomTransferStatus(@PathVariable String customStatus, @PathVariable String customReference);

    @GetMapping("/ws-emission/cash/getAllToServeTransfers")
    List<Map> getAllCustomToServeTransfers();

    @GetMapping("/ws-emission/cash/getAllLockedTransfers")
    List<Map> getAllCustomLockedTransfers();

    @GetMapping("/ws-emission/getAllTransfers")
    List<Map<String, Object>> getAllCustomTransfers();
}
