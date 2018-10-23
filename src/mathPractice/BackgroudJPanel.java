package mathPractice;


import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class BackgroudJPanel {
     private static JFrame jframe;  //����һ������
     private JPanel jpanel;         //����һ������
  
     public BackgroudJPanel(){              //���췽��
         jframe = new JFrame();
         init();
     }
 
     private void init(){
         jframe.setTitle("����");
         jpanel = new JPanel(){//�ؼ����룬������д��paint��һ������
               @Override
               protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon img = new ImageIcon(BackgroudJPanel.class.getResource("bg.png"));  
                  /**
                   * bg.PNG����ط������Լ���ͼƬ
                   * �˴�ʹ�õ����·����bg.png���ò�������ͬһ·����
                   * ��������ʹ�����·������ʹ�þ���·��
                   */
                    img.paintIcon(this, g, 0, 0);
               }
          };
          jpanel.setOpaque(true);
          jframe.setBounds(200, 200, 500, 400);  //������ʾλ�þ������200���ؾ����ϱ�200���ؼ���Ļ��С500*400
          
          jframe.add(jpanel);  //��ӻ��嵽����
          
          jframe.setVisible(true);  //������ʾ����
      }
 
      public static void main(String[] args) {
           new BackgroudJPanel();            // ʵ�������� 
     }
}
