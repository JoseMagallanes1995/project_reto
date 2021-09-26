package com.demo.services.application.currencyenchange.model.api;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class</b>: ExchangeRateSettingRequest<br/>
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

@Getter
@Setter
public class ExchangeRateSettingRequest {

  @NotNull
  private ExchangeRate exchangeRate;

}