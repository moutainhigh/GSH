/**
 * JQUERY 银行帐号输入插件 使用方法： $("#numberInput").numberInput();
 */
(function($) {
	// 输入框格式化
	$.fn.numberInput = function(length) {
		$(this).keypress(function(event) {
			var keyCode = event.which;
			if (keyCode == 8) {
				return true;
			}
			if ($(this).val().length < length) {
				if (keyCode >= 48 && keyCode <= 57)
					return true;
				else
					return false;
			}
			return false;

		}).focus(function() {
			this.style.imeMode = 'disabled';
		});
	};

})(jQuery);
