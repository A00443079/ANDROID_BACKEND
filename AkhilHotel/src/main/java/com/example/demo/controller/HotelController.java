package com.example.demo.controller;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.DB.GetDB;
import com.example.demo.DB.OtherDB;
import com.example.demo.DB.PostDB;
import com.example.demo.models.Guest;
import com.example.demo.models.HotelsList;
import com.example.demo.models.ReservationConfirmation;
import com.example.demo.models.ReservationDetails;
import com.example.demo.models.SignupError;
import com.example.demo.models.User;


@RestController
public class HotelController 
{

			//API CALL
			@RequestMapping("/")
			public String home()
			{
				
				String str="<h3>HOME</h3><br>\n";
				String str1="<h3>This API is developed by:</h3><br><br>\n";
				String str2="<h1>AKHIL ROY - A00443079</h1><br>\n";
						
				return(str+str1+str2);
			}
			
			
			
			//API CALL
			@RequestMapping("/about")
			public String about()
			{
				return("MCDA 5550\t\t\n\nAssignmmnet 1:\t\t\nBuilding Rest API for HOTEL RESERVATION ");
			}

			//API CALL ANDROID 2
			@RequestMapping(value="/login", method=RequestMethod.POST, consumes="application/json", produces="application/json")
			public Object login(@RequestBody User u)
			{
				SignupError se = new SignupError();
				String query="select * from user where username="+"\""+u.getEmail()+"\" AND password="+"\""+u.getPassword()+"\"";
				if(OtherDB.exists(query))
				{
					se.setSuccess(true);
					se.setError(null);
					se.setMessage("Login Successful !");
				}
				else
				{
					se.setSuccess(false);
					se.setError("Email or password is Invalid");
					se.setMessage(null);
				}
				return((Object)se);
			}
			//API CALL ANDROID 1
			@RequestMapping(value="/signup", method=RequestMethod.POST, consumes="application/json", produces="application/json")
			public Object signup(@RequestBody User u)
			{
				
				String query="select * from user where username="+"\""+u.getEmail()+"\"";
				SignupError se = new SignupError();
				
				if(OtherDB.exists(query))
				{
					se.setSuccess(false);
					se.setError("Email already exists !");
					se.setMessage(null);
					
				}
				else if (!u.getPassword().equals(u.getConfirm_password()))
				{
					se.setSuccess(false);
					se.setError("Passwords not matching !");
					se.setMessage(null);
				}
				else
				{
					String sql = "insert into user values("+"\""+u.getEmail()+"\", "+"\""+u.getPassword()+"\""+");";
					System.out.println(sql);
					boolean result = OtherDB.insert(sql);
					if(result== false) return(null);
					else
					{
						se.setSuccess(true);
						se.setError(null);
						se.setMessage("Successfully Registered");
					}
				}
				return((Object)se);
			}
			//API CALL-1
			@RequestMapping(value="/hotels", method=RequestMethod.GET, produces="application/json")
			public HotelsList getListOfHotels()
			{
				HotelsList HL=null;
				
				try
				{
					HL=GetDB.getRecords("select * from hotel");
				}
				catch(Exception e) {}
				
				return(HL);
			}
			
			//API CALL-2
			@RequestMapping(value="/bookingConfirmation", method=RequestMethod.POST, consumes="application/json", produces="application/json")
			public Object reservationConfirmation(@RequestBody ReservationDetails rd)
			{
				
				//checks in the database and creates a UNIQUE confirmation_number
				String id = PostDB.generateConfirmationNumber();
				
				//status of insertion, true if success, false if failure
				boolean status= PostDB.insertNewReservation(id, rd);
				
				//If insertion fails
				if(!status)
				{
					id=id+"  ==>  insertion failed";
				}
				
				ReservationConfirmation rc = new ReservationConfirmation();
				rc.setConfirmation_number(id);
				return((Object)rc);
			}
			
			//Extra API one
			//API CALL ANDROID 3
			@RequestMapping(
							value="/getListOfConfirmationNumbers", 
							method=RequestMethod.GET,  
							produces="application/json"
						) //Annotation end
			public List<ReservationConfirmation> getListOfConfirmationNumbers(
																	@RequestParam(
																			value="email", 
																			required=true
																		 ) String email
																   ) //function definition end
			{
				String query = "select confirmation_number from userreservation where username=\""+email+"\"";
				List<ReservationConfirmation> l = OtherDB.getIdList(query);
				return(l);
			}
			
			
			//API CALL ANDROID 4
			@RequestMapping(
							value="/cancel", 
							method=RequestMethod.GET,  
							produces="application/json"
						) //Annotation end
			public Object delete(
																	@RequestParam(
																			value="id", 
																			required=true
																		 ) String id
																   ) //function definition end
			{
				// Inline class definition
				class MyObject extends Object
				{
					private String message;

					public String getMessage() {
						return message;
					}

					public void setMessage(String message) {
						this.message = message;
					}
				}
				
				MyObject o = new MyObject();
				
				
				boolean result=OtherDB.delete(id);
				if(result) o.setMessage("Cancellation Successful !!");
				else o.setMessage("Cancellation Failure !!");
				
				
				return((Object)o);
			}
			
			
			//Extra API two
			//Annotation start
			@RequestMapping(
								value="/getReservationDetails", 
								method=RequestMethod.GET,  
								produces="application/json"
							) //Annotation end
			
			//function definition start
			public Object getReservationDetails(
										@RequestParam(
														value="id", 
														required=false, 
														defaultValue="00000000-0000-0000-0000-000000000000"
													 ) String id
											   ) //function definition end
			{
				
				ReservationDetails rd = OtherDB.getRD(id);
				
				//if no such confirmation_number exists
				if(rd==null)
					{
						// Inline class definition
						class MyObject extends Object
						{
							private String error;
							public String getError() {
								return error;
							}
							public void setError(String error) {
								this.error = error;
							}
							
						}
						MyObject o= new MyObject();
						o.setError("confirmation_number = "+id+"  DOES NOT EXIST !!!");
						return((Object)o);
					}
				
				
				
				return((Object)rd);
			}
			
		
			
}
