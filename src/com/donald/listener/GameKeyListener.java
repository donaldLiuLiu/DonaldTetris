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
	 * ���̰����¼�
	 */
	
	@Override
	public void keyPressed(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			//System.out.println("�󱻰���");
			//������ƶ�һ��,����Ѿ���0�ˣ��Ͳ������ƶ���
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
			//System.out.println("�ұ�����");
			//�����ƶ�һ��������Ѿ������ˣ��Ͳ������ƶ���
			if(this.controller.getShape().getEndXPos() < TetrisUtils.cell_width*(TetrisUtils.cell_width_count)) {
				//������û�������ϰ�������ƶ�
				if(!this.controller.shapeRightHasGround()) {
					this.controller.getShape().setStartXPos(this.controller.getShape().getStartXPos()
							+TetrisUtils.cell_width);
					this.controller.getShape().setEndXPos(this.controller.getShape().getEndXPos()
							+TetrisUtils.cell_width);
				}
			}
			break;
		case KeyEvent.VK_UP:
			//System.out.println("�ϱ�����");
			//�ı���״,��һ���ܴ�����⣬���ǰ���ǽ�ı���״ʱ��������״�ᳬ��ǽ
			int oldW = this.controller.getShape().getWhich();
			int w = (this.controller.getShape().getWhich()+1)%this.controller.getShape().getLength();
			this.controller.getShape().setWhich(w);
			//��״�ı�֮����Ҫ���¼���pos����������Ҫ�鿴����λ���Ƿ�Ϸ���������Ϸ����ͳ����˴δ���״�ı�
			if(!this.controller.getShape().calCurrentPos()) {
				//λ�ò��Ϸ�,�����˴���״�ı�
				this.controller.getShape().setWhich(oldW);
			}
			break;
		case KeyEvent.VK_DOWN:
			
			break;
		}
		//������ʾ
		this.controller.getPanel().reShow(this.controller.getGround(), this.controller.getShape());
		
	}
	
}
