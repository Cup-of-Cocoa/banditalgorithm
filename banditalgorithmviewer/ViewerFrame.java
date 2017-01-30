package banditalgorithmviewer;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import bandit.*;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import bandit.AgentUCBVariance;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class ViewerFrame extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;

	JPanel banditPanel1, banditPanel2;
	JLabel reg, tr, banditLabel1, banditLabel2, resultLabel1, resultLabel2, ucbLabel1, ucbLabel2, regretLabel1, regretLabel2;
	JLabel trialTime1, trialTime2, rewardMean1, rewardMean2;
	JButton banditButton1, banditButton2;

	BanditBernoulli bandit1 = new BanditBernoulli(0.9);
	BanditBernoulli bandit2 = new BanditBernoulli(0.6);

	int trialTime = 0;

	List<Double> rewardMeanList = new ArrayList<Double>();//�e�X���b�g�����܂łɔr�o�������_�̕���
	List<Integer> trialTimeList = new ArrayList<Integer>();//�e�X���b�g�̎��s��


	public ViewerFrame() {
		rewardMeanList.add(bandit1.play());
		rewardMeanList.add(bandit2.play());
		trialTimeList.add(1);
		trialTimeList.add(1);
		trialTime++;trialTime++;
		double ucb1 = Math.sqrt((2*Math.log(trialTime))/(double)trialTimeList.get(0));
		double ucb2 = Math.sqrt((2*Math.log(trialTime))/(double)trialTimeList.get(1));

		setTitle("Viewer");
		setLayout(new GridLayout(1,2));
		setSize(400, 400);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//�~����������E�B���h�E�����

		banditPanel1 = new JPanel(new GridLayout(7,1));
		banditLabel1 = new JLabel("Bandit1");
		banditPanel1.add(banditLabel1);
		banditButton1 = new JButton("Bandit1");
		banditButton1.setActionCommand("bandit1");
		banditButton1.addActionListener(this);
		banditPanel1.add(banditButton1);
		resultLabel1 = new JLabel("");
		banditPanel1.add(resultLabel1);
		ucbLabel1 = new JLabel("UCB: " + ucb1);
		banditPanel1.add(ucbLabel1);
		regretLabel1 = new JLabel("Regret: ");
		banditPanel1.add(regretLabel1);
		trialTime1 = new JLabel(Integer.toString(trialTimeList.get(0)));
		banditPanel1.add(trialTime1);
		reg = new JLabel("");
		banditPanel1.add(reg);
		add(banditPanel1);

		banditPanel2 = new JPanel(new GridLayout(7,1));
		banditLabel2 = new JLabel("Bandit2");
		banditPanel2.add(banditLabel2);
		banditButton2 = new JButton("Bandit2");
		banditButton2.setActionCommand("bandit2");
		banditButton2.addActionListener(this);
		banditPanel2.add(banditButton2);
		resultLabel2 = new JLabel("");
		banditPanel2.add(resultLabel2);
		ucbLabel2 = new JLabel("UCB: " + ucb2);
		banditPanel2.add(ucbLabel2);
		regretLabel2 = new JLabel("Regret: ");
		banditPanel2.add(regretLabel2);
		trialTime2 = new JLabel(Integer.toString(trialTimeList.get(1)));
		banditPanel2.add(trialTime2);
		tr = new JLabel(Integer.toString(trialTime));
		banditPanel2.add(tr);
		add(banditPanel2);
	}

	public void actionPerformed(ActionEvent e){
		trialTime++;
		tr.setText(Integer.toString(trialTime));
		if(e.getActionCommand().equals("bandit1")) {
			double reward = bandit1.play();
			if(reward == 1.0) resultLabel1.setText("������");
			else resultLabel1.setText("�͂���");
			trialTimeList.set(0, trialTimeList.get(0)+1);
			trialTime1.setText(Integer.toString(trialTimeList.get(0)));
			double ucb = Math.sqrt((2*Math.log(trialTime))/(double)trialTimeList.get(0));
			ucbLabel1.setText(Double.toString(ucb));


		}
		else if(e.getActionCommand().equals("bandit2")) {
			double reward = bandit2.play();
			if(reward == 1.0) resultLabel2.setText("������");
			else resultLabel2.setText("�͂���");
			trialTimeList.set(1, trialTimeList.get(1)+1);
			trialTime1.setText(Integer.toString(trialTimeList.get(1)));
			double ucb = Math.sqrt((2*Math.log(trialTime))/(double)trialTimeList.get(1));
			ucbLabel2.setText(Double.toString(ucb));
		}
	}

	public static void main(String[] args) {
		new ViewerFrame().setVisible(true);;
	}
}