layui.use(['form','element','layer','jquery'],function(){
    var $ = layui.$;

    $.getJSON(
        "http://teacher.penglei.com:9051/teacher/linkRedis",
        function () {
        }
    )

    $.getJSON(
        "http://www.penglei.com:8051/course/linkRedis",
        function () {
        }
    )
    $.getJSON(
        "http://login.penglei.com:9999/view/linkRedis",
        function () {
        }
    )
})