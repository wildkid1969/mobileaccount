<%@ page language="java" import="java.util.*" pageEncoding="GBK"%>

This is a <%=request.getAttribute("viewType")%> view !

<%if(request.getParameter("attr2") != null){%>
<h3>Redirect�����Ϣ:</h3>

<table border="1" width="80%">
	<tr>
        <td>������</td>
        <td>���ݷ�ʽ</td>
        <td>��ȡ��ʽ</td>
        <td>��ȡ����ֵ</td>
        <td>˵��</td>
    </tr>
	<tr>
        <td>attr1</td>
        <td>model.addAttribute("attr1", "v1")</td>
        <td>request.getAttribute("attr1")</td>
        <td><%=request.getAttribute("attr1")%></td>
        <td>�ϴ������зŵ�Model�еĶ����޷����ض����������л�ȡ</td>
    </tr>
	<tr>
        <td>attr2</td>
        <td>redirectAttributes.addAttribute("attr2", "v2")</td>
        <td>request.getParameter("attr2")</td>
        <td><%=request.getParameter("attr2")%></td>
        <td>��ΪURL�������ݣ�ͨ��request.getParameter("attr2")��ȡ</td>
    </tr>
	<tr>
        <td>attr3</td>
        <td>redirectAttributes.addFlashAttribute("attr3", "v3")</td>
        <td>request.getAttribute("attr3")</td>
        <td><%=request.getAttribute("attr3")%></td>
        <td>��ֵֻ�ܱ�ʹ��һ�Σ�ͨ��request.getAttribute("attr3")��ȡ</td>
    </tr>
	
</table>
<%}%>
