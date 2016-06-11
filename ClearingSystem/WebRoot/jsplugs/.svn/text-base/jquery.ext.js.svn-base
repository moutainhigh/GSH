/**
 * 
 * @requires jQuery
 * 
 * 将form表单元素的值序列化成对象
 * 
 * @returns object
 */
$.serializeObject = function(form) {
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
};

/**
 * 货币单位英文缩写 转为 货币符号
 * 
 * @param unit
 *            string CNY
 * 
 * @return ¥、$
 * 
 */
$.convertCurrencyUnit = function(unit) {
	var cu = {
		"CNY" : "¥",
		"USD" : "$",
		"EUR" : "€",
		"GBP" : "￡",
		"ADU" : "$",
		"BRL" : "R$",
		"CAD" : "CAD$",
		"CHF" : "SFR.",
		"DKK" : "kr",
		"HKD" : "HK＄",
		"IDR" : "Rs.",
		"JPY" : "￥",
		"KRW" : "₩",
		"MYR" : "M.＄",
		"NOK" : "kr",
		"NZD" : "＄NZ.",
		"PHP" : "₱",
		"MOP" : "P.",
		"SGD" : "S.＄",
		"SUR" : "Br",
		"THB" : "฿",
		"TWD" : "NT＄"
	};
	return cu[unit];
};

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式
 * 
 * @param num
 *            数值(Number或者String)
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
$.formatCurrency = function(num) {
	num = num.toString().replace(/\$|\,/g, '');
	if (isNaN(num))
		num = "0";
	sign = (num == (num = Math.abs(num)));
	num = Math.floor(num * 100 + 0.50000000001);
	cents = num % 100;
	num = Math.floor(num / 100).toString();
	if (cents < 10)
		cents = "0" + cents;
	for ( var i = 0; i < Math.floor((num.length - (1 + i)) / 3); i++)
		num = num.substring(0, num.length - (4 * i + 3)) + ',' + num.substring(num.length - (4 * i + 3));
	return (((sign) ? '' : '-') + num + '.' + cents);
};

/**
 * 将数值四舍五入(保留2位小数)后格式化成金额形式 (带货币符号)
 * 
 * @param num
 *            数值(Number或者String)
 * @param unit
 *            货币单位
 * @return 金额格式的字符串,如'1,234,567.45'
 * @type String
 */
$.formatCurrencySign = function(num, unit) {
	return $.convertCurrencyUnit(unit) + $.formatCurrency(num);
};

/**
 * 计算日期相差多少天
 * 
 * @t argsname datatype format
 * @f---------------------------------
 * @1 dateBegin string 1989-01-02
 * @2 dateEnd string 1989-01-11
 */
$.distanceDay = function(dateBegin, dateEnd) {
	if (!dateBegin || !dateEnd)
		return 0;
	var sDate = new Date(dateBegin);
	var eDate = new Date(dateEnd);
	var fen = ((eDate.getTime() - sDate.getTime()) / 1000) / 60;
	var distance = parseInt(fen / (24 * 60)); // 相隔distance天
	return distance;
};

/**
 * 与系统时间大小比较
 * 
 * @param date
 *            要比较的日期
 * @paramtype string
 * @return 1 大于系统日期
 * @return 0 相等
 * @return -1 小于系统日期
 * 
 * @eg $.compareSystemDate('2014-09-08');
 */
$.compareSystemDate = function(date) {
	var sDate = new Date();
	var eDate = new Date(date);
	var c = sDate.getTime() - eDate.getTime();
	if (c > 0) {
		return 1;
	} else if (c < 0) {
		return -1;
	} else {
		return 0;
	}
};

/**
 * 获取系统时间
 * 
 * @return 系统时间 string 格式 'YYYY-MM-DD'
 * 
 * @eg $.getSystemDateStr();
 */
$.getSystemDateStr = function() {
	var sDate = new Date();
	return sDate.getFullYear() + "-" + (sDate.getMonth() + 1) + "-" + sDate.getDate();
};