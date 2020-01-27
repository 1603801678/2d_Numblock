package sec;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.File;
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
		Choice p1=choiceCreat();
		left.add(p1);
		
		left.add(new JLabel("P2:"));
		Choice p2=choiceCreat();
		left.add(p2);
		
		left.add(new JLabel("Creating:"));
		Choice cea=creatingCreat();
		left.add(cea);
		
		Button a=new Button("START");
		Run run=new Run(twide,thigh,p1,p2,cea,init);
		a.addActionListener(run);
		left.add(a);
		
		Button e=new Button("CANCEL");
		Act act=new Act(init);
		e.addActionListener(act);
		left.add(e);
		
		init.setVisible(true);
	}
	private Choice creatingCreat(){
		File parent=new File("./creating");
		File[] test=parent.listFiles();
		Choice choi= new Choice();
		for(File ff:test){
			String temp=ff.getName();
			if(temp.matches(".+class"))choi.add(temp.replace(".class",""));
		}
		return choi;
	}
	private Choice choiceCreat(){
		File parent=new File("./mods");
		File[] test=parent.listFiles();
		Choice choi= new Choice();
		choi.add("Player");
		for(File ff:test){
			String temp=ff.getName();
			if(temp.matches(".+class"))choi.add(temp.replace(".class",""));
		}
		return choi;
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
		JTextField twide;
		JTextField thigh;
		JFrame ex;
		Choice p1;
		Choice p2;
		Choice cea;
		public Run(JTextField twide,JTextField thigh,Choice p1,Choice p2,Choice cea,JFrame t){
			this.twide=twide;
			this.thigh=thigh;
			ex=t;
			this.p1=p1;
			this.p2=p2;
			this.cea=cea;
		}
		public void actionPerformed(ActionEvent e){
			int wide=Integer.valueOf(twide.getText()).intValue();
			int high=Integer.valueOf(thigh.getText()).intValue();
			if(wide<=0)return;
			if(wide>=60)return;
			if(high<=0)return;
			if(high>=60)return;
			new ControlThread(wide,high,p1.getSelectedItem(),p2.getSelectedItem(),cea.getSelectedItem()).start();
			ex.setVisible(false);
		}
	}
}
