package com.demo.services.application.currencyenchange.dao.impl;

import com.demo.services.application.currencyenchange.dao.CurrencyExchangeDao;
import com.demo.services.application.currencyenchange.dao.entity.CurrencyExchangeEntity;
import com.demo.services.application.currencyenchange.model.domain.ExchangeDomain;
import com.demo.services.application.currencyenchange.repository.CurrencyRepository;
import io.reactivex.Completable;
import io.reactivex.Single;
import java.util.Optional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <b>Class</b>: CurrencyExchangeDaoImpl<br/>
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

@AllArgsConstructor
@Service
public class CurrencyExchangeDaoImpl implements CurrencyExchangeDao {

  CurrencyRepository repository;

  @Override
  public Single<CurrencyExchangeEntity> findRate(ExchangeDomain domain) {
    return Single.fromCallable(() -> domain)
        .flatMap(data -> {
          CurrencyExchangeEntity entity;
          if (Optional.ofNullable(repository.findByRate()).isPresent()) {
            entity = repository.findByRate();
          } else {
            entity = new CurrencyExchangeEntity();
            entity.setId(1);
            entity.setRate(4.11);
          }
          return Single.just(entity);
        });

  }

  @Override
  public Completable saveRateDao(ExchangeDomain domain) {
    return Single.fromCallable(() -> repository.findByRate())
        .flatMapCompletable(data -> {
          if (data == null) {
            data = new CurrencyExchangeEntity();
            data.setRate(domain.getExchangeRate().getRate());
            data.setId(1);
          } else {
            data.setRate(domain.getExchangeRate().getRate());
          }
          repository.save(data);
          return Completable.complete();
        });
  }


}