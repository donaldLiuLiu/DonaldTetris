package com.donald.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.donald.domain.Ground;
import com.donald.domain.Shape;
import com.donald.utils.TetrisUtils;

public class GamePanel extends JPanel {
	
	private Image img;  //��img��������Ȼ����panel����ʾ��img
	private Graphics ig;  //����img��Griphics
	
	public GamePanel() {
		//��GameFrame.setResizable����˴��ڵĴ�С���
		this.setSize(TetrisUtils.cell_width*TetrisUtils.cell_width_count-8,
				TetrisUtils.cell_height*TetrisUtils.cell_height_count-9);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(),this.getHeight());
	}
	
	/**
	 * ������ʾ��panel
	 */
	public synchronized void reShow(Ground ground,Shape shape) {
		img = createImage(this.getWidth(), this.getHeight());
		if(img != null) {
			ig = img.getGraphics();
		}
		if(ig != null) {
			//����ground
			ground.drawMe(ig);
			//����shape
			shape.drawMe(ig);
			//��img��ʾ��panel��
			this.paint(this.getGraphics());
			
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	
	
}
