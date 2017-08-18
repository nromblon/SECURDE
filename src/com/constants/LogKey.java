package com.constants;

public class LogKey {
	
	public static final String LOGIN_SUCCESS = "LOGIN_SUCCESS";
	public static final String LOGIN_FAIL = "LOGIN_FAIL";
	public static final String LOGOUT = "LOGOUT";
	public static final String ACC_LOCKOUTS = "ACC_LOCKOUTS";
	public static final String ACC_CREATION = "ACC_CREATION";
	public static final String LOGIN_ATTEMPT = "LOGIN_ATTEMPT";

	//Authorization Attempts
	public static final String AUTH_ATTEMPT = "AUTHORIZATION_ATTEMPT";
	public static final String AUTH_SUCCESS = "AUTHORIZATION_SUCCESSFUL";
	
	//Administrative Functions
	public static final String ACC_CREATION_MANAGER = "ACC_CREATION_MANAGER";
	public static final String ACC_CREATION_STAFF = "ACC_CREATION_STAFF";
	public static final String ACC_UNLOCK = "ACC_UNLOCK";
	
	//Data modification
	public static final String ADD_PUB = "ADD_PUBLICATION";
	public static final String EDIT_PUB = "EDIT_PUBLICATION";
	public static final String DELETE_PUB = "DELETE_PUBLICATION";
	public static final String RESERVE_PUB = "RESERVE_PUBLICATION";
	public static final String OVERRIDE_PUB = "PUB_RESERVATION_OVERRIDE";
	
	public static final String ADD_REVIEW = "ADD_REVIEW";
	
	public static final String RESERVE_RM = "RESERVE_ROOM";
	public static final String CANCEL_RM = "CANCEL_ROOM_RESERVE";
	
	public static final String CHANGE_PASS = "CHANGE PASSWORD";
	
	//User search
	public static final String SEARCH = "QUERY_SEARCH";
}
