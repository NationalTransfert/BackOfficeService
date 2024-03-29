package org.national.transfer.backoffice.service.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "wallet-service",url = "wallet.national-transfer.svc.cluster.local:8081")
public interface ModifiedCustomWalletServiceProxy {

    @PutMapping("/ws-wallet/{identityNumber}")
    Object updateCustomWalletAmount(@RequestParam Double customNewAmount, @PathVariable String identityNumber);

    @PutMapping("/ws-wallet/addAmount/{identityNumber}")
    Object addCustomAmountToWallet(@RequestParam Double customNewAmount, @PathVariable String identityNumber);
}
