package com.donald.domain;

import java.awt.Color;
import java.awt.Graphics;

import com.donald.utils.TetrisUtils;

/**
 * 
 * @author Donald
 *	代表地面  :创建一个大的二维数组,保存每一个格子
 */
public class Ground {
	
	private int body[][];
	
	public Ground() {
		//创建body
		body = new int[TetrisUtils.cell_width+1][TetrisUtils.cell_height];
		//每个格子填1代表有东西,填0代表没东西
		//初始化为0
		init();
	}

	public int[][] getBody() {
		return body;
	}

	public void setBody(int[][] body) {
		this.body = body;
	}

	private void init() {
		for(int i=0;i<body.length;i++) {
			for(int j=0;j<body[i].length;j++) {
				if(i == body.length-1) {
					body[i][j] = 1;
				}else {
					body[i][j] = 0;
				}
			}
		}
		/*for(int i=0;i<body.length;i++) {
			for(int j=0;j<body[i].length;j++) {
				System.out.print(body[i][j]+"\t");
			}
			System.out.println();
		}*/
	}

	/*
	 * 画出此Ground
	 */
	public void drawMe(Graphics ig) {
		for(int i=0;i<body.length;i++) {
			for(int j=0;j<body[i].length;j++) {
				if(body[i][j] == 1) {
					//画出这个地砖
					ig.setColor(TetrisUtils.ground_color);
					ig.fillRect(j*TetrisUtils.cell_width, i*TetrisUtils.cell_height,
							TetrisUtils.cell_width, TetrisUtils.cell_height);
					ig.setColor(TetrisUtils.border_color);
					ig.drawRect(j*TetrisUtils.cell_width, i*TetrisUtils.cell_height,
							TetrisUtils.cell_width, TetrisUtils.cell_height);
				}
			}
		}
		
	}

	/*
	 * shape变成地板
	 */
	public void shape2ground(Shape shape) {
		for(int k=0;k<shape.getBody()[shape.getWhich()].length;k++) {
			if(shape.getBody()[shape.getWhich()][k] == 0) {
				continue;
			}
//System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
			int sx = k%4 + shape.getStartXPos()/TetrisUtils.cell_width;
			int sy = k/4 + shape.getStartYPos()/TetrisUtils.cell_height;
			body[sy][sx] = 1;
		}
//System.out.println("***********************");
	}

	/*
	 * 处理消除
	 */
	public void handlerGroundLose() {
		//int[][] new_body = new int[TetrisUtils.cell_width+1][TetrisUtils.cell_height+1];
		boolean flag = true;
		for(int i=body.length-2;i>=0;i--) {
			flag = true;
			for(int j=0;j<body[i].length;j++) {
//System.out.println("body:"+i+j+body[i][j]);
				if(body[i][j] == 0) {
					flag = false;
					break;
				}
			}
			if(flag) {
//System.out.println(i);
				//i
				for(int m=i-1;m>=0;m--) {
					for(int n=0;n<body[m].length;n++) {
						body[m+1][n] = body[m][n];
					}
				}
				//0
				for(int l=0;l<body[0].length;l++) {
					body[0][l] = 0;
				}
				
			}
		}
		
	}
	
	
	
	
	
}
 