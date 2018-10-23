package mathPractice;

import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
 
public class Login {
    //声明窗体，面板及控件
    private static JFrame jframe;
    private JLabel jlabel,jlabel1;
    private GridBagLayout gridbag;
    private GridBagConstraints constraints;
    private JTextField usernameField;
    private JPasswordField pwField;
    private JButton loginButton,signupButton;
    private JPanel jpanel;
    List<Map<String,String>> information = new ArrayList<>();
    File file;
  
   
    
    public Login(){
    //创建一个文件 
     file =new File("src/file/login.txt");
    
        jframe = new JFrame();
        jlabel = new JLabel();
        jlabel1 = new JLabel();
        usernameField = new JTextField();
        pwField = new JPasswordField();
        gridbag = new GridBagLayout();
        loginButton = new JButton();
        loginButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {        
            if(usernameField.getText().trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "用户名不能为空");
            }
            else if(pwField.getText().trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "密码不能为空");
            }
            else {
            
              FileInputStream fis;
              try {
                fis = new FileInputStream("src/file/login.txt");
                ObjectInputStream ois=new ObjectInputStream(fis);
                 information=(List<Map<String,String>>)ois.readObject(); 
           
                 ois.close(); 
              } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              } 
           
              boolean flag = false;
              
              String pwRight = null;
              for(int i=0;i<information.size();i++)
              {
                Map<String,String> person = information.get(i);
                Set<String> key = person.keySet();
                 Iterator it =  key.iterator();
                while(it.hasNext())
                {
                  String username = (String) it.next();
                  String pw =person.get(username);
                  if(username.equals(usernameField.getText().trim()))
                     {
                    pwRight =  person.get(username);
                    flag = true;
                     }
                  System.out.println(username+"密码："+person.get(username));
                
                }
              }
              
             if(flag) {
               if(pwRight.equals(pwField.getText().trim()))
               {
                 new PracticeGUI(usernameField.getText());
               }
              
             }else {
               JFrame message = new JFrame();
               JOptionPane.showMessageDialog(message, "用户名不存在！");
             }
            }          
          }
         });

        
        
        
        
        
        
        signupButton = new JButton();
        signupButton.addActionListener(new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {      
            String username = usernameField.getText();
            String pw = pwField.getText();
            if(username.trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "用户名不能为空");
            }
            else if(pw.trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "密码不能为空");
            }
            else {
              FileInputStream fis;
              try {
                fis = new FileInputStream("src/file/login.txt");
                ObjectInputStream ois=new ObjectInputStream(fis);
                 information=(List<Map<String,String>>)ois.readObject(); 
           
                 ois.close(); 
              } catch (IOException | ClassNotFoundException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
              } 
              
              
              Map<String,String> person = new HashMap<String,String>();
              person.put(username, pw);
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "注册成功，用户名为："+username+"，密码为："+pw);
              usernameField.setText("");
              pwField.setText("");
              information.add(person);
              
             try {
               //创建文件保存数据，从API中可以了解到FileOutputStream的构造函数有这个，true代表追加，
               FileOutputStream fos=new FileOutputStream("src/file/login.txt");
               //文件的序列化
                 ObjectOutputStream oos=new ObjectOutputStream(fos);
               //writeObject 方法用于将对象写入流中。所有对象（包括 String 和数组）都//可以通过 writeObject 写入。  
                
               oos.writeObject(information); 
               oos.close(); 
             }catch(IOException e1)
             {
               System.out.println(e1.getMessage());          
             }            
            }
           
          }
         });
        init();
    }
 
     /**
      * init()初始化并显示界面
      */
    private void init(){
        jframe.setTitle("小学生做梦都会笑醒的加减法练习软件");
        /**
         * 设置JPanel背景
         */
        jpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/image/pig.jpg");
                img.paintIcon(this, g, 0, 0);
            }
        };
        //为JLabel,JButton初始化文本
        jlabel.setText("用户名：");
        jlabel1.setText("密    码：");
        loginButton.setText("登录");
        signupButton.setText("注册");
    
        //设置显示位置及屏幕大小500*400
        jframe.setBounds(450, 240, 600, 400);
        //jpanel采用GridBagLayout布局管理器
        jpanel.setOpaque(false);
        jpanel.setLayout(gridbag);
        
        //初始化用户名label，并添加该控件到画板
        constraints = getGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(jlabel, constraints);
        jpanel.add(jlabel);
        
        //初始化用户名文本框，并添加该组件到画板
        constraints = getGridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridbag.setConstraints(usernameField, constraints);
        jpanel.add(usernameField);
         
        //初始化密码label
        constraints = getGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(jlabel1, constraints);
        jpanel.add(jlabel1);
     
        //初始化密码文本框
        constraints = getGridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridbag.setConstraints(pwField, constraints);
        jpanel.add(pwField);
     
        //初始化注册按钮，并添加该控件到画板
        constraints = getGridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(signupButton, constraints);
        jpanel.add(signupButton);
    
        //初始化登录按钮
        constraints = getGridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(loginButton, constraints);
        jpanel.add(loginButton);
    
        
        //添加画板到窗体
        jframe.add(jpanel);
        //窗体初始化完成
    }
 
     private static GridBagConstraints getGridBagConstraints(int gridx,int gridy,int gridwidth,int gridheight,double weightx,double weighty,int anchor,int fill,Insets insets,int ipadx,int ipady){
         return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady);
     }
 
     public static void main(String[] args) {
         new Login();
         jframe.setVisible(true);
     }
}
