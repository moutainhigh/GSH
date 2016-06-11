(function($) {

	$.util.namespace("bset.clearCycle");

	window.bset.clearCycle.typeOnChange = function(newValue, oldValue) {
		if (newValue == "1")
			$('#day').numberspinner('disable');
		else
			$('#day').numberspinner('enable');
	};

	window.bset.clearCycle.deadlineOnChange = function(newValue, oldValue) {
		if (newValue == "1") {
			$('#startDate').datebox('disable');
			$('#endDate').datebox('disable');
		} else {
			$('#startDate').datebox('enable');
			$('#endDate').datebox('enable');
		}
	};

	window.bset.clearCycle.save = function() {
		$.messager.show({
			title : '结果提示',
			msg : '保存成功！',
			showType : 'slide'
		});

	};

	$(function() {
	});

})(jQuery);