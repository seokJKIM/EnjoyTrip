<%@ page import="java.util.*" %>
<%@ page import="org.json.*" %>
<%@ page import="com.ssafy.trip.place.model.Place" %>
<%@ page language="java" contentType="application/json; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	ArrayList<Place> places = (ArrayList<Place>)request.getAttribute("places");
	JSONArray jsonArr = new JSONArray();
	if(places == null){
		JSONObject json = new JSONObject();
		json.put("overview", "overview");
		jsonArr.put(json);
	}else{
		for(Place place:places){
			JSONObject json = new JSONObject();
			json.put("overview", place.getOverview());
			json.put("title", place.getTitle());
			json.put("latitude", String.valueOf(place.getLat()));
			json.put("longitude", String.valueOf(place.getLng()));
			json.put("addr1", place.getAddr1());
			json.put("first_image", place.getImage());
			jsonArr.put(json);
		}
	}
%>
<%=jsonArr.toString()%>