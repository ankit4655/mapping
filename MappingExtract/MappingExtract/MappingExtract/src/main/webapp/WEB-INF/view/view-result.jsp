<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE >
<html>
<head>
 <title>XML Import Copy Review OutPut</title>
</head>
<body>
<p>XML Import has been completed and we have reviewed the below points.</p>
<table>
   <c:forEach var="tempOutPut" items="${output}">
     <tr>
      <td><c:out value="${tempOutPut}"/></td>
    </tr>
   </c:forEach>
</table>
</body>

</html>