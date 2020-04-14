package com.ipn.escom.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import com.ipn.escom.dao.UserDAO;
import com.ipn.escom.dao.UserDAOImpl;
import com.ipn.escom.entity.UserEntity;

@ManagedBean(name = "userController", eager = false)
@RequestScoped
public class UserBean {

  private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());
  private String username;
  private String password;
  private UserDAO dao;

  @PostConstruct
  public void init() {
    dao = new UserDAOImpl();
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

  public void createUser() {
    LOGGER.log(Level.INFO, "createUser");
    UserEntity userEntity = new UserEntity();
    FacesContext context = FacesContext.getCurrentInstance();
    context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
    userEntity.setUsername(username);
    List<UserEntity> users = dao.findUserByUsername(userEntity);
    if (!users.isEmpty()) {
      context.addMessage("createMessage", new FacesMessage(FacesMessage.SEVERITY_FATAL,
          "Error on save", "The username alredy exists"));
      return;
    }
    userEntity.setPassword(password);
    userEntity = dao.saveUser(userEntity);
    String message = String.format("User was created with the ID<%d> at %s.",
        userEntity.getIdUser(), userEntity.getCreatedAt().toString());
    context.addMessage("createMessage",
        new FacesMessage(FacesMessage.SEVERITY_INFO, "Save sucessful", message));
    clearFields();
  }

  private void clearFields() {
    username = null;
    password = null;
  }

}
