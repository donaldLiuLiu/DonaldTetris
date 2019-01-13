package com.donald.domain;

import java.awt.Color;
import java.awt.Graphics;

import com.donald.utils.TetrisUtils;

/**
 * 
 * @author Donald
 *	������״
 */
public class Shape {
	
	
	/*
	 * ��������С�ľ����ܹ���������״������,�������������ڴ��ڵ�
	 */
	private int startXPos;  
	private int startYPos;
	private int endXPos;
	private int endYPos;
	
	/*
	 * ��ס����һ�����ͼ��
	 */
	private int type;
	
	/*
	 * ��ס������һ����ͼ���µ���һ��״̬
	 */
	private int which;
	
	/*
	 * ��ס��״
	 */
	private int body[][];
	
	public int getStartXPos() {
		return startXPos;
	}


	public int[][] getBody() {
		return body;
	}


	public void setBody(int[][] body) {
		this.body = body;
	}


	public void setStartXPos(int startXPos) {
		this.startXPos = startXPos;
	}


	public int getStartYPos() {
		return startYPos;
	}


	public void setStartYPos(int startYPos) {
		this.startYPos = startYPos;
	}


	public int getEndXPos() {
		return endXPos;
	}


	public void setEndXPos(int endXPos) {
		this.endXPos = endXPos;
	}


	public int getEndYPos() {
		return endYPos;
	}


	public int getType() {
		return type;
	}


	public void setType(int type) {
		this.type = type;
	}


	public void setEndYPos(int endYPos) {
		this.endYPos = endYPos;
	}
	
	public int getWhich() {
		return which;
	}

	public void setWhich(int which) {
		this.which = which;
	}
	
	public int getLength() {
		return body.length;
	}

	public Shape(int[][] body, int which) {
		this.body = body;
		this.which = which;
		//������ʼ�ĵõ� posֵ...
		startXPos = TetrisUtils.cell_width*TetrisUtils.cell_width_count/2; //1/2����ǰ���ڵĿ��,������������ͻ�������
		endYPos = 0;
		//endXPos,endYPos ,�����ɨ�裬�����ĵ�һ��1�������ҵ���һ��
		int xe = 0;
		int ye = 0;
		for(int k=body[which].length-1;k>=0;k--) {
			if(body[which][k] == 1) {
				int x = k%4; 
				int y = k/4;  
				if(xe < x) {
					xe = x;
				}
				if(ye < y) {
					ye = y;
				}
			}
		}
		endXPos = startXPos+TetrisUtils.cell_width*(xe+1);
		startYPos = -endYPos-TetrisUtils.cell_height*(ye+1);
//System.out.println("endXPos="+endXPos/TetrisUtils.cell_width);
//System.out.println("endYPos="+endYPos/TetrisUtils.cell_height);

	}

	
	/*
	 * ������Shape
	*/
	public void drawMe(Graphics ig) {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(body[which][i*4+j] == 1) {
					//������
					int x = startXPos+j*TetrisUtils.cell_width;
					int y = startYPos+i*TetrisUtils.cell_height;
					ig.setColor(TetrisUtils.shape_color);
					ig.fillRect(x, y, TetrisUtils.cell_width, TetrisUtils.cell_height);
					ig.setColor(TetrisUtils.border_color);
					ig.drawRect(x, y, TetrisUtils.cell_width, TetrisUtils.cell_height);
				}
			}
		}
	}
	
	/*
	 * ���ı���״֮�������¼���Pos
	 */
	public boolean calCurrentPos() {
		//startλ�ò���
		
		//�����endλ��
		int newX;
		int newY;
		int xe = 0;
		int ye = 0;
		for(int k=body[which].length-1;k>=0;k--) {
			if(body[which][k] == 1) {
				int x = k%4; 
				int y = k/4;  
				if(xe < x) {
					xe = x;
				}
				if(ye < y) {
					ye = y;
				}
			}
		}
		newX = startXPos+TetrisUtils.cell_width*(xe+1);
		newY = startYPos+TetrisUtils.cell_height*(ye+1);
		//���λ�ò��Ϸ������ؼ�
		if(newX < 0 || newX > TetrisUtils.cell_width*TetrisUtils.cell_width_count
				|| newY>TetrisUtils.cell_height*TetrisUtils.cell_height_count) {
			return false;
		}else {
			endXPos = newX;
			endYPos = newY;
			return true;
		}
			
	}
	
	
}
