package com.donald.domain;

import java.awt.Color;
import java.awt.Graphics;

import com.donald.utils.TetrisUtils;

/**
 * 
 * @author Donald
 *	代表形状
 */
public class Shape {
	
	
	/*
	 * 保存用最小的矩形能够包下姓形状的坐标,这个坐标是相对于窗口的
	 */
	private int startXPos;  
	private int startYPos;
	private int endXPos;
	private int endYPos;
	
	/*
	 * 记住是哪一种类的图形
	 */
	private int type;
	
	/*
	 * 记住是是这一种类图形下的哪一种状态
	 */
	private int which;
	
	/*
	 * 记住形状
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
		//计算起始的得到 pos值...
		startXPos = TetrisUtils.cell_width*TetrisUtils.cell_width_count/2; //1/2个当前窗口的宽度,如果除不尽，就会出现误差
		endYPos = 0;
		//endXPos,endYPos ,从最后扫描，遇到的第一个1就是最右的哪一个
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
	 * 画出此Shape
	*/
	public void drawMe(Graphics ig) {
		for(int i=0;i<4;i++) {
			for(int j=0;j<4;j++) {
				if(body[which][i*4+j] == 1) {
					//画出来
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
	 * 当改变形状之后，需重新计算Pos
	 */
	public boolean calCurrentPos() {
		//start位置不变
		
		//需更改end位置
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
		//如果位置不合法，返回假
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
