
<%@ include file="header.jsp" %>

<br>
<h2 align="center">Personal Details</h2>
<br>

 <div class="container">
 <form:form method="POST" modelAttribute="userDetails">
  
    
        <div class="form-group row">
        <form:label type="hidden" path="userId" class="col-sm-2 col-form-label"></form:label>
              <div class="col-sm-10">
        <form:input type="hidden" path="userId" class="form-control"/>
        </div>
    </div>
        
   
    <div class="form-group row">
        <form:label path="userName" class="col-sm-2 col-form-label">User Name</form:label>
                ${userDetails.userName}
              <div class="col-sm-10">
              </div>
              </div>
     
    
        <div class="form-group row">
        <form:label path="fname" class="col-sm-2 col-form-label">First Name</form:label>
         <div class="col-sm-10">
                 ${userDetails.fname}
               </div>
              </div>
              
              
        <div class="form-group row">
        <form:label path="lname" class="col-sm-2 col-form-label">Last Name</form:label>
                      <div class="col-sm-10">
             ${userDetails.lname}
            </div>
              </div>     
        
        
        <div class="form-group row">
        <form:label path="contact" class="col-sm-2 col-form-label">Contact Number</form:label>
                      <div class="col-sm-10">
            ${userDetails.contact}
               </div>
              </div>
              
         
        <div class="form-group row">
        <form:label path="email" class="col-sm-2 col-form-label">Email</form:label>
                      <div class="col-sm-10">
              ${userDetails.email}
           </div>
              </div>  
              
              <div class="form-group row">
      <div class="offset-sm-2 col-sm-10">
      <input name="_eventId_edit" type="submit" value="Edit" class="btn btn-primary"/>
       <input name="_eventId_submit" type="submit" value="AddDetails" class="btn btn-primary"/>      
      </div>
    </div>
    
              </form:form>
              </div>



 <%@ include file="footer.jsp" %>
