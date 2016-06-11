/**
 * JQUERY 格式化输入插件
 * 
 */
(function($) {
	/**
	 * * 分隔格式化
	 * 
	 * @eg1.默认使用方法： $("#account").formatInput();
	 * @eg2.设置参数 $("#account").formatInput({cutbit:4,deimiter:' '});
	 */
	$.fn.formatInput = function(options) {
		var defaults = {
			cutbit : 4,// 隔断位数
			deimiter : ' ', // 账号分隔符
			onlyNumber : true, // 只能输入数字
			copy : true
		// 允许复制
		};
		var opts = $.extend({}, defaults, options);
		var obj = $(this);
		var reg = new RegExp('(\\d{' + opts.cutbit + '})(?=\\d)', 'g');
		if (obj.val() != '')
			obj.val(obj.val().replace(/\s/g, '').replace(reg, "$1" + opts.deimiter));
		obj.bind('keyup', function(event) {
			if (opts.onlyNumber) {
				if (!(event.keyCode >= 48 && event.keyCode <= 57)) {
					this.value = this.value.replace(/\D/g, '');
				}
			}
			this.value = this.value.replace(/\s/g, '').replace(reg, "$1" + opts.deimiter);
		}).bind('dragenter', function() {
			return false;
		}).bind('onpaste', function() {
			return !clipboardData.getData('text').match(/\D/);
		}).bind('blur', function() {
			this.value = this.value.replace(/\s/g, '').replace(reg, "$1" + opts.deimiter);
		});
	};

	/**
	 * 大写输入框
	 */
	$.fn.upperbox = function(opts) {
		var obj = $(this);
		if (obj.val() != '')
			obj.val(obj.val().toLocaleUpperCase());
		obj.bind('keyup', function(event) {
			this.value = this.value.toLocaleUpperCase();
		}).bind('dragenter', function() {
			return false;
		}).bind('blur', function() {
			this.value = this.value.toLocaleUpperCase();
		});
	};

	/**
	 * 小写输入框
	 */
	$.fn.lowerbox = function(opts) {
		var obj = $(this);
		if (obj.val() != '')
			obj.val(obj.val().toLocaleLowerCase());
		obj.bind('keyup', function(event) {
			this.value = this.value.toLocaleLowerCase();
		}).bind('dragenter', function() {
			return false;
		}).bind('blur', function() {
			this.value = this.value.toLocaleLowerCase();
		});
	};
})(jQuery);
