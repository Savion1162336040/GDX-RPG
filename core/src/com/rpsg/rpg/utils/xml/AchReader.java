package com.rpsg.rpg.utils.xml;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.rpsg.rpg.object.base.BaseAchievement;

public class AchReader {

	@SuppressWarnings("rawtypes")
	public static List<BaseAchievement> get(String path) {
		List<BaseAchievement>  l= new ArrayList<BaseAchievement>();
		SAXReader reader = new SAXReader();
		try {
			Document document = reader.read(new File(path));

			Element root = document.getRootElement();

			for (Iterator it = root.elementIterator(); it.hasNext();) {

				Element achs = (Element) it.next();

				BaseAchievement ach = new BaseAchievement(
						achs.elementText("number"), achs.elementText("title"),
						achs.elementText("content"), achs.elementText("type"),
						achs.elementText("status"),
						achs.elementText("compare"), achs.elementText("judge"),
						achs.elementText("deal"));
				
				l.add(ach);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return l;

	}


}
