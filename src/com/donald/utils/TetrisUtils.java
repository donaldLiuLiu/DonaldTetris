package com.donald.utils;

import java.awt.Color;

/**
 * 
 * @author Donald
 *	工具类
 */
public class TetrisUtils {
	
	public static int cell_width = 20;  //每个格子的宽度
	public static int cell_height = 20;  //每个格子的高度
	public static int cell_width_count = 20;    //横向每个格子的数量
	public static int cell_height_count = 20;   //纵向每个格子额数量
	
	public static Color shape_color = Color.GREEN;   //形状的颜色
	public static int shape_speed = 2;               //每秒掉几格
	
	public static Color ground_color = Color.DARK_GRAY;  //地面的颜色
	
	public static Color border_color = new Color(0xff0000);  //边框的颜色
	
	public static Color bg_color = Color.LIGHT_GRAY;    //背景色
	
}
