
function getCheckCode() {
    $(".captcha-img").append("<div id='waitLogin' style='color: red'>正在加载...</div>");
    $("#getCheckCode").hide();
    $.ajax({
        url: "view/getVerify",
        success: function(){
                $("#waitLogin").hide();
                $(".captcha-img").html("<a href=\"javascript:void(0);\" title=\"点击更换验证码\">\n" +
                                        "<img id=\"captchaPic\"\n" +
                                        "src=\"/view/getVerify\"\n" +
                                        "alt=\"更换验证码\"\n" +
                                        "height=\"38\" width=\"110\"\n" +
                                        "onclick=\"getVerify(this);\"\n" +
                                        "</a>\n");
        }
    }
    );
}
function getVerify() {
    $("#captchaPic").attr("src", '/view/getVerify?' + Math.random());
}
function aVerify() {
    var value = $("#verify_input").val();
    alert(value);
    /*
    $.ajax({
        async: false,
        type: 'post',
        url: 'login/checkVerify',
        dataType: "json",
        data: {
            verifyInput: value
        },
        success: function (result) {
            if (result) {
                alert("success!");
            } else {
                alert("failed!");
            }
            // window.location.reload();
            getVerify();
        }
    });

     */
}

