<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
  <head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9z9Jm+ZxgW6KvJfQzjz+7XlL4OyZ7wKt6+JQvYjz8bWdVg4yfj0sX6wqU9hQzJ/" crossorigin="anonymous">

    <title>Hello, world!</title>
  </head>
  <body>
    <div class="container">
      <h1>Add a Pet</h1>
      <form>
        <div class="mb-3">
          <label for="petName" class="form-label">Pet Name</label>
          <input type="text" class="form-control" id="petName" aria-describedby="petNameHelp">
          <div id="petNameHelp" class="form-text">Enter your pet's name.</div>
        </div>
        <div class="mb-3">
          <label for="petType" class="form-label">Pet Type</label>
          <input type="text" class="form-control" id="petType" aria-describedby="petTypeHelp">
          <div id="petTypeHelp" class="form-text">Enter your pet's type.</div>
        </div>
        <button type="submit" class="btn btn-primary">Submit</button>
      </form>
    </div>

    <!-- Bootstrap JavaScript -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-4nFJWd0k8o+OeLlZG+eTf5VXrNlMx7tHcYrDm7vIaCqKpBvGqVnD9tRi8WZn0p/" crossorigin="anonymous"></script>
  </body>
</html>