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
@Table(name="location")
@org.hibernate.annotations.Entity(dynamicInsert = true, dynamicUpdate = true)
public class Location extends GenericModel{


	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;

	@Column(name="lon", nullable=false, length=45)
	@Expose
	public String lon;

	@Column(name="lat", nullable=false, length=45)
	@Expose
	public String lat;

	@Column(name="userid", nullable=false, length=45)
	@Expose
	public String userid;

}
