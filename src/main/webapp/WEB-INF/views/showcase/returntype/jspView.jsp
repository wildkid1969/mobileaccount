<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

This is a <%=request.getAttribute("viewType")%> view !

<%if(request.getParameter("attr2") != null){%>
<h3>Redirect相关信息:</h3>

<table border="1" width="80%">
	<tr>
        <td>参数名</td>
        <td>传递方式</td>
        <td>获取方式</td>
        <td>获取参数值</td>
        <td>说明</td>
    </tr>
	<tr>
        <td>attr1</td>
        <td>model.addAttribute("attr1", "v1")</td>
        <td>request.getAttribute("attr1")</td>
        <td><%=request.getAttribute("attr1")%></td>
        <td>上次请求中放到Model中的对象，无法在重定向后的请求中获取</td>
    </tr>
	<tr>
        <td>attr2</td>
        <td>redirectAttributes.addAttribute("attr2", "v2")</td>
        <td>request.getParameter("attr2")</td>
        <td><%=request.getParameter("attr2")%></td>
        <td>作为URL参数传递，通过request.getParameter("attr2")获取</td>
    </tr>
	<tr>
        <td>attr3</td>
        <td>redirectAttributes.addFlashAttribute("attr3", "v3")</td>
        <td>request.getAttribute("attr3")</td>
        <td><%=request.getAttribute("attr3")%></td>
        <td>此值只能被使用一次，通过request.getAttribute("attr3")获取</td>
    </tr>
	
</table>
<%}%>
