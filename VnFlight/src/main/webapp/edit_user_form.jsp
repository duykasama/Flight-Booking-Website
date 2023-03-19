<%-- 
    Document   : edit_user_form
    Created on : Mar 19, 2023, 11:38:58 PM
    Author     : MSI GF63
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Document</title>
        <style>
            form {
                max-width: 500px;
                margin: 0 auto;
                padding: 20px;
                border-radius: 5px;
                background-color: #f2f2f2;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.3);
            }

            h2 {
                font-size: 24px;
                margin-bottom: 20px;
                text-align: center;
            }

            .form-group {
                margin-bottom: 20px;
            }

            label {
                display: block;
                margin-bottom: 10px;
                font-size: 16px;
                font-weight: bold;
                color: #333;
            }

            input[type="text"],
            input[type="email"],
            input[type="tel"] {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border: none;
                border-radius: 5px;
                background-color: #fff;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
            }

            input[type="text"]:focus,
            input[type="email"]:focus,
            input[type="tel"]:focus {
                outline: none;
                box-shadow: 0 2px 5px rgba(0, 0, 0, 0.2);
            }

            .form-actions {
                margin-top: 20px;
                display: flex;
                justify-content: center;
                align-items: center;
            }

            .btn {
                padding: 10px 20px;
                font-size: 16px;
                border-radius: 5px;
                cursor: pointer;
                transition: all 0.3s ease-in-out;
            }

            .btn-primary {
                background-color: #4caf50;
                color: #fff;
                border: none;
                margin-right: 10px;
            }

            .btn-primary:hover {
                background-color: #3e8e41;
            }

            .btn-secondary {
                background-color: #e7e7e7;
                color: #333;
                border: none;
            }

            .btn-secondary:hover {
                background-color: #ddd;
            }
        </style>
    </head>
    <body>
        <form action="AdminUserListController" method="post">
            <h2>Edit User Information</h2>
            <input name="userId" value="${user.getUserId()}"/>
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" value="${user.getUserName()}"/>
            </div>

            <div class="form-group">
                <label for="phone">Phone number:</label>
                <input type="tel" id="phone" name="phone" value="${user.getPhone()}"/>
            </div>

            <div class="form-group">
                <label for="email">Email:</label>
                <input type="email" id="email" name="email" value="${user.getEmail()}"/>
            </div>

            <div class="form-actions">
                <button name="action" value="confirmEdit" type="submit" class="btn btn-primary">Save</button>
                <button name="action" value="cancelEdit" type="submit" class="btn btn-secondary">Cancel</button>
            </div>
        </form>
    </body>
</html>

