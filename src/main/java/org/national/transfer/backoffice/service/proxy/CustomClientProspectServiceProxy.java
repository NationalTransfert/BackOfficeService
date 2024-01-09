package org.national.transfer.backoffice.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "prospect-client-service",url = "prospect-client.national-transfer.svc.cluster.local:8083")
public interface CustomClientProspectServiceProxy {

    @PostMapping("/ws-prospect/prospect")
    Map<String, Object> addCustomProspectClient(@RequestBody Map<String, Object> customProspectClient);
}
