<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html>
<head>
	<title>Content Only XML Import Copy Review</title>
</head>
<body>
<form:form action="viewresults" modelAttribute="inputDetails" method="POST">
<table>
    <tr>
        <td><form:label path="username">UserName</form:label></td>
        <td><input type="text" name="username" /></td>
    </tr>
        <tr>
        <td><form:label path="password">Password</form:label></td>
        <td><input type="password" name="password" /></td>
    </tr>
    <tr>
        <td><form:label path="excelTemplate">Excel Template</form:label></td>
        <td><input type="text" name="excelTemplate" /></td>
    </tr>
    <tr>
            <td><form:label path="mappingExtract">Mapping Extract</form:label></td>
            <td><input type="text" name="mappingExtract" /></td>
     </tr>
    <tr>
            <td><input type="submit" value="Submit" /></td>
     </tr>
</table>
</form:form>

</body>
</html>