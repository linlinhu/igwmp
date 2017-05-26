package com.emin.wxs.pay;
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.TypeInfo;

public class XML {
	public static void main(String[] args) throws Exception {
		// step 1: 获得dom解析器工厂（工作的作用是用于创建具体的解析器）
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		// System.out.println("class name: " + dbf.getClass().getName());
		// step 2:获得具体的dom解析器
		DocumentBuilder db = dbf.newDocumentBuilder();
		// System.out.println("class name: " + db.getClass().getName());
		// step3: 解析一个xml文档，获得Document对象（根结点）
		Document document = db.parse(new File("a.xml"));

		Element e = document.getDocumentElement();
		System.out.println(e.getTagName());

		NodeList children = e.getChildNodes();

		for (int i = 0; i < children.getLength(); i++) {
			Node node = children.item(i);

			if (node.getNodeType() == TypeInfo.DERIVATION_RESTRICTION) {
				System.out.println("name : " + node.getNodeName());
			} else {

			}
		}
	}
}