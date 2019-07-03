//时间戳转具体日期
function timestampToTime(timestamp) {
	var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
	Y = date.getFullYear() + '-';
	M = (date.getMonth() + 1 < 10 ? '0' + (date.getMonth() + 1) : date.getMonth() + 1) + '-';
	D = (date.getDate() < 10 ? '0' + date.getDate() : date.getDate()) + ' ';
	h = (date.getHours() < 10 ? '0' + date.getHours() : date.getHours()) + ':';
	m = (date.getMinutes() < 10 ? '0' + date.getMinutes() : date.getMinutes()) + ':';
	s = (date.getSeconds() < 10 ? '0' + date.getSeconds() : date.getSeconds());
	return Y + M + D + h + m + s;
};

//从cookie中获取登陆id
jQuery(function($) {
	//显示用户名
	$("#us_name").html($.cookie("us_name"));
	//将Cookie中的id设置进个人信息中的id中
	var type = getUserById($.cookie("us_id")).us_type;
	if(type == "student") {
		//个人信息
		$("#profile").attr("href", "/views/userInformation/studentProfile.html?id=" + $.cookie("us_id"));
		//个人设置
		$("#setting").attr("href", "/views/userInformation/studentSetting.html");
	} else if(type == "teacher") {
		$("#profile").attr("href", "/views/userInformation/teacherProfile.html?id=" + $.cookie("us_id"));
		$("#setting").attr("href", "/views/userInformation/teacherSetting.html");
	} else {
		$("#profile").attr("href", "#");
		$("#setting").attr("href", "#");
	}

});

jQuery(function($) {

	//获得自己的头像

	$.ajax({
		type: "get",
		url: "/user/getPhoto/" + $.cookie("us_id"),
		success: function(data) {
			if(data == null || data == "") {
				$(".us_photo").attr("src", "/assets/images/avatars/user.jpg");
			} else {
				$(".us_photo").attr("src", "/user/getPhoto/" + $.cookie("us_id"));
			}
		},
		error: function(err) {
			alert(err);
		}

	});

});

//用户注销
function logout() {
	$.ajax({
		type: "post",
		url: "/logout",
		dataType: "json",
		data: {

		},
		success: function(data) {

		},
		error: function(err) {

		}
	});
};

//根据用户id获取用户信息
function getUserById(us_id) {
	var name;
	var type;
	$.ajax({
		type: "post",
		url: "/user/getUserById",
		dataType: "json",
		async: false,
		data: {
			"us_id": us_id
		},
		success: function(data) {
			name = data.name;
			type = data.type
		},
		error: function(err) {
			alert("获取用户信息失败!");
		}

	});
	return {
		us_name: name,
		us_type: type
	};
}

//挂科预警中，需要传入专业的中文名，产生无法读取json的情况，现在将中文名映射为英文
function getMajorEng(name) {
	switch(name) {
		case "电气工程及其自动化[3+2]":
			return "dianqigongchengjiqizidonghua";
			break;
		case "电气工程及其自动化":
			return "dianqigongchengjiqizidonghua";
			break;
		case "通信工程[嵌入式培养]":
			return "tongxingongcheng";
			break;
		case "通信工程":
			return "tongxingongcheng";
			break;
		case "计算机科学与技术[嵌入式培养]":
			return "jisuanjikexueyujishu";
			break;
		case "计算机科学与技术[专转本]":
			return "jisuanjikexueyujishu";
			break;
		case "建筑电气与智能化":
			return "jianzhudianqiyuzhinenghua";
			break;
		case "电子信息工程":
			return "dianzixinxigongcheng";
			break;
	}
}