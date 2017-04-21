package com.hc360.mobileaccount.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.DOMReader;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

public class XmlUtils {
	public static void main(String[] args) {
		try {
			XmlUtils.objectToXml();
		} catch (Exception e) {
			e.printStackTrace();
		}
	} 
   
 // 获取并解析xml格式文件为object  
 public void xmlToObject() throws ParserConfigurationException, DocumentException, SAXException, IOException{  
       SAXReader saxReader = new SAXReader();  
            //将获取的xml 转化为document 文档  
    //这里使用了File当然也可以使用URL()从指定url获取xml并进行解析   
        Document document = saxReader.read(new File("F:/student2.xml"));  
        // 获取根元素  
        Element root = document.getRootElement();  
        // 根元素名称  
        System.out.println("Root----: " + root.getName());  
          
        // 获取所有子元素  
        @SuppressWarnings("unchecked")
		List<Element> childList = root.elements();  
        System.out.println("total child count: " + childList.size());  

        // 获取特定名称的子元素  
        List<Element> childList2 = root.elements("hello");  
        Element  helloElement= root.element("hello");  
        System.out.println("hello child: " + childList2.size());  
        // 获取特定名称的子元素的值  
        System.out.println("hello----"+childList2.get(0).getText());  
        System.out.println("hello----"+helloElement.getText().trim());  
        System.out.println("迭代输出-----------------------");  
        // 迭代输出  
        for (Iterator iter = root.elementIterator(); iter.hasNext();)  
        {   // 获取根目录下的元素  
            Element e = (Element) iter.next();  
            // 获取元素对应的值  
            System.out.println(e.getText());  
            // 元素的属性个数  
            System.out.println(e.attributeCount());  
            //获取元素的第一个属性的名称  
            System.out.println(e.attribute(0).getName());  
            //获取元素的第一个属性的值  
            System.out.println(e.attribute(0).getValue());  
            //获取元素的第二个属性的名称  
            System.out.println(e.attribute(1).getName());  
          //获取元素的第二个属性的值  
            System.out.println(e.attribute(1).getValue());  
            //按照属性名称获取属性值  
            System.out.println("are you ok :"+e.attributeValue("age"));  
            System.out.println("are you ok :"+e.attributeValue("name"));  
        }  
            // 使用DOMReader将xml转化为object  
        System.out.println("用DOMReader-----------------------");  
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();  
        DocumentBuilder db = dbf.newDocumentBuilder();  
        // 注意要用完整类名  
        //将获取的xml 转化为document 文档  
        org.w3c.dom.Document document2 = db.parse(new File("F:/student2.xml"));  

        DOMReader domReader = new DOMReader();  

        // 将JAXP的Document转换为dom4j的Document  
        Document document3 = domReader.read(document2);  

        Element rootElement = document3.getRootElement();  

        System.out.println("Root: " + rootElement.getName());  
    }  
   
  // 将对象组装成xml格式文件并保存  
 public static void objectToXml ()throws Exception{  

        // 第一种方式：创建文档，并创建根元素  
        // 创建文档:使用了一个Helper类  
        Document document = DocumentHelper.createDocument();  
        // 创建根节点并添加进文档  
        Element root = DocumentHelper.createElement("student");  
        document.setRootElement(root);  

        // 第二种方式:创建文档并设置文档的根元素节点  
        Element root2 = DocumentHelper.createElement("student");  
        Document document2 = DocumentHelper.createDocument(root2);  
        // 给元素添加属性  
        root2.addAttribute("name", "zhangsan");  
        // 添加子节点:add之后就返回这个元素  
        Element helloElement = root2.addElement("hello");  
        Element worldElement = root2.addElement("world");  
        //给元素添加属性key-value  
        worldElement.addAttribute("age","12");  
        worldElement.addAttribute("name","www");  
        helloElement.addAttribute("age","13");  
        helloElement.addAttribute("name","wws");  
        //元素的节点的值  
        helloElement.setText("hello Text");  
        worldElement.setText("world text");  
        // 输出  
        // 输出到控制台  
        XMLWriter xmlWriter = new XMLWriter();  
        xmlWriter.write(document2); 

        //输出到文件  
        //其中的"  "表示格式，true参数表示另起一行，gb2312表示编码,如果不写这个参数则默认utf-8编码  
        //1、OutputFormat format=new OutputFormat("  ",true,"gb2312");   
        //生成压缩格式、紧凑格式的xml  其中的compactFormat 翻译：压缩格式      
        //2、 OutputFormat format = OutputFormat.createCompactFormat();  
        //调用静态方法创建一个没有格式的打印方式  
        //3、 OutputFormat format = OutputFormat.createPrettyPrint();      
        //format.setEncoding("gb2312");  // 设置编码  
        OutputFormat format = new OutputFormat("  ", true);// 设置缩进为2个空格，并且另起一行为true  
        XMLWriter xmlWriter2 = new XMLWriter(  
                new FileOutputStream("F:/student1.xml"), format);  
        xmlWriter2.write(document2);  
        xmlWriter2.flush();  
        xmlWriter2.close();  

        // 另一种输出方式，记得要调用flush()方法,否则输出的文件中显示空白，调用close() 方法释放资源  
        XMLWriter xmlWriter3 = new XMLWriter(new FileWriter("F:/student2.xml"),  
                format);  
        xmlWriter3.write(document2);  
        xmlWriter3.flush();  
        xmlWriter3.close();  
 }  
}
