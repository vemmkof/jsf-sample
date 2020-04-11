package com.ipn.escom;

import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@RequestScoped
@ManagedBean(name = "jsfSampleApp", eager = true)
public class JsfSampleApp {

  private static final Logger LOGGER = Logger.getLogger(JsfSampleApp.class.getName());

  private String message = "Welcome to JSF - PrimeFaces with maven";

  public JsfSampleApp() {
    LOGGER.log(Level.INFO, "JsfSampleApp instantiated");
  }

  public String getMessage() {
    StringBuilder builder = new StringBuilder(message);
    builder.append(" - ");
    builder.append(LocalDateTime.now().getHour());
    builder.append(":");
    builder.append(LocalDateTime.now().getMinute());
    return builder.toString();
  }

}
