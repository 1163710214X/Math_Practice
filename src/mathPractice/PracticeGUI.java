package mathPractice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import mathPractice.Operation;

/**
 * GUI界面布局，监听、倒计时等的实现
 * 
 * @author 1163710214刘文佳，1163710228刘思琦
 * @version 1.0
 * @date 2018/10/12
 *
 */
public class PracticeGUI {

	private JFrame mainWindow = new JFrame("小学生做梦都会笑醒的加减法练习软件");

	// 面板
	private JPanel selectPanel = new JPanel();
	private JPanel mainPanel = new JPanel();
	private JPanel commandP = new JPanel();

	private JButton JBRedo = new JButton("重做");
	private JButton JBStart = new JButton("开始做题");

	private JLabel JLChooseOp = new JLabel("请选择运算类型：");
	private JLabel JLNumberDigit = new JLabel("请选择运算位数：");
	private JLabel JLBAnsTip = new JLabel("输入答案");

	private String[] operationType = { "+", "-", "混合" };
	private String[] numberOfDigitType = { "1", "2" };
	private JComboBox<String> JCBOperationSelect = new JComboBox<String>(operationType);
	private JComboBox<String> JCBNumberOfDigit = new JComboBox<String>(numberOfDigitType);

	// 显示题目的JLabel
	private JLabel[] JLBQuestions = new JLabel[10];
	// 显示正确答案的JLabel
	private JLabel[] JLBAnswers = new JLabel[10];
	// 显示用户答案是否正确的JLabel
	private JLabel[] JLBIsTrue = new JLabel[10];

	private JTextField[] JTFUsersAnswer = new JTextField[10]; // 定义变量时需要赋初值，不然会出现空指针异常问题

	// 设置Font
	private Font buttonFont = new Font("微软雅黑", Font.PLAIN, 16);
	private Font JLBFont = new Font("微软雅黑", Font.BOLD, 18);
	private Font JTFFont = new Font("微软雅黑", Font.PLAIN, 18);
	private Font JLBAnsFont = new Font("微软雅黑", Font.PLAIN, 16);

	// 类型为Operation的questions数组，只有这个才和Operation类等等那些类关联起来
	private Operation[] questions = new Operation[10];
	// 用户答案数组
	private int[] userAnswer = new int[10];

	private int scores, n = 1;
	private JLabel JLBScores = new JLabel("你的成绩为:");
	private String chara = "+";

	private int usedTime;
	boolean runFlag = false; // runFlag默认为false
	private JPanel PTime = new JPanel();
	private JLabel JLBRemainTime = new JLabel("剩余时间：");
	private JTextField JTFWtime = new JTextField("120");
	private JLabel JLBTime = new JLabel("用时：");

