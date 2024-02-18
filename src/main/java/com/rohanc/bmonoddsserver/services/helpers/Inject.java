/* (C)2024 */
package com.rohanc.bmonoddsserver.services.helpers;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.springframework.stereotype.Component;

@Component
public class Inject {
  public String constructJSExpressionWithFile(String jsExpression, String jsFilePath) {
    try {
      byte[] encoded = Files.readAllBytes(Paths.get(jsFilePath));
      return new String(encoded, StandardCharsets.UTF_8) + "; " + jsExpression;
    } catch (IOException e) {
      throw new JSFileNotFoundException();
    }
  }

  public class JSFileNotFoundException extends RuntimeException {}
}
