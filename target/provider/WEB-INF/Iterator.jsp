<%--Iterator.jsp --%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<%@page import="java.util.ArrayList"%>      <%--Importing all the dependent classes--%>
<%@page import="com.dao.EmployeeDetails"%>
<%@page import="java.util.Iterator"%>

<% ArrayList<EmployeeDetails> employeeList = (ArrayList) request.getAttribute("EmpList"); %> <%--Assigning ArrayList object containing Employee data to the local object --%>

<strong><a href="<%=request.getContextPath()%>/IteratorExample?type=getDetails">Show Employee Details</a></strong>
<br></br>

<table cellspacing="2" cellpadding="2">

    <tr><th>Employee ID</th><th>Employee Age</th><th>Employee Name</th><th>Employee City</th></tr>
    <%
        // Iterating through subjectList

        if(request.getAttribute("EmpList") != null)  // Null check for the object
        {
            Iterator<EmployeeDetails> iterator = employeeList.iterator();  // Iterator interface

            while(iterator.hasNext())  // iterate through all the data until the last record
            {
                EmployeeDetails empDetails = iterator.next(); //assign individual employee record to the employee class object
    %>
    <tr><td><%=empDetails.getEmployeeID()%></td>
        <td><%=empDetails.getEmployeeAge()%></td>
        <td><%=empDetails.getEmployeeName()%></td>
        <td><%=empDetails.getEmployeeCity()%></td>
    </tr>
    <%
            }
        }
    %>
</table>

</body>
</html>