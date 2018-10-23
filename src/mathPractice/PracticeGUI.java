package mathPractice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mathPractice.Operation;

/**
 * GUI���沼�֣�����������ʱ�ȵ�ʵ��
 * 
 * @author 1163710214���ļѣ�1163710228��˼��
 * @version 1.0
 * @date 2018/10/12
 *
 */
public class PracticeGUI {

	private JFrame mainWindow = new JFrame("Сѧ�����ζ���Ц�ѵļӼ�����ϰ���");

	// ���
	private JPanel selectPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel commandP = new JPanel();

	private JButton JBRedo = new JButton("����");
	private JButton JBStart = new JButton("��ʼ����");

	private JLabel JLChooseOp = new JLabel("��ѡ���������ͣ�");
	private JLabel JLNumberDigit = new JLabel("��ѡ������λ����");
	private JLabel JLBAnsTip = new JLabel("�����");

	private String[] operationType = { "+", "-", "���" };
	private String[] numberOfDigitType = { "1", "2" };
	private JComboBox<String> JCBOperationSelect = new JComboBox<String>(operationType);
	private JComboBox<String> JCBNumberOfDigit = new JComboBox<String>(numberOfDigitType);

	// ��ʾ��Ŀ��JLabel
	private JLabel[] JLBQuestions = new JLabel[10];
	// ��ʾ��ȷ�𰸵�JLabel
	private JLabel[] JLBAnswers = new JLabel[10];
	// ��ʾ�û����Ƿ���ȷ��JLabel
	private JLabel[] JLBIsTrue = new JLabel[10];

	private JTextField[] JTFUsersAnswer = new JTextField[10]; // �������ʱ��Ҫ����ֵ����Ȼ����ֿ�ָ���쳣����

	// ����Font
	private Font buttonFont = new Font("΢���ź�", Font.PLAIN, 16);
	private Font JLBFont = new Font("΢���ź�", Font.BOLD, 18);
	private Font JTFFont = new Font("΢���ź�", Font.PLAIN, 18);
	private Font JLBAnsFont = new Font("΢���ź�", Font.PLAIN, 16);

	// ����ΪOperation��questions���飬ֻ������ź�Operation��ȵ���Щ���������
	private Operation[] questions = new Operation[10];
	// �û�������
	private int[] userAnswer = new int[10];

	private int scores, n = 1;
	private JLabel JLBScores = new JLabel("��ĳɼ�Ϊ:");
	private String chara = "+";

	private int usedTime;
	boolean runFlag = false; // runFlagĬ��Ϊfalse
	private JPanel PTime = new JPanel();
	private JLabel JLBRemainTime = new JLabel("ʣ��ʱ�䣺");
	private JTextField JTFWtime = new JTextField("120");
	private JLabel JLBTime = new JLabel("��ʱ��");

	// ����ʱ�߳�
	class LimitTime extends Thread {
		public void run() {
			runFlag = true;
			int i = 120;
			usedTime = 0;
			while (runFlag && i >= 0) {
				JTFWtime.setText("" + i);
				try {
					sleep(1000);
					usedTime++;
				} catch (InterruptedException e) {
					JFrame jf = new JFrame();
					JOptionPane.showMessageDialog(jf, "������δ֪���⣬����������");
				}
				i--;
			}
			// runFlag = false;
			for (int j = 0; j < 10; j++) {
				if (JTFUsersAnswer[j].getText().equals("")) {
					JTFUsersAnswer[j].setText("0");
				}
			}
			printAnswer(); // ����ʱ�����������printAnswer()����
			JBStart.setText("��ʼ����");
			JLBTime.setText("��ʱ��" + usedTime);
		}
	}

