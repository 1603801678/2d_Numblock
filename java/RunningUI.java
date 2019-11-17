import sec.GameThread;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
class RunningUI{
	public static void main(String[] args){
		JFrame main=new JFrame("2d_Numblock");
		Box inin=new Box(BoxLayout.Y_AXIS);
		main.add(inin);
		main.setBounds(200,200,200,200);
		Button a=new Button("START");
		a.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				new GameThread().run();
			}});
		inin.add(a);
		Button b=new Button("QUIT");
		b.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}});
		inin.add(b);
		main.setVisible(true);
	}
}