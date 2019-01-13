package com.donald.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

import com.donald.controller.Controller;
import com.donald.domain.Ground;
import com.donald.domain.Shape;
import com.donald.factory.ShapeFactory;
import com.donald.listener.GameKeyListener;
import com.donald.utils.TetrisUtils;
import com.donald.view.GamePanel;

public class GameFrame extends JFrame {
	
	public GameFrame() {
		
		//生成视图对象
		GamePanel panel = new GamePanel();
		add(panel);
		pack();
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int w_width = TetrisUtils.cell_width*TetrisUtils.cell_width_count;
		int w_height = TetrisUtils.cell_height*TetrisUtils.cell_height_count;
		
		this.setLocation(screenSize.width/2-w_width/2,screenSize.height/2-w_height/2);
		this.setBackground(TetrisUtils.bg_color);
		this.setResizable(false);
		this.setVisible(true);
		
		//创建ground
		Ground ground = new Ground();
		//创建第一个形状
		Shape shape = ShapeFactory.createShape();
		//创建Controller对象
		Controller controller = new Controller(ground,shape,panel);
		//开始游戏
		try {
			/*
			 * 添加键盘监听事件
			 */
			this.addKeyListener(new GameKeyListener(controller));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("呵呵");
		}
		try {
			controller.start();
		} catch (Exception e) {
			System.out.println("game over");
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
