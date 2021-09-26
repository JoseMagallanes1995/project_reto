package com.demo.services.application.currencyenchange.model.domain;

import com.demo.services.application.currencyenchange.model.api.BaseModel;
import com.demo.services.application.currencyenchange.model.api.ExchangeRate;
import com.demo.services.application.currencyenchange.model.api.ExchangeRateRequest;
import com.demo.services.application.currencyenchange.model.api.ExchangeRateResponse;
import com.demo.services.application.currencyenchange.model.api.ExchangeRateSettingRequest;
import io.reactivex.Single;
import org.springframework.stereotype.Component;

/**
 * <b>Class</b>: ExchangeMapper<br/>
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

@Component
public class ExchangeMapper {

  public ExchangeDomain requestToDomain(ExchangeRateRequest request) {

    return ExchangeDomain.builder()
        .amount(request.getAmount())
        .currencyDest(BaseDomain.builder()
            .code(request.getCurrencyDest().getCode())
            .build())
        .currencySrc(BaseDomain.builder()
            .code(request.getCurrencySrc().getCode())
            .build())
        .build();
  }

  public Single<ExchangeRateResponse> domainToResponse(ExchangeDomain exchangeDomain) {
    ExchangeRateResponse response = new ExchangeRateResponse();
    response.setAmount(exchangeDomain.getAmount());

    BaseModel currencyDest = new BaseModel();
    currencyDest.setCode(exchangeDomain.getCurrencyDest().getCode());
    response.setCurrencyDest(currencyDest);

    BaseModel currencySrc = new BaseModel();
    currencySrc.setCode(exchangeDomain.getCurrencySrc().getCode());
    response.setCurrencySrc(currencySrc);

    ExchangeRate exchangeRate = new ExchangeRate();
    exchangeRate.setRate(exchangeDomain.getExchangeRate().getRate());
    exchangeRate.setEquivalentAmount(exchangeDomain.getExchangeRate().getEquivalentAmount());
    response.setExchangeRate(exchangeRate);

    return Single.just(response);

  }

  public ExchangeDomain requestSettingToDomain(ExchangeRateSettingRequest request) {
    return ExchangeDomain.builder()
        .exchangeRate(ExchangeRateDomain.builder()
            .rate(request.getExchangeRate().getRate())
            .build())
        .build();
  }


}