package com.ipn.escom.filter;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class JsfSampleFilter implements Filter {

  private static final Logger LOGGER = Logger.getLogger(JsfSampleFilter.class.getName());


  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {

    final HttpServletRequest servletRequest = (HttpServletRequest) request;
    final HttpServletResponse servletResponse = (HttpServletResponse) response;
    final HttpSession session = servletRequest.getSession(false);
    final String rootURL = String.format("%s/", servletRequest.getContextPath());
    final String loginURL = String.format("%s/index.xhtml", servletRequest.getContextPath());
    final String welcomeURL =
        String.format("%s/user/welcome.xhtml", servletRequest.getContextPath());
    final String requestURI = servletRequest.getRequestURI();
    boolean logged = isLogged(session);
    boolean loginRequest = isLoginRequest(requestURI, loginURL, rootURL);
    boolean resourceRequest = isResourceRequest(servletRequest, requestURI);
    if (resourceRequest) {
      chain.doFilter(servletRequest, servletResponse);
      return;
    }
    if (!logged && !loginRequest) {
      LOGGER.log(Level.INFO, "not logged AND not login request");
      toLogin(servletResponse, loginURL);
    } else if (!logged) {
      LOGGER.log(Level.INFO, "not logged AND login request");
      chain.doFilter(servletRequest, servletResponse);
    } else if (loginRequest) {
      LOGGER.log(Level.INFO, "logged AND login request");
      servletResponse.sendRedirect(welcomeURL);
    } else {
      LOGGER.log(Level.INFO, "logged AND not login request");
      chain.doFilter(servletRequest, servletResponse);
    }

  }

  private boolean isResourceRequest(HttpServletRequest servletRequest, String requestURI) {
    final String resourcePrefix = String.format("%s%s/", servletRequest.getContextPath(),
        ResourceHandler.RESOURCE_IDENTIFIER);
    return requestURI.startsWith(resourcePrefix);
  }

  private void toLogin(HttpServletResponse servletResponse, String loginURL) throws IOException {
    servletResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
    servletResponse.setHeader("Pragma", "no-cache");
    servletResponse.setDateHeader("Expires", -1);
    servletResponse.sendRedirect(loginURL);
  }

  private boolean isLoginRequest(String requestURI, String loginURL, String rootURL) {
    return requestURI.equals(loginURL) || requestURI.equals(rootURL);
  }

  private boolean isLogged(HttpSession session) {
    return session != null && session.getAttribute("user") != null;
  }


}
