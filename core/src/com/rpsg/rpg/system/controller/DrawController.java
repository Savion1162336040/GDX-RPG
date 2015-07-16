package com.rpsg.rpg.system.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.rpsg.rpg.utils.display.FG;
import com.rpsg.rpg.utils.display.Msg;

public class DrawController {

	public static void draw(){
		SpriteBatch batch= (SpriteBatch) FG.stage.getBatch();
		FG.stage.draw();
		batch.begin();
		FG.draw(batch);
		Msg.draw(batch);
		batch.end();
	}
}
