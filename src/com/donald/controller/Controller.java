package com.donald.controller;

import com.donald.domain.Ground;
import com.donald.domain.Shape;
import com.donald.exception.GameOverException;
import com.donald.factory.ShapeFactory;
import com.donald.utils.TetrisUtils;
import com.donald.view.GamePanel;

public class Controller {
	
	private Ground ground;  //地面
	private Shape shape;    //还没有变成地面的形状
	private GamePanel panel;
	
	public Controller(Ground ground,Shape shape, GamePanel panel) {
		this.ground = ground;
		this.shape = shape;
		this.panel = panel;
		
	}
	
	public Ground getGround() {
		return ground;
	}



	public void setGround(Ground ground) {
		this.ground = ground;
	}



	public Shape getShape() {
		return shape;
	}



	public void setShape(Shape shape) {
		this.shape = shape;
	}



	public GamePanel getPanel() {
		return panel;
	}



	public void setPanel(GamePanel panel) {
		this.panel = panel;
	}



	/*
	 * 开始游戏
	 */
	public void start() throws Exception {
		//驱动shape向下掉一次
		
		for(;;) {
			
			Thread.sleep(1000/TetrisUtils.shape_speed);
			handlerShapeMeetGround();
			
			driveMoveDown();
			//System.out.println("移动一步了");
			/*每移动一步，进行一次撞地板测试
			 * 	
			*/
			panel.reShow(ground, shape);
//System.out.println("startYPos:"+shape.getStartYPos());
			handlerShapeMeetGround();
//System.out.println("startYPos:"+shape.getStartYPos());
		}
		
	}

	private void handlerShapeMeetGround() {
		/*如果形状遇到地板,将形状变成地板
		 * 		判断是否可以消除若干行
		 * 		如果可以，消除这些行，重新显示地板
		 * 	重新生成形状，显示形状，继续
		 */
		for(int k=0;k<shape.getBody()[shape.getWhich()].length;k++) {
			if(shape.getBody()[shape.getWhich()][k] == 0) {
				continue;
			}
//System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&");
			boolean flag = false;
			int sx = k%4 + shape.getStartXPos()/TetrisUtils.cell_width;
			int sy = k/4 + shape.getStartYPos()/TetrisUtils.cell_height;
			
			for(int i=0;i<ground.getBody().length;i++) {
				for(int j=0;j<ground.getBody()[i].length;j++) {
					if(ground.getBody()[i][j] == 1 && (sy+1) == i && sx == j) {
						System.out.println("****碰到了****");
						//如果碰到了，并且当前的形状的左上角垂直为0，代表游戏结束
						//小bug,生成shape时，指定的startYPos为0...
//System.out.println("startYPos"+shape.getStartYPos());
						if(shape.getStartYPos()/TetrisUtils.cell_height <= 0) {
							//game over
							throw new GameOverException();
						}
						//碰到了
						flag = true;
						break;
					}
				}
				if(flag) {
					break;
				}
			}
			if(flag) {
				//将形状变成地板
				ground.shape2ground(shape);
				//处理地板消除
				ground.handlerGroundLose();
				//重新生成形状
				shape = ShapeFactory.createShape();
				this.setShape(shape);
//System.out.println("startYPos:"+shape.getStartYPos());
				this.panel.reShow(ground, shape);
/*try {
	Thread.sleep(1000);
} catch (InterruptedException e) {
	e.printStackTrace();
}*/
				break;
			}
			
		}
		//如果没有遇到地板，什么也不做
//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	private void driveMoveDown() {
		shape.setStartYPos(shape.getStartYPos()+TetrisUtils.cell_height);
		shape.setEndYPos(shape.getEndYPos()+TetrisUtils.cell_height);
		//重新显示panel
		panel.reShow(ground, shape);
		
	}

	/*
	 * 如果当前左移发生冲突，返回真
	 */
	public boolean shapeLeftHasGround() {
		boolean flag = false;
		for(int i=shape.getEndYPos()/TetrisUtils.cell_height-1;
				i>=shape.getStartYPos()/TetrisUtils.cell_height;i--) {
			int j = shape.getStartXPos()/TetrisUtils.cell_width;		
			
//System.out.println(i-shape.getStartYPos()/TetrisUtils.cell_height);
//System.out.println(j-shape.getStartXPos()/TetrisUtils.cell_width);
//System.out.println(shape.getBody()[shape.getWhich()][4*(i-shape.getStartYPos()/TetrisUtils.cell_height)+(j-shape.getStartXPos()/TetrisUtils.cell_width)]);

			if(shape.getBody()[shape.getWhich()][4*(i-shape.getStartYPos()/TetrisUtils.cell_height)+(j-shape.getStartXPos()/TetrisUtils.cell_width)] == 1) {
/*System.out.println("i:"+i);
				//j = j+1;
System.out.println(i);
System.out.println(j);*/
				j--;
				if(i >=0 && ground.getBody()[i][j] == 1){
/*System.out.println("****************");*/
					flag = true;
					break;
				}
			}
		}
		return flag;
	}

	/*
	 * 有障碍物，返回真
	 */
	public boolean shapeRightHasGround() {
		boolean flag = false;
		for(int i=shape.getEndYPos()/TetrisUtils.cell_height-1;
				i>=shape.getStartYPos()/TetrisUtils.cell_height;i--) {
			int j = shape.getEndXPos()/TetrisUtils.cell_width;		
/*System.out.println(i-shape.getStartYPos()/TetrisUtils.cell_height);
System.out.println(j-shape.getStartXPos()/TetrisUtils.cell_width);
System.out.println(shape.getBody()[shape.getWhich()][4*(i-shape.getStartYPos()/TetrisUtils.cell_height)+(j-shape.getStartXPos()/TetrisUtils.cell_width-1)]);
*/
			if(shape.getBody()[shape.getWhich()][4*(i-shape.getStartYPos()/TetrisUtils.cell_height)+(j-shape.getStartXPos()/TetrisUtils.cell_width-1)] == 1) {
/*System.out.println("i:"+i);
				//j = j+1;
System.out.println(i);
System.out.println(j);*/
				if(i >=0 && ground.getBody()[i][j] == 1){
/*System.out.println("****************");*/
					flag = true;
					break;
				}
			}
		}
		return flag;
	}
	
}
