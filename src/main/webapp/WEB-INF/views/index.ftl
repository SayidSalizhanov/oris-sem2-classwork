<#assign sf=JspTaglibs["http://www.springframework.org/security/tags"]>

<@sf.authorize access="!isAuthenticated()">
    Login
</@sf.authorize>

<@sf.authorize access="isAuthenticated()">
    Logout
</@sf.authorize>

<@sf.authorize access="hasRole('ADMIN')">
    Manage users
</@sf.authorize>