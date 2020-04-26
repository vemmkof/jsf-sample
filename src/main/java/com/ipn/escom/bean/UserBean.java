package com.ipn.escom.bean;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
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
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		user.setUsername(username);
		users = dao.findUserByUsername(user);
		if (!users.isEmpty()) {
			context.addMessage("createMessage",
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Error on save", "The username alredy exists"));
			return;
		}
		user.setPassword(password);
		user = dao.saveUser(user);
		String message = String.format("User was created with the ID<%d> at %s.", user.getIdUser(),
				user.getCreatedAt().toString());
		context.addMessage("createMessage", new FacesMessage(FacesMessage.SEVERITY_INFO, "Save sucessful", message));
		clearFields();
	}

	public void deleteUser(Integer idUser) {

		final String faceMessage = "readMessage";
		final String deleteError = "Error on delete";
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		if (idUser == null) {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, deleteError, "Invalid user id."));
			return;
		}

		UserEntity user = new UserEntity();
		user.setIdUser(idUser);
		List<UserEntity> entities = dao.findUserById(user);
		if (entities.isEmpty()) {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, deleteError, "User not found."));
			return;
		}

		boolean deleted = dao.deleteUser(entities.get(0));
		if (deleted) {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "Delete sucessful", "User was deleted."));
		} else {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, deleteError, "Something went wrong"));
		}
	}

	public String findUserById(Integer idUser) {
		final String faceMessage = "readMessage";
		final String findError = "Error on find";
		final String redirect = "read?faces-redirect=true";
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		if (idUser == null) {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, findError, "Invalid user id."));
			return redirect;
		}

		UserEntity user = new UserEntity();
		user.setIdUser(idUser);
		List<UserEntity> entities = dao.findUserById(user);
		if (entities.isEmpty()) {
			context.addMessage(faceMessage,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, findError, "User not found."));
			return redirect;
		}
		currentUser = entities.get(0);
		return redirect;
	}

	public String cancelUpdate() {
		clearFields();
		return "read?faces-redirect=true";
	}

	public String updateUser() {
		dao.updateUser(currentUser);
		clearFields();
		FacesContext context = FacesContext.getCurrentInstance();
		context.getExternalContext().getFlash().setKeepMessages(Boolean.TRUE);
		context.addMessage("readMessage",
				new FacesMessage(FacesMessage.SEVERITY_INFO, "Update sucessful", "User was updated."));
		return "read?faces-redirect=true";
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

}
