var TT = SCEN = {
    checkLogin: function () {
        var _ticket = $.cookie("SCEN_TOKEN");
        if (!_ticket) {
            return;
        }
        $.ajax({
            url: "http://localhost:8085/user/token/" + _ticket,
            dataType: "jsonp",
            type: "GET",
            success: function (data) {
                if (data.status == 200) {
                    var username = data.data.username;
                    var html = username + "，欢迎来到Scen商城！<a href=\"javascript:void(0)\"" + _ticket + "\" class=\"link-logout\" onclick=\"logout()\">[退出]</a>";
                    $("#loginbar").html(html);
                }
            }
        });
    }
}

$(function () {
    // 查看是否已经登录，如果已经登录查询登录信息
    TT.checkLogin();
});