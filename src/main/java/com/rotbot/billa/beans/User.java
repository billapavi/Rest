package com.rotbot.billa.beans;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name="rotbot_users")
public class User {

	@Id
	private String userID;
	@Column
	private String userName;
	@Column
	private String ApiKey;
	@Column
	private String password;
}
