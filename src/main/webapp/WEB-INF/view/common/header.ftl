<!-- ログイン前header -->
<#if springMacroRequestContext.getRequestUri()?contains("/auth/login")>
<div class="p-login_header">
    <div class="p-header_logo">
        <h3>StoneWork</h3>
    </div>
    <div class="p-header_menu">
        <ul>
            <li><a href="#" accesskey="3" title=""><span>About Us</span></a></li>
            <li><a href="#" accesskey="4" title=""><span>Careers</span></a></li>
            <li><a href="#" accesskey="5" title=""><span>Contact Us</span></a></li>
        </ul>
    </div>
</div>
<!-- ログイン後ヘッダー -->
<#elseif springMacroRequestContext.getRequestUri()?contains("/auth/register")>
<div class="p-login_header">
    <div class="p-header_logo">
        <h3>StoneWork</h3>
    </div>
    <div class="p-header_menu">
        <ul>
            <li><a href="/auth/login" accesskey="5" title=""><span>Login</span></a></li>
        </ul>
    </div>
</div>

<#else>
<div class="p-login_header">
    <div class="p-header_logo">
        <h3>StoneWork</h3>
    </div>
    <div class="p-header_menu">
        <ul>
            <li><a href="/rooms/index?userId=${(userId)!}" accesskey="5" title=""><span>message</span></a></li>
            <li><a href="/auth/logout" accesskey="5" title=""><span>Logout</span></a></li>
        </ul>
    </div>
</div>
</#if>