	public PracticeGUI(String username) {
	  mainWindow.setTitle("Сѧ�����ζ���Ц�ѵļӼ�����ϰ���,��ӭ"+username+"С����");
		// �����û���&ѡ�����
		selectPanel.setPreferredSize(new Dimension(700, 50));
		JLChooseOp.setFont(JLBFont);
		selectPanel.add(JLChooseOp);
		JCBOperationSelect.setPreferredSize(new Dimension(50, 25));
		selectPanel.add(JCBOperationSelect);
		JLNumberDigit.setFont(JLBFont);
		selectPanel.add(JLNumberDigit);
		JCBNumberOfDigit.setPreferredSize(new Dimension(50, 25));
		selectPanel.add(JCBNumberOfDigit);

		// ���������
		mainPanel.setPreferredSize(new Dimension(700, 400));
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints GBC = new GridBagConstraints();
		GBC.weightx = 1;
		GBC.weighty = 1;
		GBC.gridx = 1;
		GBC.gridy = 0;
		GBC.anchor = GridBagConstraints.WEST;
		gridbag.setConstraints(JLBAnsTip, GBC);
		JLBAnsTip.setFont(JLBFont);
		mainPanel.add(JLBAnsTip);

		GBC.gridx = 2;
		GBC.gridx = 4;
		GBC.gridwidth = 2;
		GBC.anchor = GridBagConstraints.CENTER;
		gridbag.setConstraints(JLBScores, GBC);
		JLBScores.setFont(JLBFont);
		mainPanel.add(JLBScores);

		for (int i = 0; i < 5; i++) {
			JLBQuestions[i] = new JLabel("�����ʼ������ʾ��Ŀ");
			JLBQuestions[i].setFont(JLBFont);
			JTFUsersAnswer[i] = new JTextField(5); // һ��Ҫ�����У���Ȼ����ֿ�ָ�����
			JTFUsersAnswer[i].setFont(JTFFont);
			JLBAnswers[i] = new JLabel("");
			JLBAnswers[i].setFont(JLBAnsFont);
			JLBIsTrue[i] = new JLabel("");
			JLBIsTrue[i].setFont(JLBAnsFont);

			GBC.gridwidth = 1;
			GBC.gridx = 0;
			GBC.gridy = 2 * i + 1;
			GBC.anchor = GridBagConstraints.EAST;
			gridbag.setConstraints(JLBQuestions[i], GBC);
			mainPanel.add(JLBQuestions[i]);
			GBC.anchor = GridBagConstraints.CENTER;
			GBC.gridy = 2 * i + 2;
			GBC.gridwidth = 2;
			gridbag.setConstraints(JLBAnswers[i], GBC);
			mainPanel.add(JLBAnswers[i]);

			GBC.gridwidth = 1;
			GBC.gridx = 1;
			GBC.gridy = 2 * i + 1;
			GBC.anchor = GridBagConstraints.WEST;
			gridbag.setConstraints(JTFUsersAnswer[i], GBC);
			mainPanel.add(JTFUsersAnswer[i]);

			GBC.gridx = 2;
			GBC.gridy = 2 * i + 2;
			gridbag.setConstraints(JLBIsTrue[i], GBC);
			mainPanel.add(JLBIsTrue[i]);
		}

		for (int i = 5; i < 10; i++) {
			JLBQuestions[i] = new JLabel("�����ʼ������ʾ��Ŀ");
			JLBQuestions[i].setFont(JLBFont);
			JTFUsersAnswer[i] = new JTextField(5); // һ��Ҫ�����У���Ȼ����ֿ�ָ�����
			JTFUsersAnswer[i].setFont(JTFFont);
			JLBAnswers[i] = new JLabel("");
			JLBAnswers[i].setFont(JLBAnsFont);
			JLBIsTrue[i] = new JLabel("");
			JLBIsTrue[i].setFont(JLBAnsFont);

			GBC.gridx = 4;
			GBC.gridy = 2 * i - 9;
			GBC.anchor = GridBagConstraints.EAST;
			gridbag.setConstraints(JLBQuestions[i], GBC);
			mainPanel.add(JLBQuestions[i]);
			GBC.anchor = GridBagConstraints.CENTER;
			GBC.gridy = 2 * i - 8;
			GBC.gridwidth = 2;
			gridbag.setConstraints(JLBAnswers[i], GBC);
			mainPanel.add(JLBAnswers[i]);

			GBC.gridwidth = 1;
			GBC.gridx = 5;
			GBC.gridy = 2 * i - 9;
			GBC.anchor = GridBagConstraints.WEST;
			gridbag.setConstraints(JTFUsersAnswer[i], GBC);
			mainPanel.add(JTFUsersAnswer[i]);

			GBC.gridx = 6;
			GBC.gridy = 2 * i - 8;
			gridbag.setConstraints(JLBIsTrue[i], GBC);
			mainPanel.add(JLBIsTrue[i]);

		}
		mainPanel.setLayout(gridbag);

		// �����������
		commandP.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 20));
		JLBRemainTime.setFont(JLBFont);
		JLBTime.setFont(JLBFont);
		JTFWtime.setFont(JTFFont);
		PTime.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 20));
		PTime.add(JLBRemainTime);
		PTime.add(JTFWtime);
		PTime.add(JLBTime);
		commandP.add(PTime);
		JBStart.setFont(buttonFont);
		commandP.add(JBStart);
		JBRedo.setFont(buttonFont);
		commandP.add(JBRedo);

		// ʹ������Ƕ����ķ�ʽע�Ὺʼ��ť���¼��������������
		JBStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JBStart.getText().equals("��ʼ����")) {

					start(); // �����ť�����������"��ʼ����"�������start()��������
					JBStart.setText("�ύ��");
					// ����ʱ�߳̿�ʼ
					LimitTime t = new LimitTime();
					t.start();

				} else {
					for (int i = 0; i < 10; i++) {
						if (JTFUsersAnswer[i].getText().equals("")) {
							JTFUsersAnswer[i].setText("0");
						}
					}
					runFlag = false;// ��runFlag����Ϊfalse���߳̾ͻ᲻��ִ��whileѭ��������ݣ�
					JLBTime.setText("��ʱ��" + usedTime);
					JBStart.setText("��ʼ����");
				}
			}
		});

		// ����������ť
		JBRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JBStart.getText().equals("��ʼ����")) // �����ύ�𰸣�����Խ�������
				{
					for (int i = 0; i < 10; i++) {
						JTFUsersAnswer[i].setText("");
						JLBAnswers[i].setText("");
						JLBIsTrue[i].setText("");
						JLBScores.setText("");
					}
					JLBTime.setText("��ʱ��");
					LimitTime t = new LimitTime();
					t.start();
					JBStart.setText("�ύ��");
				} else // ��δ�ύ����������
				{
					JFrame notSubmit = new JFrame();
					JOptionPane.showMessageDialog(notSubmit, "�ύ��ſ����������ύǰ����ֱ�Ӹ��Ĵ𰸣�");
				}
			}
		});

		// ����������������ö��ŵ����
		mainWindow.add(selectPanel, BorderLayout.NORTH);
		mainWindow.add(mainPanel, BorderLayout.CENTER);
		mainWindow.add(commandP, BorderLayout.SOUTH);
		mainWindow.pack();
	//	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(800, 500); // ���ô����С
		mainWindow.setLocationRelativeTo(null); // ������������Ļ�м�
		mainWindow.setVisible(true); // ����Ϊ�ɼ���Ҫ������󣬷���ǰ����ֻ�ܿ����û�����ѡ����壬��������Ҫ�϶����ڴ�С���ܿ���
	}

	public void start() {
		// ���TextField�ʹ𰸱�ǩ������
		for (int i = 0; i < 10; i++) {
			JTFUsersAnswer[i].setText("");
			JLBAnswers[i].setText("");
			JLBIsTrue[i].setText("");
			JLBScores.setText("");
			JLBTime.setText("��ʱ��");
		}

		// ��ȡComboBox��ѡ��ֵ
		chara = (String) JCBOperationSelect.getSelectedItem();
		n = Integer.valueOf((String) JCBNumberOfDigit.getSelectedItem());

		// ����ѡ����������
		int flag = 0;
		if (chara.equals("���"))
			flag = 1;
		for (int i = 0; i < 10; i++) {
			if (flag == 1) {
				int tempCh = (int) (Math.random() * 2 + 1);
				switch (tempCh) {
				case 1:
					chara = "+";
					break;
				case 2:
					chara = "-";
					break;
				}
			}

			switch (chara) {
			case "+":
				questions[i] = new Addition(n);
				JLBQuestions[i].setText(questions[i].printQuestion());
				break;
			case "-":
				questions[i] = new Subtraction(n);
				JLBQuestions[i].setText(questions[i].printQuestion());
				break;
			default:
				JFrame jf = new JFrame();
				JOptionPane.showMessageDialog(jf, "����δ֪��������������");
			}
		}
	}

	// ���������ʾÿ�����ȷ�𰸡��÷ֺ���ʱ�����ҽ�ÿ������ļ�¼д���ļ�
	public void printAnswer() {
		// �ɼ���ʼֵΪ100
		scores = 100;

		// ����ÿ����
		for (int i = 0; i < 10; i++) {
			// ���û��Ĵ���һ���鸳ֵ��getText�Ľ��ΪString��
			userAnswer[i] = Integer.valueOf(JTFUsersAnswer[i].getText());

			// questions��������operation���ô𰸺�0��questions������鸳ֵ
			questions[i].setUsersAnswer(userAnswer[i], 0);

			// ʹ��ȷ����ʾ�������
			JLBAnswers[i].setText(questions[i].ptintQA());

			// ���������ʾ���Ƿ���ȷ
			JLBIsTrue[i].setText(questions[i].isCorrect());

			// ��������򽫴𰸺��Ƿ���ȷ������ǩ��������ɫ����Ϊ��ɫ
			if (JLBIsTrue[i].getText().equals("�ش����")) {
				JLBAnswers[i].setForeground(Color.RED);
				JLBIsTrue[i].setForeground(Color.RED);
				scores -= 10;
			}
			// ��ȷΪ��ɫ
			else {
				JLBAnswers[i].setForeground(Color.BLACK);
				JLBIsTrue[i].setForeground(Color.BLACK);
			}
		}
		// ��ʾ�ɼ�
		JLBScores.setText("��ĳɼ�Ϊ��" + scores);
		if(scores==100)
        {
          JFrame newf = new JFrame("ף��");
          JOptionPane.showMessageDialog(newf, "���úã���õ������֣���");
        }
		else if(scores>=90)
		{
		  JFrame newf = new JFrame("ף��");
		  JOptionPane.showMessageDialog(newf, "���úã���õ���һ��A");
		}
		else if(scores>=80)
        {
          JFrame newf = new JFrame("����");
          JOptionPane.showMessageDialog(newf, "��������õ���һ��B");
        }
		else if(scores>=70)
        {
          JFrame newf = new JFrame("����");
          JOptionPane.showMessageDialog(newf, "��õ���һ��C");
        }
		  else if(scores>=60)
	        {
	          JFrame newf = new JFrame("����");
	          JOptionPane.showMessageDialog(newf, "��ոռ�����õ���һ��D");
	        }
		  else 
          {
            JFrame newf = new JFrame("�ִ�");
            JOptionPane.showMessageDialog(newf, "�㲻�����ˣ����Ű����");
          }
	}
}