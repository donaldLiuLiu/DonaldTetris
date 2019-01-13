package com.donald.listener;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.donald.controller.Controller;
import com.donald.utils.TetrisUtils;

public class GameKeyListener extends KeyAdapter {

	private Controller controller;

	public GameKeyListener(Controller controller) {
		this.controller = controller;
	}

	/**
	 * 键盘按下事件
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			//System.out.println("左被按下");
			//向左边移动一下,如果已经是0了，就不能再移动了
			if(this.controller.getShape().getStartXPos() != 0) {
				if(!this.controller.shapeLeftHasGround()) {
				this.controller.getShape().setStartXPos(this.controller.getShape().getStartXPos()
						-TetrisUtils.cell_width);
				this.controller.getShape().setEndXPos(this.controller.getShape().getEndXPos()
						-TetrisUtils.cell_width);
				}
			}
			break;
		case KeyEvent.VK_RIGHT:
			//System.out.println("右被按下");
			//向右移动一步，如果已经最右了，就不能在移动了
			if(this.controller.getShape().getEndXPos() < TetrisUtils.cell_width*(TetrisUtils.cell_width_count)) {
				//如果左边没有遇到障碍物，才能移动
				if(!this.controller.shapeRightHasGround()) {
					this.controller.getShape().setStartXPos(this.controller.getShape().getStartXPos()
							+TetrisUtils.cell_width);
					this.controller.getShape().setEndXPos(this.controller.getShape().getEndXPos()
							+TetrisUtils.cell_width);
				}
			}
			break;
		case KeyEvent.VK_UP:
			//System.out.println("上被按下");
			//改变形状,有一个很大的问题，就是挨着墙改变形状时，可能形状会超过墙
			int oldW = this.controller.getShape().getWhich();
			int w = (this.controller.getShape().getWhich()+1)%this.controller.getShape().getLength();
			this.controller.getShape().setWhich(w);
			//形状改变之后，需要重新计算pos，在这里需要查看，新位置是否合法，如果不合法，就撤销此次此形状改变
			if(!this.controller.getShape().calCurrentPos()) {
				//位置不合法,撤销此次形状改变
				this.controller.getShape().setWhich(oldW);
			}
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		}
		//重新显示
		this.controller.getPanel().reShow(this.controller.getGround(), this.controller.getShape());
		
	}
	
}
