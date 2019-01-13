package com.donald.view;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import com.donald.domain.Ground;
import com.donald.domain.Shape;
import com.donald.utils.TetrisUtils;

public class GamePanel extends JPanel {
	
	private Image img;  //在img上作画，然后在panel上显示此img
	private Graphics ig;  //保存img的Griphics
	
	public GamePanel() {
		//因GameFrame.setResizable造成了窗口的大小误差
		this.setSize(TetrisUtils.cell_width*TetrisUtils.cell_width_count-8,
				TetrisUtils.cell_height*TetrisUtils.cell_height_count-9);
		
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(this.getWidth(),this.getHeight());
	}
	
	/**
	 * 重新显示次panel
	 */
	public synchronized void reShow(Ground ground,Shape shape) {
		img = createImage(this.getWidth(), this.getHeight());
		if(img != null) {
			ig = img.getGraphics();
		}
		if(ig != null) {
			//画出ground
			ground.drawMe(ig);
			//画出shape
			shape.drawMe(ig);
			//将img显示在panel上
			this.paint(this.getGraphics());
			
		}
		
	}
	
	@Override
	public void paint(Graphics g) {
		g.drawImage(img, 0, 0, this);
	}
	
	
	
}
