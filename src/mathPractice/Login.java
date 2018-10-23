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
    //�������壬��弰�ؼ�
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
    //����һ���ļ� 
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
              JOptionPane.showMessageDialog(message, "�û�������Ϊ��");
            }
            else if(pwField.getText().trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "���벻��Ϊ��");
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
                  System.out.println(username+"���룺"+person.get(username));
                
                }
              }
              
             if(flag) {
               if(pwRight.equals(pwField.getText().trim()))
               {
                 new PracticeGUI(usernameField.getText());
               }
              
             }else {
               JFrame message = new JFrame();
               JOptionPane.showMessageDialog(message, "�û��������ڣ�");
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
              JOptionPane.showMessageDialog(message, "�û�������Ϊ��");
            }
            else if(pw.trim().equals("")) {
              JFrame message = new JFrame();
              JOptionPane.showMessageDialog(message, "���벻��Ϊ��");
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
              JOptionPane.showMessageDialog(message, "ע��ɹ����û���Ϊ��"+username+"������Ϊ��"+pw);
              usernameField.setText("");
              pwField.setText("");
              information.add(person);
              
             try {
               //�����ļ��������ݣ���API�п����˽⵽FileOutputStream�Ĺ��캯���������true����׷�ӣ�
               FileOutputStream fos=new FileOutputStream("src/file/login.txt");
               //�ļ������л�
                 ObjectOutputStream oos=new ObjectOutputStream(fos);
               //writeObject �������ڽ�����д�����С����ж��󣨰��� String �����飩��//����ͨ�� writeObject д�롣  
                
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
      * init()��ʼ������ʾ����
      */
    private void init(){
        jframe.setTitle("Сѧ�����ζ���Ц�ѵļӼ�����ϰ���");
        /**
         * ����JPanel����
         */
        jpanel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon img = new ImageIcon("src/image/pig.jpg");
                img.paintIcon(this, g, 0, 0);
            }
        };
        //ΪJLabel,JButton��ʼ���ı�
        jlabel.setText("�û�����");
        jlabel1.setText("��    �룺");
        loginButton.setText("��¼");
        signupButton.setText("ע��");
    
        //������ʾλ�ü���Ļ��С500*400
        jframe.setBounds(450, 240, 600, 400);
        //jpanel����GridBagLayout���ֹ�����
        jpanel.setOpaque(false);
        jpanel.setLayout(gridbag);
        
        //��ʼ���û���label������Ӹÿؼ�������
        constraints = getGridBagConstraints(0,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(jlabel, constraints);
        jpanel.add(jlabel);
        
        //��ʼ���û����ı��򣬲���Ӹ����������
        constraints = getGridBagConstraints(1,0,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridbag.setConstraints(usernameField, constraints);
        jpanel.add(usernameField);
         
        //��ʼ������label
        constraints = getGridBagConstraints(0,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(jlabel1, constraints);
        jpanel.add(jlabel1);
     
        //��ʼ�������ı���
        constraints = getGridBagConstraints(1,1,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),100,0);
        gridbag.setConstraints(pwField, constraints);
        jpanel.add(pwField);
     
        //��ʼ��ע�ᰴť������Ӹÿؼ�������
        constraints = getGridBagConstraints(0,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(signupButton, constraints);
        jpanel.add(signupButton);
    
        //��ʼ����¼��ť
        constraints = getGridBagConstraints(1,2,1,1,0,0,GridBagConstraints.CENTER,GridBagConstraints.NONE,new Insets(10,0,10,0),0,0);
        gridbag.setConstraints(loginButton, constraints);
        jpanel.add(loginButton);
    
        
        //��ӻ��嵽����
        jframe.add(jpanel);
        //�����ʼ�����
    }
 
     private static GridBagConstraints getGridBagConstraints(int gridx,int gridy,int gridwidth,int gridheight,double weightx,double weighty,int anchor,int fill,Insets insets,int ipadx,int ipady){
         return new GridBagConstraints(gridx, gridy, gridwidth, gridheight, weightx, weighty, anchor, fill, insets, ipadx, ipady);
     }
 
     public static void main(String[] args) {
         new Login();
         jframe.setVisible(true);
     }
}
