! function(a) {
	"use strict";
	"function" == typeof define && define.amd ? define(["jquery", "../grid.base"], a) : a(jQuery)
}(function(a) {
	a.jgrid = a.jgrid || {}, a.jgrid.hasOwnProperty("regional") || (a.jgrid.regional = []), a.jgrid.regional.en = {
		defaults: {
			recordtext: "正在查看 {0} - {1} 共 {2} 条",
			emptyrecords: "没有记录！",
			loadtext: "加载中...",
			savetext: "保存中...",
			pgtext: "当前页数: {0} 总页数: {1}",
			pgfirst: "First Page",
			pglast: "Last Page",
			pgnext: "Next Page",
			pgprev: "Previous Page",
			pgrecs: "Records per Page",
			showhide: "Toggle Expand Collapse Grid",
			pagerCaption: "Grid::Page Settings",
			pageText: "Page:",
			recordPage: "Records per Page",
			nomorerecs: "No more records...",
			scrollPullup: "Pull up to load more...",
			scrollPulldown: "Pull down to refresh...",
			scrollRefresh: "Release to refresh..."
		},
		search: {
			caption: "搜索",
			Find: "查找",
			Reset: "重置",
			odata: [{
				oper: "eq",
				text: "&nbsp;等于&nbsp; "
			}, {
				oper: "cn",
				text: "&nbsp;相似&nbsp;"
			}],
			groupOps: [{
				op: "AND",
				text: "满足所有条件"
			}, {
				op: "OR",
				text: "满足任意一条"
			}],
			operandTitle: "Click to select search operation.",
			resetTitle: "Reset Search Value"
		},
		edit: {
			addCaption: "新增记录",
			editCaption: "Edit Record",
			bSubmit: "提交",
			bCancel: "取消",
			bClose: "Close",
			saveData: "Data has been changed! Save changes?",
			bYes: "Yes",
			bNo: "No",
			bExit: "Cancel",
			msg: {
				required: "Field is required",
				number: "Please, enter valid number",
				minValue: "value must be greater than or equal to ",
				maxValue: "value must be less than or equal to",
				email: "is not a valid e-mail",
				integer: "Please, enter valid integer value",
				date: "Please, enter valid date value",
				url: "is not a valid URL. Prefix required ('http://' or 'https://')",
				nodefined: " is not defined!",
				novalue: " return value is required!",
				customarray: "Custom function should return array!",
				customfcheck: "Custom function should be present in case of custom checking!"
			}
		},
		view: {
			caption: "View Record",
			bClose: "Close"
		},
		del: {
			caption: "删除",
			msg: "确定删除选定的记录？",
			bSubmit: "确定删除",
			bCancel: "取消"
		},
		nav: {
			edittext: "",
			edittitle: "编辑",
			addtext: "",
			addtitle: "新增",
			deltext: "",
			deltitle: "删除",
			searchtext: "",
			searchtitle: "查找",
			refreshtext: "",
			refreshtitle: "刷新",
			alertcap: "提示",
			alerttext: "请选择记录",
			viewtext: "",
			viewtitle: "查看",
			savetext: "",
			savetitle: "Save row",
			canceltext: "",
			canceltitle: "Cancel row editing",
			selectcaption: "Actions..."
		},
		col: {
			caption: "Select columns",
			bSubmit: "Ok",
			bCancel: "Cancel"
		},
		errors: {
			errcap: "Error",
			nourl: "No url is set",
			norecords: "No records to process",
			model: "Length of colNames <> colModel!"
		},
		formatter: {
			integer: {
				thousandsSeparator: ",",
				defaultValue: "0"
			},
			number: {
				decimalSeparator: ".",
				thousandsSeparator: ",",
				decimalPlaces: 2,
				defaultValue: "0.00"
			},
			currency: {
				decimalSeparator: ".",
				thousandsSeparator: ",",
				decimalPlaces: 2,
				prefix: "",
				suffix: "",
				defaultValue: "0.00"
			},
			date: {
				dayNames: ["Sun", "Mon", "Tue", "Wed", "Thr", "Fri", "Sat", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"],
				monthNames: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec", "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"],
				AmPm: ["am", "pm", "AM", "PM"],
				S: function(a) {
					return 11 > a || a > 13 ? ["st", "nd", "rd", "th"][Math.min((a - 1) % 10, 3)] : "th"
				},
				srcformat: "Y-m-d",
				newformat: "n/j/Y",
				parseRe: /[#%\\\/:_;.,\t\s-]/,
				masks: {
					ISO8601Long: "Y-m-d H:i:s",
					ISO8601Short: "Y-m-d",
					ShortDate: "n/j/Y",
					LongDate: "l, F d, Y",
					FullDateTime: "l, F d, Y g:i:s A",
					MonthDay: "F d",
					ShortTime: "g:i A",
					LongTime: "g:i:s A",
					SortableDateTime: "Y-m-d\\TH:i:s",
					UniversalSortableDateTime: "Y-m-d H:i:sO",
					YearMonth: "F, Y"
				},
				reformatAfterEdit: !1,
				userLocalTime: !1
			},
			baseLinkUrl: "",
			showAction: "",
			target: "",
			checkbox: {
				disabled: !0
			},
			idName: "id"
		}
	}
});