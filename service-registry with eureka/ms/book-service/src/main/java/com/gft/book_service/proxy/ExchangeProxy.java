package com.gft.book_service.proxy;

import com.gft.book_service.domain.dto.ExchangeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "exchange-service", path = "/exchange-service")
public interface ExchangeProxy {

    @RequestMapping(method = RequestMethod.GET, value = "/{amount}/{from}/{to}")
    public ExchangeDTO getExchange(@PathVariable double amount, @PathVariable String from, @PathVariable String to);

}
