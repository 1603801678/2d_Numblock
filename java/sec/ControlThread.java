package sec;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;
public class ControlThread extends Thread{
	final int rim=20;
	boolean flag=true;
	int wide;
	int high;
	String creating="Randem";
	String P1="Player";
	String P2="Player";
	MainGame game;
	int mx=-1;
	int my=-1;
	int dx0=-1;
	int dy0=-1;
	int dx1=-1;
	int dy1=-1;
	boolean isclick=false;
	NStage nstage;
	boolean isp1=true;
	public ControlThread(int wide,int high){
		this.wide=wide;
		this.high=high;
		boolean[][] inm=new boolean[high][wide];
		Random rand=new Random();
		for(int h=0;h<high;h++){
			for(int w=0;w<wide;w++){
				inm[h][w]=rand.nextBoolean();
			}
		}
		game=new MainGame(inm);
	}
	public void run(){
		JFrame main=new JFrame("2d_Numblock");
		Box inin=new Box(BoxLayout.X_AXIS);
		main.add(inin);
		
		Box left=new Box(BoxLayout.Y_AXIS); 
		inin.add(left);
		main.setBounds(400,300,rim*wide+100,rim*high+40);
		JTextArea sign=new JTextArea();
		sign.setText(">>p1:\n"+P1+"\n  p2:\n"+P2);
		//sign.setSize(200,200);
		left.add(sign);
		JButton ok=new JButton("OK");
		ok.addActionListener(new Correct(this));
		left.add(ok);
		
		nstage=new NStage(this,game);
		MMove mmove=new MMove(this);
		nstage.addMouseMotionListener(mmove);
		MClick mclick=new MClick(this);
		nstage.addMouseListener(mclick);
		nstage.setSize(rim*wide,rim*high);
		inin.add(nstage);
		
		main.setVisible(true);
		while(!game.isFinish()){
			playerControl();
			isp1=false;
			sign.setText("  p1:\n"+P1+"\n>>p2:\n"+P2);
			nstage.repaint();
			if(game.isFinish())break;
			playerControl();
			isp1=true;
			sign.setText(">>p1:\n"+P1+"\n  p2:\n"+P2);
			nstage.repaint();
			//nstage.repaint();
			//System.out.println(10000);
			//try{sleep(10000);}catch(InterruptedException e){}
		}
		System.out.println(isp1?"P1":"P2"+"win");
		nstage.removeMouseMotionListener(mmove);
		nstage.removeMouseListener(mclick);
		main.setVisible(false);
	}
	boolean waiting=true;
	private void playerControl(){
		do{
			while(waiting){}
			waiting=true;
		}while(!game.control(dx0/rim,dy0/rim,dx1/rim,dy1/rim));
	}
	public int[] getRed(){
		if(dx0>=0&dx0<=rim*wide&dy0>=0&dy0<=rim*high&dx1>=0&dx1<=rim*wide&dy1>=0&dy1<=rim*high){
			if(Math.abs(dx0-dx1)>Math.abs(dy0-dy1)){
				int x0=0;
				int x1=0;
				if(dx0>dx1){x1=dx0;x0=dx1;}
				else{x0=dx0;x1=dx1;}
				int[] re={x0-x0%rim+1,dy0-dy0%rim+1,x1-x1%rim-x0+x0%rim+rim-2,rim-2};
				return re;
			}
			else{
				int y0=0;
				int y1=0;
				if(dy0>dy1){y1=dy0;y0=dy1;}
				else{y0=dy0;y1=dy1;}
				int[] re={dx0-dx0%rim+1,y0-y0%rim+1,rim-2,y1-y1%rim-y0+y0%rim+rim-2};
				return re;
			}
		}
		else{int[] re={-1,-1,-1,-1};return re;}
	}
	/*class Can implements ActionListener{
		Thread ext;
		public Can(Thread t){
			ext=t;
		}
		public void actionPerformed(ActionEvent e){
			ext.stop();
		}
	}*/
	class Correct implements ActionListener{
		ControlThread t;
		public Correct(ControlThread t){
			this.t=t;
		}
		public void actionPerformed(ActionEvent e){
			t.waiting=false;
		}
	}
	class NStage extends Canvas{
		ControlThread t;
		MainGame m;
		int w,h;
		public NStage(ControlThread t,MainGame m){
			this.t=t;
			this.m=m;
			h=m.getHigh();
			w=m.getWide();
			}
		public void paint(Graphics g){
			paintBack(g);
			paintFront(g);
		}
		private void paintBack(Graphics g){
			boolean[][] inmp=m.getMap();
			for(int th=0;th<h;th++){
				for(int tw=0;tw<w;tw++){
					if(inmp[th][tw]==false)g.setColor(Color.WHITE);
					else g.setColor(Color.BLACK);
					g.fillRect(tw*rim,th*rim,rim,rim);
				}
			}
		}
		private void paintFront(Graphics g){
			int mx=t.mx;
			int my=t.my;
			int[] p=t.getRed();
			if(mx>0&mx<rim*w&my>0&my<rim*h){
				g.setColor(Color.GREEN);
				g.fillRect(mx-mx%rim+2,my-my%rim+2,rim-4,rim-4);
			}
			if(p[0]>0&&p[1]>0){
				g.setColor(Color.RED);
				g.fillRect(p[0],p[1],p[2],p[3]);
			}
		}
	}
	class MMove implements MouseMotionListener{
		ControlThread t;
		public MMove(ControlThread t){this.t=t;}
		public void mouseDragged(MouseEvent e){
			int xx=e.getX();
			int yy=e.getY();
			t.dx1=xx;
			t.dy1=yy;
			//t.nstage.repaint(xx-xx%rim,yy-yy%rim,rim,rim);
			t.nstage.repaint();
		}
		public void mouseMoved(MouseEvent e){
			int xx=e.getX();
			int yy=e.getY();
			t.mx=xx;
			t.my=yy;
			t.nstage.repaint();
		}
	}
	class MClick implements MouseListener{
		ControlThread t;
		public MClick(ControlThread t){this.t=t;}
		public void mouseClicked(MouseEvent e){
			t.dx0=e.getX();
			t.dy0=e.getY();
			t.dx1=dx0;
			t.dy1=dy0;
			t.nstage.repaint();
		}
		public void mouseEntered(MouseEvent e){}
		public void mouseExited(MouseEvent e){
			t.isclick=false;
		}
		public void mousePressed(MouseEvent e){
			t.isclick=true;
			t.dx0=e.getX();
			t.dy0=e.getY();
			t.nstage.repaint();
		}
		public void mouseReleased(MouseEvent e){
			if(isclick==true){
				t.isclick=false;
				t.dx1=e.getX();
				t.dy1=e.getY();
			}
			//t.nstage.repaint();
		}
	}
	
}