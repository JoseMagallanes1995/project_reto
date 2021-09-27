package com.demo.services.application.currencyenchange.dao;

import com.demo.services.application.currencyenchange.dao.entity.CurrencyExchangeEntity;
import com.demo.services.application.currencyenchange.model.domain.ExchangeDomain;
import io.reactivex.Completable;
import io.reactivex.Single;

/**
 * <b>Class</b>: CurrencyExchangeDao<br/>
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
 * <li>26/09/2021 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */

public interface CurrencyExchangeDao {

  Single<CurrencyExchangeEntity> findRate(ExchangeDomain data);

  Completable saveRateDao(ExchangeDomain data);

}