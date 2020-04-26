package com.ipn.escom.bean;

import java.io.IOException;
import java.util.Enumeration;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import com.ipn.escom.dao.UserDAO;
import com.ipn.escom.dao.UserDAOImpl;
import com.ipn.escom.entity.UserEntity;

@ManagedBean(name = "login", eager = false)
@SessionScoped
public class LoginBean {

	private static final Logger LOGGER = Logger.getLogger(LoginBean.class.getName());

	private String username;
	private String password;

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

	public String signIn() {
		final UserDAO userDAO = new UserDAOImpl();
		UserEntity userEntity = new UserEntity();
		userEntity.setUsername(username);
		userEntity.setPassword(password);
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		List<UserEntity> userEntities = userDAO.findUserByUsernameAndPassword(userEntity);
		if (userEntities == null || userEntities.isEmpty()) {
			LOGGER.log(Level.INFO, "NO USERS");
			context.addMessage("loginMessage",
					new FacesMessage(FacesMessage.SEVERITY_WARN, "Login failed", "Invalid or unknown credentials"));
			return "index?faces-redirect=true";
		} else {
			LOGGER.log(Level.INFO, "CREATING SESSION");
			HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
			session.setAttribute("user", userEntities.get(0));
			return "user/welcome";
		}
	}

	public String signOut() throws IOException {
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		HttpSession session = (HttpSession) context.getExternalContext().getSession(true);
		if (session != null) {
			Enumeration<String> atts = session.getAttributeNames();
			while (atts.hasMoreElements()) {
				String att = atts.nextElement();
				session.removeAttribute(att);
			}
			LOGGER.log(Level.INFO, "INVALIDATING SESSION");
			session.invalidate();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.invalidateSession();
			externalContext.redirect(
					String.format("%s/index.xhtml?faces-redirect=true", externalContext.getRequestContextPath()));
		}
		return "index?faces-redirect=true";
	}

}
