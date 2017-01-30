<!DOCTYPE html>
<head>
    <title>Login</title>
<#include "../common/resource.ftl" >
</head>

<body>
<#include "../common/header.ftl" >

<div class="p-login_wrapper">
    <div class="p-container">
        <div class="p-title">
            <h2>Management Page</h2>
            <span class="p-byline">Lorem ipsum dolor sit amet, consectetuer adipiscing elit</span>
        </div>
        <!-- ログインフォーム -->
        <div class="p-login_container">
            <form modelAttribute="loginForm" method="POST" action="/auth/login" class="p-login_form">
                <table>
                    <tbody>
                    <#if error?has_content>
                    <div class="c-alert_f11"><strong>${error}</strong></div>
                    </#if>
                    <tr>
                        <td>
                            <p>Username</p>
                        <@spring.formInput 'loginForm.loginId' 'class="p-login_input"' />
                        <#if spring.status.error><span
                                class='c-alert_f11'><@spring.showErrors "class='c-alert_f11'" /></span></#if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <p>Password</p>
                        <@spring.formPasswordInput "loginForm.password" 'class="p-login_input"' />
                        <#if spring.status.error><span class='c-alert_f11'><@spring.showErrors "" /></span></#if>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <input type="submit" class="p-login_btn" value="Login">
                        </td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <!-- end of ログインフォーム -->
    </div>
</div>
</body>
</html>
