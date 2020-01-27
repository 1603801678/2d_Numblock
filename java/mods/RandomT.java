package mods;
import java.util.Random;
public class RandomT{
	public static int[] control(boolean[][] map){
		int[] re=new int[4];
		Random rand=new Random();
		while(true){
			int x=rand.nextInt(map.length);
			int y=rand.nextInt(map[0].length);
			if(map[x][y]==true){
				re[0]=y;
				re[1]=x;
				re[2]=y;
				re[3]=x;
				return re;
			}
		}
	}
}