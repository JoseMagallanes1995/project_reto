package com.demo.services.application.currencyenchange.repository;

import com.demo.services.application.currencyenchange.dao.CurrencyExchangeEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * <b>Class</b>: CurrencyRepository<br/>
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

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyExchangeEntity, Integer> {

  @Query(value = "SELECT * FROM CURRENCY_EXCHANGE", nativeQuery = true)
  CurrencyExchangeEntity findByRate();

}