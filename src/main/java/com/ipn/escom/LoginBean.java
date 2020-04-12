package com.ipn.escom;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import com.ipn.escom.dao.UserDAO;
import com.ipn.escom.dao.UserDAOImpl;
import com.ipn.escom.entity.UserEntity;

@ManagedBean(name = "login", eager = true)
@SessionScoped
public class LoginBean {

  private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

  private String username;
  private String password;
  private String message = "Please fill the form";

  public LoginBean() {
    LOGGER.log(Level.INFO, "LoginBean instantiated");
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String signIn() {
    final UserDAO userDAO = new UserDAOImpl();
    UserEntity userEntity = new UserEntity();
    userEntity.setUsername(username);
    userEntity.setPassword(password);
    List<UserEntity> userEntities = userDAO.findUserByLogin(userEntity);
    if (userEntities == null || userEntities.isEmpty()) {
      this.message = "Invalid credentials";
      return "index";
    } else {
      FacesContext context = FacesContext.getCurrentInstance();
      HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
      session.setAttribute("user", userEntities.get(0));
      return "user/welcome";
    }
  }


}
