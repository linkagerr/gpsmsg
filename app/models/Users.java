package models;

import javax.persistence.*;

import java.sql.*;
import java.sql.Blob;
import javax.sql.*;
import java.math.*;
import java.util.*;
import java.util.Date;

import play.db.jpa.*;
import com.google.gson.annotations.Expose;

@Entity
@Table(name="users")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Users extends GenericModel{


	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Expose
	public Long id;

	@Column(name="username", nullable=false, length=45)
	@Expose
	public String username;

	@Column(name="password", nullable=false, length=45)
	@Expose
	public String password;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

}
