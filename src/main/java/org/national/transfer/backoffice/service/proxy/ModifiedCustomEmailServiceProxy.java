package org.national.transfer.backoffice.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name = "email-service", url = "sms.national-transfer.svc.cluster.local:8084")
public interface ModifiedCustomEmailServiceProxy {

    @PostMapping("/ws-mail/mail/notification")
    void sendCustomStatusNotification(@RequestBody Map<String, String> customClientInfo);

}
