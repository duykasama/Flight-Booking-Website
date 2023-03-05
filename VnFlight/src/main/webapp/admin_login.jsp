<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="en">
    <head>
        <title>Login Admin</title>        
        <%@include file="css.jsp"%>
    </head>
    <body id="page1">
        <%@include file="/header.jsp" %>
        <div class="main">
            <section id="content">
                <article class="col1">
                    <div class="pad_1">
                        <h2 class="h2_style">Contact Us</h2>
                        <span class="cols"> Country:<br>
                            City:<br>
                            Telephone:<br>
                            Email: </span> USA<br>
                        San Diego<br>
                        +354 5635600<br>
                        <a href="#">businessco@mail.com</a>
                        <h2 class="h2_style">Miscellaneous Info</h2>
                        <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia.</p>
                    </div>
                </article>

                <!--Login Part-->
                <div class="col2 pad_left1 login_div">
                    <c:url var="loginLink" value="${request.contextPath}/AdminAccess/login"/>
                    <form action="${loginLink}" name="" method="POST">
                        <!-- Main container for all inputs -->
                        <div class="mainContainer">
                            <!-- Adminname -->
                            <label class="label_title" for="adminname">Adminname</label>
                            <input type="text" placeholder="Enter Adminname" name="adminname" required>

                            <br><br>

                            <!-- Password -->
                            <label class="label_title" for="pswrd">Password</label>
                            <input type="password" placeholder="Enter Password" name="password" required>

                            <!-- sub container for the checkbox and forgot password link -->
                            <div class="subcontainer">
                                <label>
                                    <input type="checkbox" checked="checked" name="remember"> Remember me
                                </label>
                                <p class="forgotpsd"><br> <a href="#">Forgot Password?</a></p>
                            </div>


                            <!-- Submit button -->
                            <button type="submit">Login</button>

                            <!-- Sign up link -->
                            <p class="register">Don't have an account?<br><a href="#">Create new account!</a></p>

                        </div>
                    </form>
                </div>
                <!--End Login Part-->       
            </section>
        </div>
        <%@include file="/footer.jsp" %>
    </body>
</html>