	// 倒计时线程
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
					JOptionPane.showMessageDialog(jf, "出现了未知问题，请重启程序");
				}
				i--;
			}
			// runFlag = false;
			for (int j = 0; j < 10; j++) {
				if (JTFUsersAnswer[j].getText().equals("")) {
					JTFUsersAnswer[j].setText("0");
				}
			}
			printAnswer(); // 倒计时结束，则调用printAnswer()方法
			JBStart.setText("开始做题");
			JLBTime.setText("用时：" + usedTime);
		}
	}

	public PracticeGUI(String username) {
	  mainWindow.setTitle("小学生做梦都会笑醒的加减法练习软件,欢迎"+username+"小朋友");
		// 布局用户名&选择面板
		selectPanel.setPreferredSize(new Dimension(700, 50));
		JLChooseOp.setFont(JLBFont);
		selectPanel.add(JLChooseOp);
		JCBOperationSelect.setPreferredSize(new Dimension(50, 25));
		selectPanel.add(JCBOperationSelect);
		JLNumberDigit.setFont(JLBFont);
		selectPanel.add(JLNumberDigit);
		JCBNumberOfDigit.setPreferredSize(new Dimension(50, 25));
		selectPanel.add(JCBNumberOfDigit);

		// 布局主面板
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
			JLBQuestions[i] = new JLabel("点击开始做题显示题目");
			JLBQuestions[i].setFont(JLBFont);
			JTFUsersAnswer[i] = new JTextField(5); // 一定要加这行，不然会出现空指针错误
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
			JLBQuestions[i] = new JLabel("点击开始做题显示题目");
			JLBQuestions[i].setFont(JLBFont);
			JTFUsersAnswer[i] = new JTextField(5); // 一定要加这行，不然会出现空指针错误
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

		// 布局命令面板
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

		// 使用匿名嵌套类的方式注册开始按钮的事件处理监听器对象
		JBStart.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (JBStart.getText().equals("开始做题")) {

					start(); // 如果按钮上面的文字是"开始做题"，则调用start()方法出题
					JBStart.setText("提交答案");
					// 倒计时线程开始
					LimitTime t = new LimitTime();
					t.start();

				} else {
					for (int i = 0; i < 10; i++) {
						if (JTFUsersAnswer[i].getText().equals("")) {
							JTFUsersAnswer[i].setText("0");
						}
					}
					runFlag = false;// 将runFlag设置为false（线程就会不再执行while循环里的内容）
					JLBTime.setText("用时：" + usedTime);
					JBStart.setText("开始做题");
				}
			}
		});

		// 监听重做按钮
		JBRedo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JBStart.getText().equals("开始做题")) // 若已提交答案，则可以进行重做
				{
					for (int i = 0; i < 10; i++) {
						JTFUsersAnswer[i].setText("");
						JLBAnswers[i].setText("");
						JLBIsTrue[i].setText("");
						JLBScores.setText("");
					}
					JLBTime.setText("用时：");
					LimitTime t = new LimitTime();
					t.start();
					JBStart.setText("提交答案");
				} else // 答案未提交，不能重做
				{
					JFrame notSubmit = new JFrame();
					JOptionPane.showMessageDialog(notSubmit, "提交后才可以重做！提交前可以直接更改答案！");
				}
			}
		});

		// 尽量把主窗体的设置都放到最后
		mainWindow.add(selectPanel, BorderLayout.NORTH);
		mainWindow.add(mainPanel, BorderLayout.CENTER);
		mainWindow.add(commandP, BorderLayout.SOUTH);
		mainWindow.pack();
	//	mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(800, 500); // 设置窗体大小
		mainWindow.setLocationRelativeTo(null); // 将窗口置于屏幕中间
		mainWindow.setVisible(true); // 设置为可见，要放在最后，放在前面则只能看见用户名和选择面板，主面板等需要拖动窗口大小才能看见
	}

	public void start() {
		// 清除TextField和答案标签的内容
		for (int i = 0; i < 10; i++) {
			JTFUsersAnswer[i].setText("");
			JLBAnswers[i].setText("");
			JLBIsTrue[i].setText("");
			JLBScores.setText("");
			JLBTime.setText("用时：");
		}

		// 获取ComboBox的选中值
		chara = (String) JCBOperationSelect.getSelectedItem();
		n = Integer.valueOf((String) JCBNumberOfDigit.getSelectedItem());

		// 根据选择的运算出题
		int flag = 0;
		if (chara.equals("混合"))
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
				JOptionPane.showMessageDialog(jf, "出现未知错误，请重启程序。");
			}
		}
	}

	// 在面板上显示每题的正确答案、得分和用时，并且将每次做题的记录写入文件
	public void printAnswer() {
		// 成绩初始值为100
		scores = 100;

		// 对于每道题
		for (int i = 0; i < 10; i++) {
			// 给用户的答案这一数组赋值（getText的结果为String）
			userAnswer[i] = Integer.valueOf(JTFUsersAnswer[i].getText());

			// questions的类型是operation，用答案和0给questions这个数组赋值
			questions[i].setUsersAnswer(userAnswer[i], 0);

			// 使正确答案显示在面板上
			JLBAnswers[i].setText(questions[i].ptintQA());

			// 在面板上显示答案是否正确
			JLBIsTrue[i].setText(questions[i].isCorrect());

			// 如果错误则将答案和是否正确两个标签的字体颜色设置为红色
			if (JLBIsTrue[i].getText().equals("回答错误")) {
				JLBAnswers[i].setForeground(Color.RED);
				JLBIsTrue[i].setForeground(Color.RED);
				scores -= 10;
			}
			// 正确为黑色
			else {
				JLBAnswers[i].setForeground(Color.BLACK);
				JLBIsTrue[i].setForeground(Color.BLACK);
			}
		}
		// 显示成绩
		JLBScores.setText("你的成绩为：" + scores);
		if(scores==100)
        {
          JFrame newf = new JFrame("祝贺");
          JOptionPane.showMessageDialog(newf, "做得好，你得到了满分！！");
        }
		else if(scores>=90)
		{
		  JFrame newf = new JFrame("祝贺");
		  JOptionPane.showMessageDialog(newf, "做得好，你得到了一个A");
		}
		else if(scores>=80)
        {
          JFrame newf = new JFrame("不错");
          JOptionPane.showMessageDialog(newf, "还不错，你得到了一个B");
        }
		else if(scores>=70)
        {
          JFrame newf = new JFrame("将就");
          JOptionPane.showMessageDialog(newf, "你得到了一个C");
        }
		  else if(scores>=60)
	        {
	          JFrame newf = new JFrame("惊险");
	          JOptionPane.showMessageDialog(newf, "你刚刚及格，你得到了一个D");
	        }
		  else 
          {
            JFrame newf = new JFrame("讨打");
            JOptionPane.showMessageDialog(newf, "你不及格了，等着挨打吧");
          }
	}
}