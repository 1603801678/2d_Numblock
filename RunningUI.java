import sec.MainGame;
import sec.GameThread;
import java.awt.*;
import java.awt.event.*;
class RunningUI{
	public static void main(String[] args){
		Frame main=new Frame("2d_Numblock");
		main.setLayout(new BorderLayout());
		main.setBounds(200,200,400,400);
		Button a=new Button("START");
		a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new GameThread().run();
			}});
		main.add(a,BorderLayout.CENTER);
		Button b=new Button("QUIT");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}});
		main.add(b,BorderLayout.SOUTH);
		main.setVisible(true);
	}
}