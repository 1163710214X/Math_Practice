package mathPractice;


import java.awt.Graphics;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class BackgroudJPanel {
     private static JFrame jframe;  //声明一个窗体
     private JPanel jpanel;         //声明一个画板
  
     public BackgroudJPanel(){              //构造方法
         jframe = new JFrame();
         init();
     }
 
     private void init(){
         jframe.setTitle("测试");
         jpanel = new JPanel(){//关键代码，就是重写了paint的一个方法
               @Override
               protected void paintComponent(Graphics g) {
                    super.paintComponent(g);
                    ImageIcon img = new ImageIcon(BackgroudJPanel.class.getResource("bg.png"));  
                  /**
                   * bg.PNG这个地方换成自己的图片
                   * 此处使用的相对路径，bg.png跟该测试类在同一路径下
                   * 不过建议使用相对路径避免使用绝对路径
                   */
                    img.paintIcon(this, g, 0, 0);
               }
          };
          jpanel.setOpaque(true);
          jframe.setBounds(200, 200, 500, 400);  //设置显示位置距离左边200像素距离上边200像素及屏幕大小500*400
          
          jframe.add(jpanel);  //添加画板到窗体
          
          jframe.setVisible(true);  //设置显示界面
      }
 
      public static void main(String[] args) {
           new BackgroudJPanel();            // 实例化对象 
     }
}
