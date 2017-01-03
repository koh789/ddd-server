<!DOCTYPE htm>
<head>
    <title>Registration</title>
<#include "/common/resource.ftl" >
</head>

<body>
<#if error?has_content>
<div>
    <strong>${error}</strong>
</div>
</#if>

<#include "/common/header.ftl" >

<!-- 登録フォーム -->
<!-- <form modelAttribute="adminRegisterForm" method="post" action="/staff/register">
		<fieldset>
    		<legend>アカウント登録</legend>
    		<div class="form-group">
	     		<label for="exampleInput">アカウント名(半角英数字)</label>
	     		<@spring.formInput "adminRegisterForm.adminId" 'class="form-control"' 'id="exampleInput"'/><br>
				<@spring.bind path="adminRegisterForm.adminId"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
			<div class="form-group">
	     		<label for="exampleInput">名前</label>
	     		<@spring.formInput "adminRegisterForm.name" 'class="form-control"' 'id="exampleInput"'/><br>
				<@spring.bind path="adminRegisterForm.name"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
			<div class="form-group">
	     		<label for="Password">パスワード</label>
	     		<@spring.formPasswordInput "adminRegisterForm.password" 'class="form-control"'/><br>
				<@spring.bind path="adminRegisterForm.password"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
			<div class="form-group">
	     		<label for="Password">パスワード再確認</label>
	     		<@spring.formPasswordInput "adminRegisterForm.confirmPassword" 'class="form-control"'/><br>
				<@spring.bind path="adminRegisterForm.confirmPassword"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
   			<div class="form-group">
   				<label>権限選択</label><br />
				<label class="radio-inline">
					<input type="radio" name="roleType" value="1"> ルートシステム管理者
				</label>
				<label class="radio-inline">
					<input type="radio" name="roleType" value="2"> システム管理者
				</label>
				<@spring.bind path="adminRegisterForm.roleType" /><br />
				<font color="red"><@spring.showErrors "" /></font>
			</div>
			<div class="form-group">
	     		<label for="exampleInput">email</label>
	     		<@spring.formInput "adminRegisterForm.eMail" 'class="form-control"' 'id="exampleInput"'/><br>
				<@spring.bind path="adminRegisterForm.eMail"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
			<div class="form-group">
	     		<label for="exampleInput">備考</label>
	     		<@spring.formInput "adminRegisterForm.note" 'class="form-control"' 'id="exampleInput"'/><br>
				<@spring.bind path="adminRegisterForm.note"/>
				<font color="red"><@spring.showErrors "" /></font>
			</div>
    		<button type="submit" class="btn btn-primary">登録</button>
  		</fieldset>
	</form> -->
<!-- end of 登録フォーム -->
</div>
</div>
</body>
</html>
