<#import "/spring.ftl" as spring/>
<head>
    <title>Sign-Up/Search Form</title>
        <#include "/base_libraries/head.ftl"/>
</head>
    <body>
    <#include "/Admin/menu_bar.ftl"/>
            <#if errorMessage??>
            <div class="container">
            <div class="alert alert-danger" role="alert">
             ${errorMessage}
            </div>
            </div>
            </#if>
             <div class="row"  >
                 <div class="col-md-2"></div>
                 <div class="col-md-8">
                       <form name="searchForm" action="/admin/search-user"  method="post" >
                          <!-- <label for="vat">VAT:</label>-->
                           <input type="text" class="form-control"  id="vat" placeholder="Enter VAT" autocomplet="off" name="vat" /><br>

                          <!-- <label for="email">Email:</label> -->
                           <input type="email" class="form-control" id="email" placeholder="Enter email" name="email" /><br>
                           <button type="submit" class="btn">Search</button>
                        </form>

                 </div>
            <div class="col-md-2"></div>
</div>

<hr>
<#if member??>
<div class="container">
<table class="table">
   <tr>
     <th>FirstName</th>
     <th>LastName</th>
     <th>VAT</th>
     <th>Address</th>
     <th>Cars</th>
     <th>Edit</th>
     <th>Delete</th>
   </tr>
   <tr>
    <th>${member.firstname}</th>
    <th>${member.lastname}</th>
    <th>${member.vat}</th>
    <th>${member.address}</th>
    <th>not done yet</th>
    <form action ="/admin/edit-user" name="editForm" id ="editForm" method="GET">
        <input type="hidden" name="vat" id="vat" value="${member.vat}">
    <th><input type="submit" class="btn btn-info" value="Edit"></th>
    </form>
    <form action ="/admin/delete-user" name="deleteForm" id ="deleteForm" method="POST">
        <input type="hidden" name="vat" id="vat" value="${member.vat}">
    <th><input type="submit" class="btn btn-danger" value="Delete"></th>
    </form>
  </tr>
</table>
</#if>


<#include "/base_libraries/footer.ftl"/>
<#include "/base_libraries/js.ftl"/>
    </body>