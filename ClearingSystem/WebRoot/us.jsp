<%@ page pageEncoding="utf-8"%>

<!DOCTYPE HTML >
<html>
<head>

<title>My JSP 'us.jsp' starting page</title>
<style type="text/css">
.currency-unit-tab {
	list-style: none;
	margin: 0px;
	font-size: 12px;
	max-width: 80px;
}

.currency-unit-tab li {
	color: #777777;
	padding: 5px 20px 5px 20px;
}

.currency-unit-tab-active {
	border-top: solid 1px #5da9e3;
	border-left: solid 1px #5da9e3;
	border-bottom: solid 1px #5da9e3;
	font-weight: bold;
}

.currency-unit-tab-unactive {
	border-right: solid 1px #5da9e3;
}

.currency-unit-selector {
	width: 500px;
	border: solid 1px #ccc;
}

.currency-unit-content ul {
	margin-top: 230px;
	list-style: none;
	margin: 0px;
	font-size: 12px;
	list-style: none;
}

.currency-unit-content li {
	float: left;
	padding: 5px 10px 5px 10px;
}
</style>

</head>

<body>
	<div class="currency-unit-selector">
		<ul class="currency-unit-tab">
			<li class="currency-unit-tab-unactive">常用</li>
			<li class="currency-unit-tab-unactive">欧洲</li>
			<li class="currency-unit-tab-active">美洲</li>
			<li class="currency-unit-tab-unactive">大洋洲</li>
			<li class="currency-unit-tab-unactive">非洲</li>
			<li class="currency-unit-tab-unactive">亚洲</li>
		</ul>
		<div class="currency-unit-content">
			<ul>
				<li class="DDD">人民币(CNY)</li>
				<li class="DDD">人民币(CNY)</li>
				<li class="DDD">人民币(CNY)</li>
				<li class="DDD">人民币(CNY)</li>
			</ul>
		</div>
	</div>
</body>
</html>
