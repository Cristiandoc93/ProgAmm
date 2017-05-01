<%-- 
    Document   : login
    Created on : 20-apr-2017, 17.55.00
    Author     : Cristian
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    
    <head>
        <title>.:Login to NerdBook!:.</title>
        <meta charset="UTF-8">
        <meta name="author" content="Cristian">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta name="keywords" content="persone world future amici social">  
        <link rel="stylesheet" type="text/css" href="style.css" media="screen">
    </head>
    
    <body>
        
            <c:set var="title" value="Login" scope="request"/>
        <div id="page">
           
          
      
        
        <div id="contentLo">
           
            <div id="loginbody">
        
                <div id="titlelogin">
                    <h1>NerdBook</h1>
            </div>
            
                <div id="loginuser">
             
            
        
                    <form action="Login" method="post">
                        <!--<input type="hidden" name="cmd" value="login"> -->
                        <br/>
                        <label for="user">Username</label>
                        <input name="Username" id="user" type="text" value=""/>
                        <br/>
                        <label for="password">Password</label>
                        <input name="Password" id="password" type="password" value=""/>  
                        <br/>

                        <input type="submit" name="Submit" value="Accedi">
                    </form>
                            <%
                                if(null!=request.getAttribute("logError"))
                                    {
                                     out.println(request.getAttribute("logError"));
                                    }
                            %>
                </div>
         
            </div>
        </div> 
           
        <footer>
            <p> Copyright Cristian 2017</p>
        </footer> 
       </div>
        
    </body>
 
</html>

