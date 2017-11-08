
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<c:set var="now" value="<%=new java.text.SimpleDateFormat(\"yyyy-MM-dd'T'HH:mm\").format(new java.util.Date())%>" />
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<html>
<head>
  <style>
    table {
      font-family: arial, sans-serif;
      border-collapse: collapse;
      width: 100%;
    }

    td, th {
      border: 1px solid #dddddd;
      text-align: left;
      padding: 8px;
    }

    tr:nth-child(even) {
      background-color: #dddddd;
    }
    td:nth-last-child(1) {
      border: none;
      background-color: #ffffff;
    }
    .error {
      color: red;
    }
  </style>
</head>
<body>
  <p class="error">${error}</p>
  <table>
    <h1>Booking List</h1>
    <tr>
      <th>Booked From</th>
      <th>Booked To</th>
      <th>Car number (info)</th>
    </tr>
    <c:forEach items="${bookings}" var="booking">
      <tr>
        <td>${booking.bookedFrom}</td>
        <td>${booking.bookedTo}</td>
        <td>${booking.car.id} (${booking.car.model}, ${booking.car.color})</td>
        <td>
          <form:form method="post" action="${contextPath}/bookings/${booking.id}/delete">
            <input type="submit" value="delete">
          </form:form>
        </td>
      </tr>
    </c:forEach>
    <h2>Create your account</h2>
    <form:form method="post" modelAttribute="booking">
      <tr>
        <td>
          <spring:bind path="bookedFrom">
            <div>
              <form:input type="datetime-local" path="bookedFrom" placeholder="bookedFrom" value="${now}"></form:input>
            </div>
          </spring:bind>
        </td>
        <td>
          <spring:bind path="bookedTo">
            <div>
              <form:input type="datetime-local" path="bookedTo" placeholder="bookedTo" value="${now}"></form:input>
            </div>
          </spring:bind>
        </td>
        <td>
          <spring:bind path="car">
            <div>
              <form:select path="car">
                <c:forEach items="${cars}" var="car">
                  <form:option value="${car}">
                    ${car.id} (${car.model}, ${car.color})
                  </form:option>
                </c:forEach>
              </form:select>
            </div>
          </spring:bind>
        </td>
        <td>
          <button type="submit">Submit</button>
        </td>
      </tr>
    </form:form>
  </table>
</body>
</html>