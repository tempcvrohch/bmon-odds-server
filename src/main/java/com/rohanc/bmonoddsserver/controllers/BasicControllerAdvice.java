/* (C)2024 */
package com.rohanc.bmonoddsserver.controllers;

import com.rohanc.bmonoddsserver.controllers.AuthController.NotLoggedInException;
import com.rohanc.bmonoddsserver.services.*;
import com.rohanc.bmonoddsserver.services.helpers.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.util.ContentCachingRequestWrapper;

@ControllerAdvice
public class BasicControllerAdvice {
  private Logger logger = LoggerFactory.getLogger(getClass());

  @ResponseBody
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler({
    MatchService.MatchNotFound.class,
    UserCoreService.UsernameTakenException.class,
    BetService.BetAlreadyPlacedException.class,
    BetService.InsufficientBalanceException.class,
    BetService.UnknownMarketStateOnBetException.class,
    BetService.StakeOutOfBoundsException.class
  })
  public String handleBadRequest(Exception exception) {
    logger.info(exception.getLocalizedMessage());
    return exception.getClass().getName();
  }

  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  @ExceptionHandler({
    Inject.JSFileNotFoundException.class,
    BetService.InvalidFractionalOddException.class
  })
  public void handleServerError(Exception exception, ContentCachingRequestWrapper request) {
    logger.error("aaaa!!");
    logger.debug(new String(request.getContentAsByteArray()));
  }

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler({NotLoggedInException.class})
  public void handleUnautherizeddError(Exception exception) {
    logger.error(exception.getLocalizedMessage());
  }
}
