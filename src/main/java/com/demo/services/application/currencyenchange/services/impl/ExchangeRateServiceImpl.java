package com.demo.services.application.currencyenchange.services.impl;

import com.demo.services.application.currencyenchange.dao.CurrencyExchangeDao;
import com.demo.services.application.currencyenchange.model.domain.ExchangeDomain;
import com.demo.services.application.currencyenchange.model.domain.ExchangeRateDomain;
import com.demo.services.application.currencyenchange.services.ExchangeRateService;
import io.reactivex.Completable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;
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

  CurrencyExchangeDao currencyExchangeDao;

  @Override
  public Single<ExchangeDomain> calculateExchangeRate(ExchangeDomain exchangeDomain) {
    return currencyExchangeDao.findRate(exchangeDomain)
        .subscribeOn(Schedulers.io())
        .flatMap(data -> Single.just(ExchangeDomain.builder()
            .exchangeRate(ExchangeRateDomain.builder()
                .rate(data.getRate())
                .equivalentAmount(calculateAmount(exchangeDomain, data.getRate()))
                .build())
            .currencySrc(exchangeDomain.getCurrencySrc())
            .currencyDest(exchangeDomain.getCurrencyDest())
            .amount(exchangeDomain.getAmount())
            .build()));

  }

  private double calculateAmount(ExchangeDomain exchangeDomain, double rate) {
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
    return Single.fromCallable(() -> exchangeDomain)
        .flatMapCompletable(currencyExchangeDao::saveRateDao);
  }

}