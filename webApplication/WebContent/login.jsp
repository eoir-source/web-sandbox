<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Account Login</title>
<script language="JavaScript">
	var StrUser, StrPass, url;
    function InitializeService()
    {
        StrUser = null;
        StrPass = null;
    	url = "http://localhost:8080/webApplication/Login?wsdl";
    }
    var StrUser, StrPass;
    function getCredentials()
    {
		var pl = new SOAPClientParameters();
		pl.add("UserName", document.userLogin.UserName.value);
		pl.add("PassWord", document.userLogin.PassWord.value);
		SOAPClient.invoke(url, "authenticate", pl, true, getCallBack);
    }
    function getCallBack(callBack)
    {
		alert(callBack);
    }
</script>
</head>
<body bgcolor="#f0f0f0" onload="InitializeService()" id="service" style="behavior:url(webservice.htc)">
<form name="userLogin">
Please login to access your account.<br /><br />
Username: <input type="text" name="UserName"/><br /><br />
Password: <input type="password" name="PassWord"/><br /><br />
<input type="submit" value="Login" onclick="getCredentials()"/>
<input type="reset" value="Reset"/>
</form>
</body>
</html>