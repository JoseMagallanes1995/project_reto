package com.demo.services.application.currencyenchange.model.api;

import com.fasterxml.jackson.annotation.JsonInclude;
import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * <b>Class</b>: ExchangeRate<br/>
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
 * <li>25/09/2021 Creaci&oacute;n de Clase.</li>
 * </ul>
 * @version 1.0
 */

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ExchangeRate {

  @NotNull
  private double rate;

  private double equivalentAmount;

}