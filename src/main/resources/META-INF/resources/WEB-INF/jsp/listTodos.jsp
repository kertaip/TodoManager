<%@ include file="common/headers.jspf" %>
<%@ include file="common/navigation.jspf" %>
<div class="container">
	<c:if test = "${pendingTodos.size() eq 0}">
		<center><img src="img/well_done.gif" alt="Well done!" /></center>
	</c:if>
	<c:if test = "${pendingTodos.size() gt 0}">
		<h1>Your pending Tasks</h1>
		<table class="table" >
			<thead>
				<tr>
					<th>Description</th>
					<th>Target date</th>
					<th>Set done</th>
					<th>Update</th>
					<th>Delete</th>

				</tr>
			</thead>
			<c:forEach items="${pendingTodos}" var="pendingTodo">
				<tr>
					<td>${pendingTodo.description}</td>
					<td>${pendingTodo.targetDate}</td>
					<td><a href="switch-todo-done?id=${pendingTodo.id}" class="btn btn-success">DONE</a></td>
					<td><a href="update-todo?id=${pendingTodo.id}" class="btn btn-warning">UPDATE</a></td>
					<td><a href="delete-todo?id=${pendingTodo.id}" class="btn btn-danger">DELETE</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>

	<a href="add-todo" class="btn btn-success">Add Todo</a>
    <br><br><br><br>

	<h1>Your completed Tasks</h1>
	<table class="table" >
		<thead>
			<tr>
				<th>Description</th>
				<th>Target date</th>
				<th>Set undone</th>
				<th>Delete</th>

			</tr>
		</thead>
		<c:forEach items="${doneTodos}" var="doneTodo">
			<tr>
				<td>${doneTodo.description}</td>
				<td>${doneTodo.targetDate}</td>
				<td><a href="switch-todo-done?id=${doneTodo.id}" class="btn btn-warning">UNDONE</a></td>

				<td><a href="delete-todo?id=${doneTodo.id}" class="btn btn-danger">DELETE</a></td>
			</tr>
		</c:forEach>
	</table>


</div>
<%@ include file="common/footers.jspf" %>