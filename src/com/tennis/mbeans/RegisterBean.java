package com.tennis.mbeans;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;

import org.primefaces.event.SelectEvent;

import com.tennis.exceptions.EmailIsAlreadyExistException;
import com.tennis.models.Player;
import com.tennis.models.Role;
import com.tennis.models.User;
import com.tennis.models.Player.Gender;
import com.tennis.services.UserService;

@ManagedBean(name = "register")
public class RegisterBean {

	private User user;
	private Player player;
	private List<SelectItem> roles;
	private List<SelectItem> genders;
	private boolean disableAge;
	private Date player_birthdate;

	@PostConstruct
	public void init() {
		disableAge = true;
		user = new User();
		
		player = new Player();
		
		roles = new ArrayList<SelectItem>();
		roles.add(new SelectItem(Role.ADMIN));
		roles.add(new SelectItem(Role.PARENT));
		roles.add(new SelectItem(Role.PLAYER));
		
		genders = new ArrayList<SelectItem>();
		genders.add(new SelectItem(Gender.MALE));
		genders.add(new SelectItem(Gender.FEMALE));

	}

	@EJB
	UserService userService;

	public String saveUser() {
		
		addDetailsAccoringToRole(this.user);
		
		try {
			if(this.user.getRole().equals(Role.PLAYER)) {
				userService.saveUser(this.player);
			}
				
			
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_INFO, "", "User is saved "));
		} catch (EmailIsAlreadyExistException e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_WARN, "", "User is already exist "));
		} 
		return "secure/register";
		
	}

	private void addDetailsAccoringToRole(User user) {
		if(this.user.getRole().equals(Role.PLAYER)) {
			
			this.player.setFirstname(user.getFirstname());
			this.player.setLastname(user.getLastname());
			this.player.setEmail(user.getEmail());
			this.player.setPhone_num(user.getPassword());
			this.player.setBirthdate(convertDateToLocaldate());
			this.player.setAge(calculateAge());
			this.player.setFirstname(user.getFirstname());
			
			
		}
	}

	private int calculateAge() {
		return Period.between(convertDateToLocaldate(), LocalDate.now()).getYears();
	}

	public void enableInputText(ValueChangeEvent event) {
		System.out.println(event.getNewValue());
		Role role = (Role) event.getNewValue();

		if (role.equals(Role.PLAYER)) {
			setDisableAge(false);
		} else if (role.equals(Role.ADMIN)) {
			setDisableAge(true);
		} else if (role.equals(Role.PARENT)) {
			setDisableAge(true);
		}

	}

	public LocalDate convertDateToLocaldate() {
		LocalDate birthdate = getPlayer_birthdate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return birthdate;
	}
	
	
	
	public Gender[] getGenderValues() {
		return Gender.values();
	}
	
	
	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isDisableAge() {
		return disableAge;
	}

	public void setDisableAge(boolean disableAge) {
		this.disableAge = disableAge;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<SelectItem> getRoles() {
		return roles;
	}

	public void setRoles(List<SelectItem> roles) {
		this.roles = roles;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public List<SelectItem> getGenders() {
		return genders;
	}

	public void setGenders(List<SelectItem> genders) {
		this.genders = genders;
	}

	public Date getPlayer_birthdate() {
		return player_birthdate;
	}

	public void setPlayer_birthdate(Date player_birthdate) {
		this.player_birthdate = player_birthdate;
	}

	
	
}
