<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<input type="button" id="test_btn" value="강남역" />

<div id="map_wrapper"></div>

<script>
$("#test_btn").click(function() {
      $.ajax({
         type: "POST"
         , url: "/test"
         , data: {
            where: $(this).val()
         }
         , dataType: "html"
         , success: function(result) {
            console.log("AJAX 성공")
            console.log(result)
            $("#map_wrapper").html(result);
         }
         , error: function() {
            console.log("AJAX 실패")
         }
      });
});
</script>

</body>
</html>