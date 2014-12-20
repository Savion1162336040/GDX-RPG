package com.rpsg.rpg.utils.display;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpsg.rpg.core.Setting;
import com.rpsg.rpg.io.Input;
import com.rpsg.rpg.object.script.BaseScriptExecutor;
import com.rpsg.rpg.object.script.Script;
import com.rpsg.rpg.object.script.ScriptExecutor;
import com.rpsg.rpg.system.base.IOMode;
import com.rpsg.rpg.system.base.Image;
import com.rpsg.rpg.system.control.InputControler;
import com.rpsg.rpg.utils.game.GameUtil;
import com.rpsg.rpg.view.GameViews;


public class Msg {
	public static Image msgbox;
	
	public static void init(){
		msgbox=new Image(Setting.GAME_RES_MESSAGE+"msgbox.png");
		float ss=msgbox.getWidth();
		msgbox.setWidth(GameUtil.screen_width-40);
		ss=ss/msgbox.getWidth();
		msgbox.setHeight((float)msgbox.getHeight()/ss);
		msgbox.setX(GameUtil.screen_width/2-msgbox.getWidth()/2);
		msgbox.setY(25);
	}
	
	static String currentText="";
	static int currentTextPoint=0;
	static Script currentScript;
	static SpriteBatch currentBatch;
	static int TEXT_DISPLAY_SPEED=30;
	private static int DISPLAY_OFFSET=0;
	public static BaseScriptExecutor say(final Script script,final String str,final String title,final int size){
		return script.add(new ScriptExecutor(script) {
			SpriteBatch batch=GameViews.batch;
			public void step() {
				if(Input.isPress(Keys.CONTROL_LEFT))
					TEXT_DISPLAY_SPEED=100;
				else
					TEXT_DISPLAY_SPEED=30;
				DISPLAY_OFFSET-=TEXT_DISPLAY_SPEED;
				if(DISPLAY_OFFSET<=0){
					DISPLAY_OFFSET=100;
					if(currentTextPoint>=currentText.length()){
						if(Input.isPress(Keys.Z) || Input.isPress(Keys.CONTROL_LEFT)){
							dispose();
						}
					}else
						++currentTextPoint;
				}
				GameUtil.resetBacth(currentBatch);
				msgbox.setColor(0.4f,0.67f,0.98f,1);
				msgbox.draw(batch);
				FontUtil.draw(batch, currentText.substring(0,currentTextPoint), size, Color.WHITE, 50, 130, (int) (msgbox.getWidth()-60));
				FontUtil.draw(batch, title, 22, Color.WHITE, getOrPosX(title, 22), 178, (int) (msgbox.getWidth()-60));
			}
			public void init() {
				currentText=str;
				currentTextPoint=0;
				currentBatch=batch;
			}
		});
	}
	
	public static BaseScriptExecutor setKeyLocker(Script script,final boolean flag){
		return script.add(()->{
			InputControler.currentIOMode=flag?IOMode.MAP_INPUT_MESSAGING:IOMode.MAP_INPUT_NORMAL;
		});
	}
	
	private static int getOrPosX(String txt,int size){
		int o=(200/2-FontUtil.getTextWidth(txt, size, Setting.STRING_PADDING_LR)/2);
		return 20+o;
	}
	
	public static BaseScriptExecutor show(Script script,final int msgType){
		return script.add(()->{
//			InputControler.currentIOMode=flag?IOMode.MAP_INPUT_MESSAGING:IOMode.MAP_INPUT_NORMAL;
			//TODO MSG�ı�
		});
	}
	
	
}
