package com.donald.controller;

import com.donald.domain.Ground;
import com.donald.domain.Shape;
import com.donald.exception.GameOverException;
import com.donald.factory.ShapeFactory;
import com.donald.utils.TetrisUtils;
import com.donald.view.GamePanel;

public class Controller {
	
	private Ground ground;  //����
	private Shape shape;    //��û�б�ɵ������״
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
	 * ��ʼ��Ϸ
	 */
	public void start() throws Exception {
		//����shape���µ�һ��
		
		for(;;) {
			
			Thread.sleep(1000/TetrisUtils.shape_speed);
			handlerShapeMeetGround();
			
			driveMoveDown();
			//System.out.println("�ƶ�һ����");
			/*ÿ�ƶ�һ��������һ��ײ�ذ����
			 * 	
			*/
			panel.reShow(ground, shape);
//System.out.println("startYPos:"+shape.getStartYPos());
			handlerShapeMeetGround();
//System.out.println("startYPos:"+shape.getStartYPos());
		}
		
	}

	private void handlerShapeMeetGround() {
		/*�����״�����ذ�,����״��ɵذ�
		 * 		�ж��Ƿ��������������
		 * 		������ԣ�������Щ�У�������ʾ�ذ�
		 * 	����������״����ʾ��״������
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
						System.out.println("****������****");
						//��������ˣ����ҵ�ǰ����״�����ϽǴ�ֱΪ0��������Ϸ����
						//Сbug,����shapeʱ��ָ����startYPosΪ0...
//System.out.println("startYPos"+shape.getStartYPos());
						if(shape.getStartYPos()/TetrisUtils.cell_height <= 0) {
							//game over
							throw new GameOverException();
						}
						//������
						flag = true;
						break;
					}
				}
				if(flag) {
					break;
				}
			}
			if(flag) {
				//����״��ɵذ�
				ground.shape2ground(shape);
				//����ذ�����
				ground.handlerGroundLose();
				//����������״
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
		//���û�������ذ壬ʲôҲ����
//System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	private void driveMoveDown() {
		shape.setStartYPos(shape.getStartYPos()+TetrisUtils.cell_height);
		shape.setEndYPos(shape.getEndYPos()+TetrisUtils.cell_height);
		//������ʾpanel
		panel.reShow(ground, shape);
		
	}

	/*
	 * �����ǰ���Ʒ�����ͻ��������
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
	 * ���ϰ��������
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
