package com.demo.services.application.expose.web;

import com.demo.services.application.currencyenchange.model.api.ExchangeRateRequest;
import com.demo.services.application.currencyenchange.model.api.ExchangeRateResponse;
import com.demo.services.application.currencyenchange.model.api.ExchangeRateSettingRequest;
import com.demo.services.application.currencyenchange.model.domain.ExchangeMapper;
import com.demo.services.application.currencyenchange.services.ExchangeRateService;
import io.reactivex.Completable;
import io.reactivex.Single;
import javax.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <b>Class</b>: CurrencyChangeController<br/>
 * <b>Copyright</b>: &copy; 2021<br/>
 * <b>Company</b> : <br/>
 *
 * @author Jose Magallanes <br/>
 * <u>Service Provider</u>:  <br/>
 * <u>Developed by</u>: <br/>
 * <ul>
 * <li>Jose Magallanes</li>
 * </ul>
 * <u>Changes</u>:<br/>
 * <ul>
 * <li>24/09/2021 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */

@RestController
@RequestMapping("/application/services/exchange-rate/v1")
@AllArgsConstructor
public class CurrencyChangeController {

  private ExchangeRateService exchangeRateService;
  private ExchangeMapper mapper;


  @PostMapping(value = "/calculate", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public Single<ExchangeRateResponse> calculateExchangeRate(@Valid @RequestBody ExchangeRateRequest request) {

    return Single.fromCallable(() -> mapper.requestToDomain(request))
        .flatMap(exchangeRateService::calculateExchangeRate)
        .flatMap(mapper::domainToResponse);
  }

  @PostMapping(value = "/setting", consumes = MediaType.APPLICATION_JSON_VALUE)
  public Completable settingRate(@Valid @RequestBody ExchangeRateSettingRequest request) {

    return Single.fromCallable(() -> mapper.requestSettingToDomain(request))
        .flatMapCompletable(exchangeRateService::settingRate);

  }

}