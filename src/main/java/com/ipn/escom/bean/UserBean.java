package com.ipn.escom.bean;

import static javax.faces.application.FacesMessage.SEVERITY_FATAL;
import static javax.faces.application.FacesMessage.SEVERITY_INFO;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.ipn.escom.dao.UserDAO;
import com.ipn.escom.dao.UserDAOImpl;
import com.ipn.escom.entity.UserEntity;

@ManagedBean(name = "userView", eager = false)
@SessionScoped
public class UserBean {

	private static final Logger LOGGER = Logger.getLogger(UserBean.class.getName());
	private static final String REDIRECT = "read?faces-redirect=true";
	private static final String READ_FACE_MESSAGE = "readMessage";
	private static final String CREATE_FACE_MESSAGE = "createMessage";
	private static final String ERROR_ON_FIND = "Error on find";
	private static final String ERROR_ON_DELETE = "Error on delete";
	private static final String ERROR_ON_SAVE = "Error on save";
	private List<UserEntity> users; // findAll
	private UserEntity currentUser; // update
	private String username;
	private String password;
	private UserDAO dao;

	@PostConstruct
	public void init() {
		dao = new UserDAOImpl();
		currentUser = null;
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
		UserEntity user = new UserEntity();
		FacesContext context = FacesContext.getCurrentInstance();
		user.setUsername(username);
		users = dao.findUserByUsername(user);
		if (!users.isEmpty()) {
			setMessage(context, CREATE_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_SAVE, "The username alredy exists");
			return;
		}
		user.setPassword(password);
		user = dao.saveUser(user);
		String message = String.format("User was created with the ID<%d> at %s.", user.getIdUser(),
				user.getCreatedAt().toString());
		setMessage(context, CREATE_FACE_MESSAGE, FacesMessage.SEVERITY_INFO, "Save sucessful", message);
		clearFields();
	}

	public void deleteUser(Integer idUser) {
		FacesContext context = FacesContext.getCurrentInstance();
		if (idUser == null) {
			setMessage(context, READ_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_DELETE, "Invalid user id.");
			return;
		}
		UserEntity user = new UserEntity();
		user.setIdUser(idUser);
		List<UserEntity> entities = dao.findUserById(user);
		if (entities.isEmpty()) {
			setMessage(context, READ_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_DELETE, "User not found.");
			return;
		}
		boolean deleted = dao.deleteUser(entities.get(0));
		if (deleted) {
			setMessage(context, READ_FACE_MESSAGE, SEVERITY_INFO, "Delete sucessful", "User was deleted.");
		} else {
			setMessage(context, READ_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_DELETE, "Something went wrong");
		}
	}

	public String findUserById(Integer idUser) {

		FacesContext context = FacesContext.getCurrentInstance();
		if (idUser == null) {
			setMessage(context, READ_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_FIND, "Invalid user id.");
		} else {
			UserEntity user = new UserEntity();
			user.setIdUser(idUser);
			List<UserEntity> entities = dao.findUserById(user);
			if (entities.isEmpty()) {
				setMessage(context, READ_FACE_MESSAGE, SEVERITY_FATAL, ERROR_ON_FIND, "User not found.");
			} else {
				currentUser = entities.get(0);
			}
		}
		return REDIRECT;
	}

	public String cancelUpdate() {
		clearFields();
		return REDIRECT;
	}

	public String updateUser() {
		dao.updateUser(currentUser);
		clearFields();
		FacesContext context = FacesContext.getCurrentInstance();
		setMessage(context, READ_FACE_MESSAGE, SEVERITY_INFO, "Update sucessful", "User was updated.");
		return REDIRECT;
	}

	public List<UserEntity> getUsers() {
		users = dao.findAllUsers();
		return users;
	}

	public UserEntity getCurrentUser() {
		return currentUser;
	}

	private void clearFields() {
		username = null;
		password = null;
		currentUser = null;
	}

	private void setMessage(FacesContext context, String clientId, Severity severity, String summary, String detail) {
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		context.addMessage(clientId, new FacesMessage(severity, summary, detail));
	}

}
