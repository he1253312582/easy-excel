<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <title>文件上传</title>
</head>
<body>

<form method="post" action="/upload" enctype="multipart/form-data">
    <input type="file" name="file1"><br>
    <input type="submit" value="提交">
</form>
<hr>
<form method="post" action="/multiUpload" enctype="multipart/form-data">
    <input type="file" name="file1"><br>
    <input type="file" name="file2"><br>
    <input type="file" name="file3"><br>
    <input type="file" name="file4"><br>
    <input type="file" name="file5"><br>
    <input type="submit" value="提交">
</form>
</body>
</html>