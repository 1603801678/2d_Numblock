package sec;
public class MainGame{
	private boolean[][] map;
	public MainGame(boolean[][] inmap){
		map=inmap;
	}
	public boolean control(int y1,int x1,int y2,int x2){
		System.out.println(x1+" "+y1+" "+x2+" "+y2);
		if(x1>=map.length|x2>=map.length){return false;}
		if(y1>=map[0].length|y2>=map[0].length){return false;}
		if(x1==x2){
			if(y1<=y2){
				for(int i=y1;i<=y2;i++){if(map[x1][i]==false)return false;}
				for(int i=y1;i<=y2;i++){map[x1][i]=false;}
				System.out.println("ww");
				return true;
			}
			if(y1>=y2){
				for(int i=y2;i<=y1;i++){if(map[x1][i]==false)return false;}
				for(int i=y2;i<=y1;i++){map[x1][i]=false;}
				System.out.println("ww");
				return true;
			}
		}
		if(y1==y2){
			if(x1<=x2){
				for(int i=x1;i<=x2;i++){if(map[i][y1]==false)return false;}
				for(int i=x1;i<=x2;i++){map[i][y1]=false;}
				return true;
			}
			if(x1>=x2){
				for(int i=x2;i<=x1;i++){if(map[i][y1]==false)return false;}
				for(int i=x2;i<=x1;i++){map[i][y1]=false;}
				return true;
			}
		}
		return false;
	}
	public boolean isFinish(){
		for(int x=0;x<map.length;x++){
			for(int y=0;y<map[0].length;y++){
				if(map[x][y]==true)return false;
			}
		}
		return true;
	}
	public boolean[][] getMap(){
		return map;
	}
	public int getHigh(){
		return map.length;
	}
	public int getWide(){
		return map[0].length;
	}
}
