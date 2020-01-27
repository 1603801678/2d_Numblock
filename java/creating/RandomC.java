package creating;
import java.util.Random;
public class RandomC{
	public static boolean[][] cea(int wide,int high){
		Random rand=new Random();
		boolean[][] inm=new boolean[high][wide];
		for(int h=0;h<high;h++){
			for(int w=0;w<wide;w++){
				inm[h][w]=rand.nextBoolean();
			}
		}
		return inm;
	}
}