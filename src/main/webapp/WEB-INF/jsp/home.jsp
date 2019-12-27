<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:th="http://www.thymeleaf.org"
      xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
      xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Github DashBoard</title>

<style type="text/css">
  span.error{
    color: red;
    margin-left: 5px;
  }

  table, th, td {
    border: 1px solid black;
    border-collapse: collapse;
  }
</style>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script th:inline="javascript">

$(function() {
   $('input[name=profile-detail]').click(function(e) {
      e.preventDefault();
      $.post({
         url : 'http://localhost:8080/v1/profile',
         data : $('form[name=profile-detail-form]').serialize(),
         success : function(res) {
            if(!res.isError){
               $('#error-div').hide();
               $('#search-history').empty();
               $('#resultContainer pre code').text(JSON.stringify(res));
               $('#resultContainer').show();
            }else{
              $('#error-div').show(res.message);
            }
         }
      })
   });

$('input[name=search-history]').click(function(e) {
    e.preventDefault();
    getSearchHistory();

 });

    function getSearchHistory(){
        $.get({
                            url : 'http://localhost:8080/v1/profile',
                            data : $('form[name=search-history-form]').serialize(),
                            success : function(res) {

                               if(!res.isError){
                                  $('#resultContainer').hide();
                                  $('#error-div').hide();
                                  $('#search-history').empty();
                                  displaySearchHistory(res);
                                  $('#search-history').show();
                               }else{
                               $('#error-div').show(res.message);
                               }
                            }
                         })
    }

     function displaySearchHistory(res){
        var a = "";
        /*<![CDATA[*/
        var userId = [[${userId}]];
        /*]]>*/
        $.each(res.searchHistoryList, function(key,value) {
            a += (" <tr>" +
                    "<td>" + value + "</td>" +
                    "<td>"+
                    "<form method='delete' name='delete-history-form-" + value + "'>" +
                    "<input type='hidden' name='userId' value=" + userId + ">"+
                    "<input type='hidden' name='handle' value='"+value+"'>"+
                    "<input type='button' value='Delete' name='delete-history-"+value+"'>"+
                    "</form>" +
                    "</td>" +
                    "</tr>");
                    bindDeleteHistoryEvent(value);
        });

        $('<table>').html(
                a +
                "</table>"
                ).appendTo('#search-history');
     }

     function bindDeleteHistoryEvent(handle){
        $(document).on("click", 'input[name=delete-history-'+handle+']' , function() {
                      $.ajax({
                         type:"GET",
                         url : 'http://localhost:8080/v1/profile/delete',
                         data : $('form[name=delete-history-form-'+handle+']').serialize(),
                         success : function(res) {

                            if(!res.isError){
                               $('#error-div').hide();
                               $('#search-history').hide();
                               getSearchHistory()
                            }else{
                              $('#error-div').show(res.message);
                            }
                         }
                      });
             });
     }

});
</script>
</head>

<body>
  <hr />

  <form  method="post" name="profile-detail-form">
    <input type="hidden" name="userId" th:value="${userId}">
    <table>
      <tr>
        <td><input name="handle" id="handle" type="text" placeholder="Enter handle"/></td>
        <td><input type="submit" name="profile-detail"></td>
      </tr>
    </table>
  </form>

  <form  method="get" name="search-history-form">
      <input type="hidden" name="userId" th:value="${userId}">
      <table>
        <tr>
              <td></td>
              <td><input type="submit" name="search-history" value="Get Search history"></td>
        </tr>
      </table>
  </form>

<div id="error-div" style="display: none;"></div>

<!-- Result Container  -->
<div id="resultContainer" style="display: none;">
 <hr/>
  <pre style="color: green;">
    <code></code>
   </pre>
</div>


<div id="search-history" style="display: none;">
Search History
</div>
</body>
</html>