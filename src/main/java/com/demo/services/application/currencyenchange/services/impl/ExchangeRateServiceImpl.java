package com.demo.services.application.currencyenchange.services.impl;

import com.demo.services.application.currencyenchange.dao.CurrencyExchangeEntity;
import com.demo.services.application.currencyenchange.model.domain.ExchangeDomain;
import com.demo.services.application.currencyenchange.model.domain.ExchangeRateDomain;
import com.demo.services.application.currencyenchange.repository.CurrencyRepository;
import com.demo.services.application.currencyenchange.services.ExchangeRateService;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <b>Class</b>: ExchangeRateServiceImpl<br/>
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

@Service
@AllArgsConstructor
public class ExchangeRateServiceImpl implements ExchangeRateService {

  CurrencyRepository repository;

  @Override
  public Single<ExchangeDomain> calculateExchangeRate(ExchangeDomain exchangeDomain) {
    double rate = Optional.ofNullable(repository.findByRate()).isPresent()
        ? repository.findByRate().getRate() : 4.11;

    return Single.just(ExchangeDomain.builder()
        .exchangeRate(ExchangeRateDomain.builder()
            .rate(rate)
            .equivalentAmount(calculateAmount(exchangeDomain, rate))
            .build())
        .currencySrc(exchangeDomain.getCurrencySrc())
        .currencyDest(exchangeDomain.getCurrencyDest())
        .amount(exchangeDomain.getAmount())
        .build());
  }

  private Double calculateAmount(ExchangeDomain exchangeDomain, double rate) {
    double convertAmount = 0;
    if (!exchangeDomain.getCurrencySrc().getCode()
        .equals(exchangeDomain.getCurrencyDest().getCode())) {

      switch (exchangeDomain.getCurrencySrc().getCode()) {
        case "PEN":
          convertAmount = exchangeDomain.getAmount() / rate;
          break;
        case "USD":
          convertAmount = exchangeDomain.getAmount() * rate;
          break;
        default:
          break;
      }

    }
    return convertAmount;

  }


  @Override
  public Completable settingRate(ExchangeDomain exchangeDomain) {

    CurrencyExchangeEntity entity = repository.findByRate();
    if (entity == null) {
      entity = new CurrencyExchangeEntity();
      entity.setRate(exchangeDomain.getExchangeRate().getRate());
      entity.setId(1);
    } else {
      entity.setRate(exchangeDomain.getExchangeRate().getRate());
    }

    repository.save(entity);

    return Completable.complete();
  }

}