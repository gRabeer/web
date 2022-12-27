<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <style type="text/css">
    body {
        background: linear-gradient(#F5DEB3, #A52A2A);
        }
    h1 {
       color: black;
       display: flex;
       justify-content: center;
       background: purple;
       }

    </style>
    <title>Информация</title>

</head>
<body>
<h1>Редактирование записи</h1><hr>
<h5>Актуальная информация:</h5><hr>
<h5>Фотография преподавателя</h5>
<#list images as img>
<img src="/images/${img.id?if_exists}" height="100px"/><br><br>
</#list>
<b>Дисциплина: </b>${product.title?if_exists}<br>
<b>Имя преподавателя: </b>${product.description?if_exists}<br>
<b>Группа (ID): </b>${product.price?if_exists}<br>
<b>Наличие курсовой работы: </b>${product.author?if_exists}<br>
<b>Количество зачетных единиц: </b>${product.city?if_exists}<br>
<hr>

<h5>Редактирование:</h5><hr>
<form action="/product/edit/${product.id}" method="post" enctype="multipart/form-data">
    <!--<input type="number" name="id"/><br>-->

    <p class="form-p">Дисциплина: <input type="text" name="title"/><br></p>

    <p class="form-p">Преподаватель: <input type="text" name="description"/><br></p>

    <p class="form-p">ID группы: <input type="number" name="price"/><br></p>

    <p class="form-p">Наличие курсовой: <input type="text" name="author"/><br></p>

    <p class="form-p">Кол-во зач. ед.: <input type="text" name="city"/><br></p>

    <p class="form-p">Фотограция: <input type="file" name="file1"/><br></p>

    <input type="submit" value="Обновить запись"/><br><hr>

<h4>Удаление</h4>
</form>
<form action="/product/delete/${product.id}" method="post">
    <input type="submit" value="Удалить запись"/>
</form>

</body>
</html>