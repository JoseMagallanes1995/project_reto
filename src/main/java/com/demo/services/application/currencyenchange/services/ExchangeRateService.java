package com.demo.services.application.currencyenchange.services;

import com.demo.services.application.currencyenchange.model.domain.ExchangeDomain;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * <b>Class</b>: ExchangeRateService<br/>
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

public interface ExchangeRateService {

  Single<ExchangeDomain> calculateExchangeRate(ExchangeDomain exchangeDomain);

  Completable settingRate (ExchangeDomain exchangeDomain);

}
