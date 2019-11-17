package sec;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
public class GameThread extends Thread{
	public GameThread(){}
	public void run(){
		JFrame init=new JFrame("initialization");
		init.setBounds(100,100,200,300);
		Box mid=new Box(BoxLayout.X_AXIS);
		init.add(mid);
		Box left=new Box(BoxLayout.Y_AXIS);
		mid.add(left);
		
		left.add(new JLabel("Wide:"));
		
		JTextField twide=new JTextField("6");
		twide.setSize(10,10);
		left.add(twide);
		
		left.add(new JLabel("High:"));
		
		JTextField thigh=new JTextField("6");
		thigh.setSize(10,10);
		left.add(thigh);
		
		left.add(new JLabel("P1:"));
		left.add(new JLabel("Player"));
		left.add(new JLabel("P2:"));
		left.add(new JLabel("Rand"));
		left.add(new JLabel("Creating:"));
		
		Button a=new Button("START");
		Run run=new Run(twide,thigh,init);
		a.addActionListener(run);
		left.add(a);
		
		Button e=new Button("CANCEL");
		Act act=new Act(init);
		e.addActionListener(act);
		left.add(e);
		
		init.setVisible(true);
	}
	class Act implements ActionListener{
		JFrame ex;
		public Act(JFrame t){
			ex=t;
		}
		public void actionPerformed(ActionEvent e){
			ex.setVisible(false);
		}
	}
	class Run implements ActionListener{
		JTextField wide;
		JTextField high;
		JFrame ex;
		public Run(JTextField twide,JTextField thigh,JFrame t){
			wide=twide;
			high=thigh;
			ex=t;
		}
		public void actionPerformed(ActionEvent e){
			int w=Integer.valueOf(wide.getText()).intValue();
			int h=Integer.valueOf(high.getText()).intValue();
			if(w<=0)return;
			if(w>=60)return;
			if(h<=0)return;
			if(h>=60)return;
			new ControlThread(w,h).start();
			ex.setVisible(false);
		}
	}
}
